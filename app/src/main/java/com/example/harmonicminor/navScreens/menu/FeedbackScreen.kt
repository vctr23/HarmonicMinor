package com.example.harmonicminor.navScreens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.backgroundGradient1
import com.example.harmonicminor.ui.theme.backgroundGradient2
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(navController: NavController) {
    var feedback by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.feedback)) },
                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = textColor,
                    actionIconContentColor = iconColor
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            backgroundAccentColor,
                            backgroundGradient1,
                            backgroundGradient2
                        ),
                    ),
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(R.string.feedback_text),
                color = textColor,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
            OutlinedTextField(value = feedback,
                onValueChange = {
                    feedback = it
                }, label = {
                    Text(text = stringResource(R.string.message), color = Color.White)
                },
                modifier = Modifier
                    .width(340.dp)
                    .height(260.dp)
                    .padding(vertical = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor,
                )
            )
            Box(
                modifier = Modifier
                    .background(
                        color = backgroundAccentColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { /*Añadir funcion al hacer click*/ }
                    .padding(vertical = 16.dp, horizontal = 80.dp)
            ) {
                Text(
                    text = stringResource(R.string.send_message),
                    color = textColor
                )
            }
        }
    }
}