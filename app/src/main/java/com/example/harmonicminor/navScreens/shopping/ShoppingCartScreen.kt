package com.example.harmonicminor.navScreens.shopping

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.navScreens.home.guitar.GuitarSectionItem
import com.example.harmonicminor.navScreens.home.guitar.GuitarThumbnail
import com.example.harmonicminor.navScreens.home.guitar.GuitarViewModel
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ShoppingCartScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
    ) {
        val guitarViewModel: GuitarViewModel = viewModel()
        val context = LocalContext.current
        val cart = guitarViewModel.cart.value

        val totalPrice = cart.sumOf {
            it.price.replace("[^0-9]".toRegex(), "").toIntOrNull() ?: 0
        }

        LaunchedEffect(Unit) {
            guitarViewModel.loadCart(FirebaseAuth.getInstance().currentUser?.uid ?: "")
        }

        Scaffold { innerPadding ->
            if (cart.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(backgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.cart_empty),
                        fontSize = 20.sp,
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.cart_empty),
                        contentDescription = null,
                        modifier = Modifier
                            .size(500.dp)
                            .padding(vertical = 20.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(top = 45.dp)
                        .weight(1f)
                        .background(backgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                            text = stringResource(R.string.cart),
                            fontSize = 20.sp,
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                    items(cart) { guitar ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            GuitarSectionItem(
                                guitarThumbnail = GuitarThumbnail(
                                    name = guitar.name,
                                    type = guitar.type,
                                    price = guitar.price,
                                    isAvailable = guitar.isAvailable,
                                    thumbNailUrl = guitar.thumbnailUrl
                                ), navController
                            )
                            Box(
                                modifier = Modifier
                                    .height(42.dp)
                                    .width(150.dp)
                                    .padding(vertical = 4.dp)
                                    .padding(bottom = 8.dp)
                                    .background(
                                        Brush.linearGradient(
                                            colors = listOf(buttonColor1, buttonColor2)
                                        ),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .clickable {
                                        guitarViewModel.toggleCart(guitar, context)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.remove_shopping_cart),
                                    contentDescription = null,
                                    modifier = Modifier.size(28.dp),
                                    tint = iconColor
                                )
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .background(
                                    backgroundAccentColor,
                                    shape = RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            // Mostrar dirección de envío
                            Text(text = "Dirección de envío")
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .background(
                                    backgroundAccentColor,
                                    shape = RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(R.string.total_price) + " " + totalPrice.toString() + "€",
                                    fontSize = 18.sp,
                                    color = textColor,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Box(
                                    modifier = Modifier
                                        .height(42.dp)
                                        .width(150.dp)
                                        .padding(vertical = 4.dp)
                                        .padding(bottom = 8.dp)
                                        .background(
                                            Brush.linearGradient(
                                                colors = listOf(buttonColor1, buttonColor2)
                                            ),
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .clickable {

                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.add_card),
                                            contentDescription = null,
                                            modifier = Modifier.size(28.dp)
                                        )
                                        Text(
                                            text = stringResource(R.string.pay),
                                            color = textColor,
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}