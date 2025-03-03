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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.navScreens.home.bass.Bass
import com.example.harmonicminor.navScreens.home.bass.BassSectionItem
import com.example.harmonicminor.navScreens.home.bass.BassThumbnail
import com.example.harmonicminor.navScreens.home.dj.Dj
import com.example.harmonicminor.navScreens.home.dj.DjSectionItem
import com.example.harmonicminor.navScreens.home.dj.DjThumbnail
import com.example.harmonicminor.navScreens.home.drums.Drums
import com.example.harmonicminor.navScreens.home.drums.DrumsSectionItem
import com.example.harmonicminor.navScreens.home.drums.DrumsThumbnail
import com.example.harmonicminor.navScreens.home.guitar.Guitar
import com.example.harmonicminor.navScreens.home.guitar.GuitarSectionItem
import com.example.harmonicminor.navScreens.home.guitar.GuitarThumbnail
import com.example.harmonicminor.navScreens.home.microphones.Microphone
import com.example.harmonicminor.navScreens.home.microphones.MicrophoneSectionItem
import com.example.harmonicminor.navScreens.home.microphones.MicrophoneThumbnail
import com.example.harmonicminor.navScreens.home.piano.Piano
import com.example.harmonicminor.navScreens.home.piano.PianoSectionItem
import com.example.harmonicminor.navScreens.home.piano.PianoThumbnail
import com.example.harmonicminor.navScreens.home.software.Software
import com.example.harmonicminor.navScreens.home.software.SoftwareSectionItem
import com.example.harmonicminor.navScreens.home.software.SoftwareThumbnail
import com.example.harmonicminor.navScreens.home.wind.Wind
import com.example.harmonicminor.navScreens.home.wind.WindSectionItem
import com.example.harmonicminor.navScreens.home.wind.WindThumbnail
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavouriteScreen(
    navController: NavController,
    favouriteViewModel: FavouriteViewModel = viewModel()
) {
    val favourites by favouriteViewModel.favourites.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        favouriteViewModel.loadFavourites(FirebaseAuth.getInstance().currentUser?.uid ?: "")
    }

    Scaffold {
        if (favourites.isEmpty()) {
            EmptyFavouritesScreen()
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
                items(favourites) { favourite ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        when (favourite) {
                            is Guitar -> GuitarSectionItem(
                                guitarThumbnail = GuitarThumbnail(
                                    name = favourite.name,
                                    type = favourite.type,
                                    price = favourite.price,
                                    isAvailable = favourite.isAvailable,
                                    thumbNailUrl = favourite.thumbnailUrl
                                ),
                                navController = navController
                            )

                            is Bass -> BassSectionItem(
                                bassThumbnail = BassThumbnail(
                                    name = favourite.name,
                                    type = favourite.type,
                                    price = favourite.price,
                                    isAvailable = favourite.isAvailable,
                                    thumbNailUrl = favourite.thumbnailUrl
                                ),
                                navController = navController
                            )

                            is Drums -> DrumsSectionItem(
                                drumsThumbnail = DrumsThumbnail(
                                    name = favourite.name,
                                    type = favourite.type,
                                    price = favourite.price,
                                    isAvailable = favourite.isAvailable,
                                    thumbNailUrl = favourite.thumbnailUrl
                                ),
                                navController = navController
                            )

                            is Dj -> DjSectionItem(
                                djThumbnail = DjThumbnail(
                                    name = favourite.name,
                                    type = favourite.type,
                                    price = favourite.price,
                                    isAvailable = favourite.isAvailable,
                                    thumbNailUrl = favourite.thumbnailUrl
                                ),
                                navController = navController
                            )

                            is Piano -> PianoSectionItem(
                                pianoThumbnail = PianoThumbnail(
                                    name = favourite.name,
                                    type = favourite.type,
                                    price = favourite.price,
                                    isAvailable = favourite.isAvailable,
                                    thumbNailUrl = favourite.thumbnailUrl
                                ),
                                navController = navController
                            )

                            is Software -> SoftwareSectionItem(
                                softwareThumbnail = SoftwareThumbnail(
                                    name = favourite.name,
                                    type = favourite.type,
                                    price = favourite.price,
                                    isAvailable = favourite.isAvailable,
                                    thumbNailUrl = favourite.thumbnailUrl
                                ),
                                navController = navController
                            )

                            is Wind -> WindSectionItem(
                                windThumbnail = WindThumbnail(
                                    name = favourite.name,
                                    type = favourite.type,
                                    price = favourite.price,
                                    isAvailable = favourite.isAvailable,
                                    thumbNailUrl = favourite.thumbnailUrl
                                ),
                                navController = navController
                            )

                            is Microphone -> MicrophoneSectionItem(
                                microphoneThumbnail = MicrophoneThumbnail(
                                    name = favourite.name,
                                    type = favourite.type,
                                    price = favourite.price,
                                    isAvailable = favourite.isAvailable,
                                    thumbNailUrl = favourite.thumbnailUrl
                                ),
                                navController = navController
                            )
                        }
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
                                    favouriteViewModel.toggleFavourite(favourite, context)
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

@Composable
fun EmptyFavouritesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.favourites_empty),
            fontSize = 24.sp,
            color = textColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = stringResource(R.string.favourites_empty_message),
            fontSize = 16.sp,
            color = textColor,
            modifier = Modifier.padding(vertical = 8.dp),
            textAlign = TextAlign.Center
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
}