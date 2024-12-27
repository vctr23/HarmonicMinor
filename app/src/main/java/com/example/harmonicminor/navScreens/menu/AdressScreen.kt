package com.example.harmonicminor.navScreens.menu

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current
    var id by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var lastname by rememberSaveable { mutableStateOf("") }
    var street by rememberSaveable { mutableStateOf("") }
    var postcode by rememberSaveable { mutableStateOf("") }
    var locality by rememberSaveable { mutableStateOf("") }
    var country by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.address))
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
                .background(backgroundColor),
        ) {
            OutlinedTextField(
                value = id,
                onValueChange = {
                    id = it
                },
                label = {
                    Text(text = stringResource(R.string.id), color = textColor)
                },
                trailingIcon = {
                    Box(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .background(iconColor, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "OPTIONAL",
                            color = backgroundColor,
                            fontSize = 9.sp,
                        )
                    }
                },
                modifier = Modifier
                    .width(500.dp)
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor
                ),
                singleLine = true,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text(text = stringResource(R.string.name), color = textColor)
                },
                modifier = Modifier
                    .width(500.dp)
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor
                ),
                singleLine = true,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            OutlinedTextField(
                value = lastname,
                onValueChange = {
                    lastname = it
                },
                label = {
                    Text(text = stringResource(R.string.last_name), color = textColor)
                },
                modifier = Modifier
                    .width(500.dp)
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor
                ),
                singleLine = true,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            OutlinedTextField(
                value = street,
                onValueChange = {
                    street = it
                },
                label = {
                    Text(text = stringResource(R.string.street), color = textColor)
                },
                modifier = Modifier
                    .width(500.dp)
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor
                ),
                singleLine = true,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedTextField(
                    value = postcode,
                    onValueChange = {
                        postcode = it
                    },
                    label = {
                        Text(text = stringResource(R.string.postcode), color = textColor)
                    },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(start = 16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor,
                        errorTextColor = textColor
                    ),
                    singleLine = true,
                    maxLines = 1,
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                OutlinedTextField(
                    value = locality,
                    onValueChange = {
                        locality = it
                    },
                    label = {
                        Text(text = stringResource(R.string.locality), color = textColor)
                    },
                    modifier = Modifier
                        .width(210.dp)
                        .padding(horizontal = 16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor,
                        errorTextColor = textColor
                    ),
                    singleLine = true,
                    maxLines = 1,
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            OutlinedTextField(
                value = country,
                onValueChange = {
                    country = it
                },
                label = {
                    Text(text = stringResource(R.string.address_country), color = textColor)
                },
                modifier = Modifier
                    .width(500.dp)
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor
                ),
                singleLine = true,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = stringResource(R.string.contact),
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            OutlinedTextField(
                value = phone,
                onValueChange = {
                    phone = it
                },
                label = {
                    Text(text = stringResource(R.string.phone), color = textColor)
                },
                trailingIcon = {
                    Box(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .background(iconColor, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "OPTIONAL",
                            color = backgroundColor,
                            fontSize = 9.sp,
                        )
                    }
                },
                modifier = Modifier
                    .width(500.dp)
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor
                ),
                singleLine = true,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

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
                        val userId = auth.currentUser?.uid

                        if (userId != null) {
                            if (name.isEmpty() || lastname.isEmpty() || street.isEmpty() ||
                                postcode.isEmpty() || locality.isEmpty() || country.isEmpty()
                            ) {
                                Toast
                                    .makeText(
                                        context,
                                        context.getString(R.string.address_empty_fields),
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                                return@clickable
                            }


                            val addressData = mutableMapOf<String, Any>(
                                "name" to name,
                                "lastname" to lastname,
                                "street" to street,
                                "postcode" to postcode,
                                "locality" to locality,
                                "country" to country
                            )

                            if (id.isNotEmpty()) addressData["id"] = id
                            if (phone.isNotEmpty()) addressData["phone"] = phone

                            db
                                .collection("address")
                                .document(userId)
                                .set(addressData)
                                .addOnSuccessListener {
                                    Toast
                                        .makeText(
                                            context,
                                            context.getString(R.string.address_success),
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                    navController.popBackStack()
                                }
                                .addOnFailureListener { e ->
                                    Toast
                                        .makeText(
                                            context,
                                            context.getString(R.string.userid_not_found) + e.localizedMessage,
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.save_address),
                    color = textColor
                )
            }
        }
    }
}