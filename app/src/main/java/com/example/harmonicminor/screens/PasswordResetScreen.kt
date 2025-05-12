package com.example.harmonicminor.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.harmonicminor.R
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundGradient3
import com.example.harmonicminor.ui.theme.backgroundGradient4
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.errorColor
import com.example.harmonicminor.ui.theme.textColor
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PasswordResetScreen(auth: FirebaseAuth, navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    var isEmailError by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(backgroundGradient3, backgroundGradient4)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.login_logo),
            contentDescription = "Login image",
            modifier = Modifier
                .size(380.dp)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = stringResource(R.string.password_reset),
            fontSize = 16.sp,
            color = textColor,
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                isEmailError = !it.matches(Regex(emailPattern))
            }, label = {
                Text(text = stringResource(R.string.email_address), color = Color.White)
            },
            modifier = Modifier.width(280.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                errorTextColor = textColor
            ),
            singleLine = true,
            maxLines = 1,
            isError = isEmailError,
            supportingText = {
                if (isEmailError) {
                    Text(text = stringResource(R.string.email_error), color = errorColor)
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(buttonColor1, buttonColor2),
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {
                    auth
                        .sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.navigate(Routes.LoginScreen) {
                                    launchSingleTop = true
                                    navController.popBackStack()
                                }
                            } else {
                                val errorMessage = task.exception?.localizedMessage
                                    ?: "Credencies no coinciden con ningún registro"
                                Toast
                                    .makeText(context, errorMessage, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                }
                .padding(vertical = 16.dp, horizontal = 80.dp)
        ) {
            Text(
                text = stringResource(R.string.send_email),
                color = textColor
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 16.dp))
    }
}