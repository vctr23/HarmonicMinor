package com.example.harmonicminor.navScreens.home.dj

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
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
fun DjDetailScreen(navController: NavController, djName: String) {
    val djViewModel: DjViewModel = viewModel()

    LaunchedEffect(Unit) {
        djViewModel.readDj()
    }

    val dj = djViewModel.dj.find { it.name == djName }

    if (dj != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = dj.name) },
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
                        painter = rememberAsyncImagePainter(dj.imageUrl),
                        contentDescription = dj.name,
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
                        text = stringResource(R.string.type) + " " + dj.type,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp,
                        color = textColor
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.manufacturer) + " " + dj.manufacturer,
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
                        text = dj.description,
                        modifier = Modifier.padding(8.dp, vertical = 2.dp),
                        fontSize = 16.sp,
                        color = textColor
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.price) + " " + dj.price,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp,
                        color = textColor
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.stock) + " " + dj.stock,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp,
                        color = textColor
                    )
                }
                item {
                    if (dj.isAvailable) {
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

//@Composable
//fun AddDjsAutomatically() {
//    val db = FirebaseFirestore.getInstance()
//
//    val djs = listOf(
//        Dj(
//            name = stringResource(R.string.dj1_name),
//            type = stringResource(R.string.dj1_type),
//            description = stringResource(R.string.dj1_description),
//            manufacturer = stringResource(R.string.dj1_manufacturer),
//            price = stringResource(R.string.dj1_price),
//            stock = stringResource(R.string.dj1_stock),
//            isAvailable = true,
//            imageUrl = "https://drive.google.com/uc?id=1mx9Ky31hzQw9ADpnC_iGI4DHAA0mLy1r",
//            thumbnailUrl = "https://drive.google.com/uc?id=15oHtjjhbNKgGa8ErXQipQM5REerrw0Lm"
//        ),
//        Dj(
//            name = stringResource(R.string.dj2_name),
//            type = stringResource(R.string.dj2_type),
//            description = stringResource(R.string.dj2_description),
//            manufacturer = stringResource(R.string.dj2_manufacturer),
//            price = stringResource(R.string.dj2_price),
//            stock = stringResource(R.string.dj2_stock),
//            isAvailable = true,
//            imageUrl = "https://drive.google.com/uc?id=1jYHvKD-3XN7JTTCnbXuMvOmuoxwBgRpA",
//            thumbnailUrl = "https://drive.google.com/uc?id=1lkR8wWsJI7o7oMfEYleFPqnGpyk40LiN"
//        ),
//        Dj(
//            name = stringResource(R.string.dj3_name),
//            type = stringResource(R.string.dj3_type),
//            description = stringResource(R.string.dj3_description),
//            manufacturer = stringResource(R.string.dj3_manufacturer),
//            price = stringResource(R.string.dj3_price),
//            stock = stringResource(R.string.dj3_stock),
//            isAvailable = true,
//            imageUrl = "https://drive.google.com/uc?id=1uQpsjVCpI6WZ-eUUaC2o0PH3pME9qjaJ",
//            thumbnailUrl = "https://drive.google.com/uc?id=1eTxE3c5bO5rRI_s3zdjzE13PUfW87fbA"
//        ),
//        Dj(
//            name = stringResource(R.string.dj4_name),
//            type = stringResource(R.string.dj4_type),
//            description = stringResource(R.string.dj4_description),
//            manufacturer = stringResource(R.string.dj4_manufacturer),
//            price = stringResource(R.string.dj4_price),
//            stock = stringResource(R.string.dj4_stock),
//            isAvailable = true,
//            imageUrl = "https://drive.google.com/uc?id=1Bq7AN4VzWfDDJAFopnnTAIMxoJnPVq2t",
//            thumbnailUrl = "https://drive.google.com/uc?id=1aS4NqvT8086umemX9Ypwt8qU3grW0Tl3"
//        ),
//        Dj(
//            name = stringResource(R.string.dj5_name),
//            type = stringResource(R.string.dj5_type),
//            description = stringResource(R.string.dj5_description),
//            manufacturer = stringResource(R.string.dj5_manufacturer),
//            price = stringResource(R.string.dj5_price),
//            stock = stringResource(R.string.dj5_stock),
//            isAvailable = true,
//            imageUrl = "https://drive.google.com/uc?id=1kFXXdaFg8VkXOzPS5TDtYPcHF-rtRaE4",
//            thumbnailUrl = "https://drive.google.com/uc?id=10JM0T8f1ptjrvagQUBOjysHfP2r5byy8"
//        ),
//    )
//
//    // Ejecuta automáticamente al componerse
//    LaunchedEffect(Unit) {
//        addDjsToFirestore(db, djs)
//    }
//}
//
//fun addDjsToFirestore(db: FirebaseFirestore, djs: List<Dj>) {
//    for (dj in djs) {
//        db.collection("djs")
//            .add(dj)
//            .addOnSuccessListener { documentReference ->
//                Log.d("Firestore", "dj añadido con ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.e("Firestore", "Error al añadir dj", e)
//            }
//    }
//}

