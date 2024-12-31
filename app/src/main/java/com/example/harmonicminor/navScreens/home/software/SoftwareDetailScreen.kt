package com.example.harmonicminor.navScreens.home.software

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.harmonicminor.R
import com.example.harmonicminor.navScreens.home.ViewModelFactory
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
fun SoftwareDetailScreen(navController: NavController, softwareName: String) {
    val softwareViewModel: ItemViewModel<Software> = viewModel(factory = ViewModelFactory(Software::class.java))
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        softwareViewModel.readItems("softwares")
    }

    val software = softwareViewModel.items.find { it.name == softwareName }

    if (software != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = software.name) },
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
                        painter = rememberAsyncImagePainter(software.imageUrl),
                        contentDescription = software.name,
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
                        text = stringResource(R.string.type) + " " + software.type,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp,
                        color = textColor
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.manufacturer) + " " + software.manufacturer,
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
                        text = software.description,
                        modifier = Modifier.padding(8.dp, vertical = 2.dp),
                        fontSize = 16.sp,
                        color = textColor
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.price) +" "+software.price,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp,
                        color = textColor
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.stock) +" "+software.stock,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp,
                        color = textColor
                    )
                }
                item {
                    if (software.isAvailable) {
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
                                    softwareViewModel.toggleFavourite(software, context, "softwares")
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
                                    softwareViewModel.toggleCart(software, context, "softwares")
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
//fun AddSoftwaresAutomatically() {
//    val db = FirebaseFirestore.getInstance()
//    val context = LocalContext.current
//
//    // Lista de softwares
//    val softwares = listOf(
//        Software(
//            name = stringResource(R.string.software1_name),
//            type = stringResource(R.string.software1_type),
//            description = stringResource(R.string.software1_description),
//            manufacturer = stringResource(R.string.software1_manufacturer),
//            price = stringResource(R.string.software1_price),
//            stock = stringResource(R.string.software1_stock),
//            isAvailable = true,
//            imageUrl = "https://drive.google.com/uc?id=15XqI2OfCFv4GvYACwgO3yesSre_H5EiM",
//            thumbnailUrl = "https://drive.google.com/uc?id=1y2fxtOYB043NMVdRGfx8EkjJgkivIdqx",
//        ),
//        Software(
//            name = stringResource(R.string.software2_name),
//            type = stringResource(R.string.software2_type),
//            description = stringResource(R.string.software2_description),
//            manufacturer = stringResource(R.string.software2_manufacturer),
//            price = stringResource(R.string.software2_price),
//            stock = stringResource(R.string.software2_stock),
//            isAvailable = true,
//            imageUrl = "https://drive.google.com/uc?id=1Ci1Bu8WuaoC0qvbeCs18gKUiJnF_EuBG",
//            thumbnailUrl = "https://drive.google.com/uc?id=1r1yyYmGCHGqPYIjAyFgJqyNbX2-dLws0",
//        ),
//        Software(
//            name = stringResource(R.string.software3_name),
//            type = stringResource(R.string.software3_type),
//            description = stringResource(R.string.software3_description),
//            manufacturer = stringResource(R.string.software3_manufacturer),
//            price = stringResource(R.string.software3_price),
//            stock = stringResource(R.string.software3_stock),
//            isAvailable = true,
//            imageUrl = "https://drive.google.com/uc?id=1be_9KhaT4e5zUHSvvgMA0Dt04MLtZ0fQ",
//            thumbnailUrl = "https://drive.google.com/uc?id=1ewy3udvWVV8F8fWUGNRsTaJiEqEXB6PS",
//        ),
//        Software(
//            name = stringResource(R.string.software4_name),
//            type = stringResource(R.string.software4_type),
//            description = stringResource(R.string.software4_description),
//            manufacturer = stringResource(R.string.software4_manufacturer),
//            price = stringResource(R.string.software4_price),
//            stock = stringResource(R.string.software4_stock),
//            isAvailable = true,
//            imageUrl = "https://drive.google.com/uc?id=1N1rIzAWjH6O_PookXLw02xba8JnT4I4W",
//            thumbnailUrl = "https://drive.google.com/uc?id=1CnItYqRbRL12SIsmtNEmlg9JVn9e_VBb",
//        ),
//        Software(
//            name = stringResource(R.string.software5_name),
//            type = stringResource(R.string.software5_type),
//            description = stringResource(R.string.software5_description),
//            manufacturer = stringResource(R.string.software5_manufacturer),
//            price = stringResource(R.string.software5_price),
//            stock = stringResource(R.string.software5_stock),
//            isAvailable = true,
//            imageUrl = "https://drive.google.com/uc?id=1GLvMFlNlMOAxeYl0vQ-aY953Q5V-lYQh",
//            thumbnailUrl = "https://drive.google.com/uc?id=1uf5B7dlGjTwTB8UxcZkqk96XgilnWyL1",
//        )
//    )
//
//    // Ejecuta automáticamente al componerse
//    LaunchedEffect(Unit) {
//        addSoftwaresToFirestore(db, softwares)
//    }
//}
//
//fun addSoftwaresToFirestore(db: FirebaseFirestore, softwares: List<Software>) {
//    for (software in softwares) {
//        db.collection("softwares")
//            .add(software)
//            .addOnSuccessListener { documentReference ->
//                Log.d("Firestore", "Software añadido con ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.e("Firestore", "Error al añadir software", e)
//            }
//    }
//}
