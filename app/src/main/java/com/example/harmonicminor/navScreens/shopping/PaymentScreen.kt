package com.example.harmonicminor.navScreens.shopping

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.OffsetMapping.Companion.Identity
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.secondaryTextColor
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
            CardModel(navController)
        }
    }
}

@Composable
fun CardModel(navController: NavController) {
    val context = LocalContext.current
    var cardNum by remember { mutableStateOf("") }
    val validNum = "^[0-9]*$" // Only numbers regex
    var isNumError by remember { mutableStateOf(false) }
    var cardCVC by remember { mutableStateOf("") }
    var isCVCError by remember { mutableStateOf(false) }
    var cardHolderName by remember { mutableStateOf("") }
    var isHolderNameError by remember { mutableStateOf(true) }
    var cardExpirationDate by remember { mutableStateOf("") }
    var isDateError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(235.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(12.dp),
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
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "HarmonicMinor",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
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
                    // Variable that displays the card number with the visual transformation
                    val displayedCardNum =
                        if (cardNum.isEmpty()) "XXXX XXXX XXXX XXXX" else CardNumberVisualTransformation().filter(
                            AnnotatedString(cardNum)
                        ).text.text.replace("-", " ")
                    Text(
                        text = AnnotatedString(displayedCardNum),
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Expiration date and security code
                    // Variable that displays the expiration date with the visual transformation
                    val displayedCardExpirationDate =
                        if (cardExpirationDate.isEmpty()) "exp XX/XX" else "exp " + DateVisualTransformation().filter(
                            AnnotatedString(cardExpirationDate)
                        ).text.text
                    Row() {
                        Text(
                            text = AnnotatedString(displayedCardExpirationDate),
                            fontSize = 12.sp,
                            color = secondaryTextColor
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = if (cardCVC == "") "cvc" else "cvc: $cardCVC",
                            fontSize = 16.sp,
                            color = textColor
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Cardholder Name
                    Text(
                        text = if (cardHolderName == "") "App User" else cardHolderName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                }
            }
        }

        // Spacer between the card and the textfields
        Spacer(modifier = Modifier.size(16.dp))

        // Text field for card number
        OutlinedTextField(
            value = cardNum,
            onValueChange = {
                cardNum = it
                isNumError = cardNum.length < 16 || cardNum.length > 16 || !cardNum.trim()
                    .matches(Regex(validNum))
            },
            label = {
                Text(text = stringResource(R.string.card_number), color = textColor)
            },
            modifier = Modifier
                .width(380.dp)
                .height(75.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                errorTextColor = textColor
            ),
            singleLine = true,
            maxLines = 1,
            isError = isNumError,
            supportingText = {
                if (isNumError) {
                    Text(text = stringResource(R.string.card_number_error))
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            visualTransformation = CardNumberVisualTransformation()
        )
        Spacer(modifier = Modifier.size(16.dp))

        // Row with textfields for card security code & card expiration date
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = cardExpirationDate,
                onValueChange = {
                    cardExpirationDate = it
                    isDateError = cardExpirationDate.isEmpty() || cardExpirationDate.length < 4 || cardExpirationDate.length > 4
                },
                label = {
                    Text(text = stringResource(R.string.card_expiration), color = textColor)
                },
                visualTransformation = DateVisualTransformation(),
                modifier = Modifier
                    .width(180.dp)
                    .height(90.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor
                ),
                singleLine = true,
                maxLines = 1,
                isError = isDateError,
                supportingText = {
                    if (isDateError) {
                        Text(text = stringResource(R.string.card_date_error))
                    }
                },
                keyboardOptions = KeyboardOptions(
                    // Only numeric keyboard
                    keyboardType = KeyboardType.Number,
                    // Done when clicking enter
                    imeAction = ImeAction.Done
                )
            )
            OutlinedTextField(
                value = cardCVC,
                onValueChange = {
                    cardCVC = it
                    isCVCError = cardCVC.length < 3 || cardCVC.length > 3 || !cardCVC.trim()
                        .matches(Regex(validNum))
                },
                label = {
                    Text(text = stringResource(R.string.card_cvc), color = textColor)
                },
                modifier = Modifier
                    .width(180.dp)
                    .height(90.dp)
                    .wrapContentSize(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor
                ),
                singleLine = true,
                maxLines = 1,
                isError = isCVCError,
                supportingText = {
                    if (isCVCError) {
                        Text(text = stringResource(R.string.card_cvc_error))
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }
        Spacer(modifier = Modifier.size(16.dp))

        // Textfield for card holder name
        OutlinedTextField(
            value = cardHolderName,
            onValueChange = {
                cardHolderName = it
                isHolderNameError = cardHolderName.isEmpty()
            },
            label = {
                Text(text = stringResource(R.string.card_owner_name), color = textColor)
            },
            modifier = Modifier
                .width(380.dp)
                .height(75.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                errorTextColor = textColor
            ),
            singleLine = true,
            maxLines = 1,
            isError = isHolderNameError,
            supportingText = {
                if (isHolderNameError) {
                    Text(text = stringResource(R.string.card_owner_name_error))
                }
            }
        )

        // Row with the button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(buttonColor1, buttonColor2),
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {
                        if (isDateError || isHolderNameError || isCVCError || isNumError) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.card_error),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            navController.navigate(Routes.PaymentCorrectScreen)
                        }
                    }
                    .padding(vertical = 16.dp, horizontal = 80.dp)
            ) {
                Text(
                    text = stringResource(R.string.pay),
                    color = textColor
                )
            }
        }
    }
}

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Only work with numbers
        val digits = text.text.filter { it.isDigit() }.take(4)

        if (digits.length <= 2) {
            return TransformedText(
                AnnotatedString(digits),
                offsetMapping = Identity
            )
        }

        val formatted = "${digits.take(2)}/${digits.drop(2)}"

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // Si el offset es menor o igual a 2, no se afecta.
                // Si es mayor que 2, se le suma 1 (por la barra insertada).
                return if (offset <= 2) offset else offset + 1
            }

            override fun transformedToOriginal(offset: Int): Int {
                // Si el offset está en la parte antes de la barra, es el mismo.
                // Si es mayor que 2, se resta 1 para obtener la posición original.
                return if (offset <= 2) offset else offset - 1
            }
        }

        return TransformedText(
            AnnotatedString(formatted),
            offsetMapping
        )
    }
}

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text.filter { it.isDigit() } // Filtrar solo números
        val formattedText = StringBuilder()
        val offsets = mutableListOf<Int>()

        var originalIndex = 0
        for (i in originalText.indices) {
            if (i > 0 && i % 4 == 0) {
                formattedText.append('-') // Agregar guión cada 4 dígitos
            }
            formattedText.append(originalText[i])
            offsets.add(originalIndex)
            originalIndex++
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset >= offsets.size) return formattedText.length
                return offsets[offset] + (offset / 4)
            }

            override fun transformedToOriginal(offset: Int): Int {
                val cleanOffset = offset.coerceIn(0, formattedText.length)
                return offsets.indexOfFirst { it >= cleanOffset }.takeIf { it != -1 }
                    ?: originalText.length
            }
        }

        return TransformedText(AnnotatedString(formattedText.toString()), offsetMapping)
    }
}