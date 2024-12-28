package com.example.harmonicminor.navScreens.menu.emailUpdate

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.harmonicminor.R
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.errorColor
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailUpdateScreen(auth: FirebaseAuth, navController: NavHostController) {
    var newEmail by remember { mutableStateOf("") }
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    var isEmailError by remember { mutableStateOf(false) }
    var isProcessing by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val user = auth.currentUser

    Scaffold(
        topBar =
        {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.email_reset))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = textColor,
                    navigationIconContentColor = iconColor,
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(backgroundAccentColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.email_reset),
                color = textColor,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            OutlinedTextField(
                value = newEmail,
                onValueChange = {
                    newEmail = it
                    isEmailError = !it.matches(emailPattern.toRegex())
                },
                label = { Text(text = stringResource(R.string.new_email)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                isError = isEmailError,
                supportingText = {
                    if (isEmailError) {
                        Text(text = stringResource(R.string.email_error), color = errorColor)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor
                ),
                singleLine = true,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 50.dp)
                    .height(48.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                buttonColor1,
                                buttonColor2
                            )
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {
                        isProcessing = true
                        user?.let {
                            it
                                .verifyBeforeUpdateEmail(newEmail)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        val db = FirebaseFirestore.getInstance()
                                        val userRef = db
                                            .collection("users")
                                            .document(it.uid)

                                        userRef
                                            .update("email", newEmail)
                                            .addOnSuccessListener {
                                                navController.navigate(Routes.InitialScreen) {
                                                    launchSingleTop = true
                                                    navController.popBackStack()
                                                }
                                            }
                                            .addOnFailureListener { e ->
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "Error updating email in Firestore: ${e.message}",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                            }
                                    } else {
                                        val errorMessage = task.exception?.localizedMessage
                                            ?: "Error updating email"
                                        Toast
                                            .makeText(context, errorMessage, Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                    isProcessing = false
                                }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.send_new_email),
                    color = textColor
                )

                if (isProcessing) {
                    CircularProgressIndicator(color = textColor)
                } else {
                    Text(
                        text = stringResource(R.string.send_new_email),
                        color = textColor
                    )
                }
            }
        }
    }
}