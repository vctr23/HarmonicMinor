package com.example.harmonicminor.navScreens.favourite

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavouriteScreen(navController: NavController) {
    val guitarViewModel: GuitarViewModel = viewModel()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        guitarViewModel.loadFavourites(FirebaseAuth.getInstance().currentUser?.uid ?: "")
    }

    val guitarFavourites = guitarViewModel.favourites.value

    Scaffold {
        if (guitarFavourites.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.favourites_empty),
                    fontSize = 20.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                Image(
                    painter = painterResource(R.drawable.favourite_empty),
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
                    .background(backgroundColor),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = stringResource(R.string.favourites),
                        fontSize = 20.sp,
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
                items(guitarFavourites) { favourite ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GuitarSectionItem(
                            guitarThumbnail = GuitarThumbnail(
                                name = favourite.name,
                                type = favourite.type,
                                price = favourite.price,
                                isAvailable = favourite.isAvailable,
                                thumbNailUrl = favourite.thumbnailUrl
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
                                    guitarViewModel.toggleFavourite(favourite, context)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.heart_minus),
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