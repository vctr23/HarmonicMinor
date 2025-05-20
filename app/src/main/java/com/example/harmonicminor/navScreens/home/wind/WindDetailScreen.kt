package com.example.harmonicminor.navScreens.home.wind

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.harmonicminor.R
import com.example.harmonicminor.navScreens.favourite.FavouriteViewModel
import com.example.harmonicminor.navScreens.shopping.ShoppingCartViewModel
import com.example.harmonicminor.ui.theme.backgroundAccentColor
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
fun WindDetailScreen(navController: NavController, djName: String) {
    val windViewModel: WindViewModel = viewModel()
    val favouriteViewModel: FavouriteViewModel = viewModel()
    val shoppingCartViewModel: ShoppingCartViewModel = viewModel()
    val context = LocalContext.current
    val currentLocale = context.resources.configuration.locales[0]
    val selectedLanguage = currentLocale.language

    LaunchedEffect(Unit) {
        windViewModel.readWind()
    }

    val wind = windViewModel.wind.find { it.name == djName }


    if (wind != null) {
        val description = wind.description[selectedLanguage] ?: wind.description["es"] ?: ""

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = wind.name) },
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
                        painter = rememberAsyncImagePainter(wind.imageUrl),
                        contentDescription = wind.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(secondaryTextColor),
                        contentScale = ContentScale.Fit,
                    )
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                            .shadow(16.dp, shape = RoundedCornerShape(12.dp))
                            .background(backgroundAccentColor, shape = RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(8.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.type) + " " + wind.type,
                                modifier = Modifier.padding(8.dp),
                                fontSize = 18.sp,
                                color = textColor
                            )
                            Text(
                                text = stringResource(R.string.manufacturer) + " " + wind.manufacturer,
                                modifier = Modifier.padding(8.dp),
                                fontSize = 18.sp,
                                color = textColor
                            )
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                            .shadow(16.dp, shape = RoundedCornerShape(12.dp))
                            .background(backgroundAccentColor, shape = RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(8.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.description),
                                fontSize = 18.sp,
                                color = textColor
                            )
                            Text(
                                text = description,
                                modifier = Modifier.padding(8.dp, vertical = 2.dp),
                                fontSize = 16.sp,
                                color = textColor
                            )
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                            .shadow(16.dp, shape = RoundedCornerShape(12.dp))
                            .background(backgroundAccentColor, shape = RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(8.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.price) + " " + wind.price,
                                modifier = Modifier.padding(8.dp),
                                fontSize = 18.sp,
                                color = textColor
                            )
                            Text(
                                text = stringResource(R.string.stock) + " " + wind.stock,
                                modifier = Modifier.padding(8.dp),
                                fontSize = 18.sp,
                                color = textColor
                            )
                        }
                    }
                }
                item {
                    if (wind.isAvailable) {
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
                                    favouriteViewModel.toggleFavourite(wind, context)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.heart_plus),
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
                                    shoppingCartViewModel.toggleCart(wind, context)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.add_shopping_cart),
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



