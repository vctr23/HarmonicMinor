package com.example.harmonicminor.navScreens.home.guitar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.notOnStockColor
import com.example.harmonicminor.ui.theme.secondaryTextColor
import com.example.harmonicminor.ui.theme.stockColor
import com.example.harmonicminor.ui.theme.textColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuitarDetailScreen(navController: NavController, guitarName: String) {
    val guitars = listOf(
        Guitar(
            name = stringResource(R.string.guitar1_name),
            type = stringResource(R.string.guitar1_type),
            description = stringResource(R.string.guitar1_description),
            manufacturer = stringResource(R.string.guitar1_manufacturer),
            price = stringResource(R.string.guitar1_price),
            stock = stringResource(R.string.guitar1_stock),
            isAvailable = true,
            imageRes = R.drawable.strato_full,
        ),
        Guitar(
            name = stringResource(R.string.guitar2_name),
            type = stringResource(R.string.guitar2_type),
            description = stringResource(R.string.guitar2_description),
            manufacturer = stringResource(R.string.guitar2_manufacturer),
            price = stringResource(R.string.guitar2_price),
            stock = stringResource(R.string.guitar2_stock),
            isAvailable = true,
            imageRes = R.drawable.strato_full2,
        ),
        Guitar(
            name = stringResource(R.string.guitar3_name),
            type = stringResource(R.string.guitar3_type),
            description = stringResource(R.string.guitar3_description),
            manufacturer = stringResource(R.string.guitar3_manufacturer),
            price = stringResource(R.string.guitar3_price),
            stock = stringResource(R.string.guitar3_stock),
            isAvailable = false,
            imageRes = R.drawable.tele_full,
        ),
        Guitar(
            name = stringResource(R.string.guitar4_name),
            type = stringResource(R.string.guitar4_type),
            description = stringResource(R.string.guitar4_description),
            manufacturer = stringResource(R.string.guitar4_manufacturer),
            price = stringResource(R.string.guitar4_price),
            stock = stringResource(R.string.guitar4_stock),
            isAvailable = true,
            imageRes = R.drawable.tele_full2,
        ),
        Guitar(
            name = stringResource(R.string.guitar5_name),
            type = stringResource(R.string.guitar5_type),
            description = stringResource(R.string.guitar5_description),
            manufacturer = stringResource(R.string.guitar5_manufacturer),
            price = stringResource(R.string.guitar5_price),
            stock = stringResource(R.string.guitar5_stock),
            isAvailable = true,
            imageRes = R.drawable.lespaul_full,
        ),
        Guitar(
            name = stringResource(R.string.guitar6_name),
            type = stringResource(R.string.guitar6_type),
            description = stringResource(R.string.guitar6_description),
            manufacturer = stringResource(R.string.guitar6_manufacturer),
            price = stringResource(R.string.guitar6_price),
            stock = stringResource(R.string.guitar6_stock),
            isAvailable = true,
            imageRes = R.drawable.lespaul_full2,
        ),
        //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        Guitar(
            name = stringResource(R.string.guitar7_name),
            type = stringResource(R.string.guitar7_type),
            description = stringResource(R.string.guitar7_description),
            manufacturer = stringResource(R.string.guitar7_manufacturer),
            price = stringResource(R.string.guitar7_price),
            stock = stringResource(R.string.guitar7_stock),
            isAvailable = false,
            imageRes = R.drawable.heavy_full,
        ),
        Guitar(
            name = stringResource(R.string.guitar8_name),
            type = stringResource(R.string.guitar8_type),
            description = stringResource(R.string.guitar8_description),
            manufacturer = stringResource(R.string.guitar8_manufacturer),
            price = stringResource(R.string.guitar8_price),
            stock = stringResource(R.string.guitar8_stock),
            isAvailable = true,
            imageRes = R.drawable.superstrat_full,
        ),
        Guitar(
            name = stringResource(R.string.guitar9_name),
            type = stringResource(R.string.guitar9_type),
            description = stringResource(R.string.guitar9_description),
            manufacturer = stringResource(R.string.guitar9_manufacturer),
            price = stringResource(R.string.guitar9_price),
            stock = stringResource(R.string.guitar9_stock),
            isAvailable = true,
            imageRes = R.drawable.sevenstring_full,
        ),
        Guitar(
            name = stringResource(R.string.guitar10_name),
            type = stringResource(R.string.guitar10_type),
            description = stringResource(R.string.guitar10_description),
            manufacturer = stringResource(R.string.guitar10_manufacturer),
            price = stringResource(R.string.guitar10_price),
            stock = stringResource(R.string.guitar10_stock),
            isAvailable = true,
            imageRes = R.drawable.sevenstring_full2,
        ),
        Guitar(
            name = stringResource(R.string.guitar11_name),
            type = stringResource(R.string.guitar11_type),
            description = stringResource(R.string.guitar11_description),
            manufacturer = stringResource(R.string.guitar11_manufacturer),
            price = stringResource(R.string.guitar11_price),
            stock = stringResource(R.string.guitar11_stock),
            isAvailable = true,
            imageRes = R.drawable.eightstring_full,
        ),
        Guitar(
            name = stringResource(R.string.guitar12_name),
            type = stringResource(R.string.guitar12_type),
            description = stringResource(R.string.guitar12_description),
            manufacturer = stringResource(R.string.guitar12_manufacturer),
            price = stringResource(R.string.guitar12_price),
            stock = stringResource(R.string.guitar12_stock),
            isAvailable = true,
            imageRes = R.drawable.hollow_full,
        ),
    )

    val guitar = guitars.find { it.name == guitarName }

    if (guitar != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = guitar.name) },
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(backgroundColor),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Image(
                        painter = painterResource(guitar.imageRes),
                        contentDescription = guitar.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(secondaryTextColor),
                        contentScale = ContentScale.Fit,
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.type) + " " + guitar.type,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp,
                        color = textColor
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.manufacturer) + " " + guitar.manufacturer,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp,
                        color = textColor
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.description),
                        fontSize = 18.sp,
                        color = textColor
                    )
                    Text(
                        text = guitar.description,
                        modifier = Modifier.padding(8.dp, vertical = 2.dp),
                        fontSize = 16.sp,
                        color = textColor
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.price) + " " + guitar.price,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp,
                        color = textColor
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.stock) + " " + guitar.stock,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp,
                        color = textColor
                    )
                }
                item {
                    if (guitar.isAvailable) {
                        Text(
                            text = stringResource(R.string.available),
                            modifier = Modifier.padding(8.dp),
                            fontSize = 18.sp,
                            color = stockColor
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.not_available),
                            modifier = Modifier.padding(8.dp),
                            fontSize = 18.sp,
                            color = notOnStockColor
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 16.dp, horizontal = 20.dp)
                                .height(42.dp)
                                .width(150.dp)
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(buttonColor1, buttonColor2)
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    // Funcionalidad favorito
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp),
                                tint = iconColor
                            )
                        }

                        Box(
                            modifier = Modifier
                                .padding(vertical = 16.dp, horizontal = 20.dp)
                                .height(42.dp)
                                .width(150.dp)
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(buttonColor1, buttonColor2)
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    // Funcionalidad carrito
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.ShoppingCart,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp),
                                tint = iconColor
                            )
                        }
                    }
                }
            }
        }
    }
}