package com.example.harmonicminor.navScreens.shopping

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.payment_method))
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
                .padding(innerPadding)
                .background(backgroundColor)
                .fillMaxHeight()
        ) {
            CardModel()
        }
    }
}

@Composable
fun CardModel(modifier: Modifier = Modifier) {
    var cardNum by remember { mutableStateOf("") }
    var cardCVC by remember { mutableStateOf("") }
    var cardHolderName by remember { mutableStateOf("") }
    var cardExpirationDate by remember { mutableStateOf("") }
    Column() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF1E1E1E), Color(0xFF3D3D3D)),
                            start = Offset(0f, 0f),
                            end = Offset(1000f, 1000f)
                        )
                    )
                    .padding(16.dp)
            ) {
                Column {
                    // Upper text with bank name
                    Row() {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "HarmonicMinor",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // "Mastercard" logo
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Canvas(modifier = Modifier.size(24.dp)) {
                            drawCircle(color = Color.Red)
                        }
                        Canvas(modifier = Modifier.size(24.dp)) {
                            drawCircle(color = Color.Yellow)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Card Number
                    Text(
                        text = "XXXX XXXX XXXX XXXX",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Expiration date
                    Text(
                        text = "VALID THRU XX/XX",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Cardholder Name
                    Text(
                        text = "App User",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
        // Text field for card number
        OutlinedTextField(
            value = cardNum,
            onValueChange = {
                cardNum = it
            },
            label = {
                Text(text = stringResource(R.string.card_owner_name), color = textColor)
            },
            modifier = Modifier.width(280.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                errorTextColor = textColor
            ),
            singleLine = true,
            maxLines = 1,
        )
        // Row with textfields for card security code & card expiration date
        Row(){
            OutlinedTextField(
                value = cardExpirationDate,
                onValueChange = {
                    cardExpirationDate = it
                },
                label = {
                    Text(text = stringResource(R.string.card_owner_name), color = textColor)
                },
                modifier = Modifier.width(280.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor
                ),
                singleLine = true,
                maxLines = 1,
            )
            OutlinedTextField(
                value = cardCVC,
                onValueChange = {
                    cardCVC = it
                },
                label = {
                    Text(text = stringResource(R.string.card_owner_name), color = textColor)
                },
                modifier = Modifier.width(280.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor
                ),
                singleLine = true,
                maxLines = 1,
            )
        }
        // Textfield for card holder name
        OutlinedTextField(
            value = cardHolderName,
            onValueChange = {
                cardHolderName = it
            },
            label = {
                Text(text = stringResource(R.string.card_owner_name), color = textColor)
            },
            modifier = Modifier.width(280.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                errorTextColor = textColor
            ),
            singleLine = true,
            maxLines = 1,
        )
    }
}