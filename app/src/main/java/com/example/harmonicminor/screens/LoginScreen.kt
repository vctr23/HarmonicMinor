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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.errorColor
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Login(navController: NavController, modifier: Modifier = Modifier, auth: FirebaseAuth) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.login_logo),
            contentDescription = "Login image",
            modifier = Modifier
                .size(380.dp)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(R.string.welcome),
            fontSize = 26.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = stringResource(R.string.sing),
            fontSize = 20.sp,
            color = textColor
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(value = email,
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

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                isPasswordError = password.length < 6 || password.length > 12
            },
            label = {
                Text(text = stringResource(R.string.password), color = textColor)
            },
            modifier = Modifier.width(280.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                errorTextColor = textColor
            ),
            singleLine = true,
            maxLines = 1,
            isError = isPasswordError,
            supportingText = {
                if (isPasswordError) {
                    Text(text = stringResource(R.string.password_error), color = errorColor)
                }
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (passwordVisible) R.drawable.visibility else R.drawable.visibility_off
                        ),
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

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
                        .signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.navigate(Routes.MainScreen) {
                                    launchSingleTop = true
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
                text = stringResource(R.string.sing_in),
                color = textColor
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(R.string.forgot_password),
            modifier = Modifier.clickable { navController.navigate(Routes.PasswordResetScreen) },
            color = textColor
        )
    }
}


