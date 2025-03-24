package com.example.harmonicminor.navScreens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.harmonicminor.R
import com.example.harmonicminor.navScreens.home.bass.BassThumbnail
import com.example.harmonicminor.navScreens.home.dj.DjThumbnail
import com.example.harmonicminor.navScreens.home.drums.DrumsThumbnail
import com.example.harmonicminor.navScreens.home.guitar.GuitarThumbnail
import com.example.harmonicminor.navScreens.home.microphones.MicrophoneThumbnail
import com.example.harmonicminor.navScreens.home.piano.PianoThumbnail
import com.example.harmonicminor.navScreens.home.software.SoftwareThumbnail
import com.example.harmonicminor.navScreens.home.wind.WindThumbnail
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.secondaryTextColor
import com.example.harmonicminor.ui.theme.textColor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            contentPadding = PaddingValues(bottom = 10.dp)
        ) {
            item {
                if (userId != null) {
                    HeaderSection(userId)
                }
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                CategoriesSection(navController)
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                BestSellersSection(navController)
                HorizontalDivider(
                    thickness = 1.dp,
                    color = secondaryTextColor,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                RecentlyAddedSection(navController)
                HorizontalDivider(
                    thickness = 1.dp,
                    color = secondaryTextColor,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun HeaderSection(userId: String) {
    val username = rememberSaveable { mutableStateOf<String?>(null) }

    LaunchedEffect(userId) {
        getUsername(userId) { gottenUsername ->
            username.value = gottenUsername
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.hello) + " " + (username.value
                ?: stringResource(R.string.default_username)),
            color = textColor,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(R.drawable.sentiment_very_satisfied),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(32.dp)
        )
    }
}

@Composable
fun CategoriesSection(navController: NavController) {
    val isExpanded = remember { mutableStateOf(false) }

    val categories = listOf(
        Category(
            stringResource(R.string.guitars),
            R.drawable.category_guitar,
            onClick = {
                navController.navigate(Routes.GuitarScreen) {
                    launchSingleTop = true
                }
            }),
        Category(
            stringResource(R.string.bass),
            R.drawable.category_bass,
            onClick = {
                navController.navigate(Routes.BassScreen) {
                    launchSingleTop = true
                }
            }
        ),
        Category(
            stringResource(R.string.piano),
            R.drawable.category_piano,
            onClick = {
                navController.navigate(Routes.PianoScreen) {
                    launchSingleTop = true
                }
            }
        ),
        Category(
            stringResource(R.string.drums),
            R.drawable.category_drums,
            onClick = {
                navController.navigate(Routes.DrumsScreen) {
                    launchSingleTop = true
                }
            }
        ),
        Category(
            stringResource(R.string.wind),
            R.drawable.category_wind,
            onClick = {
                navController.navigate(Routes.WindScreen) {
                    launchSingleTop = true
                }
            }
        ),
        Category(
            stringResource(R.string.dj),
            R.drawable.category_dj,
            onClick = {
                navController.navigate(Routes.DjScreen) {
                    launchSingleTop = true
                }
            }
        ),
        Category(
            stringResource(R.string.microphones),
            R.drawable.category_microfones,
            onClick = {
                navController.navigate(Routes.MicrophonesScreen) {
                    launchSingleTop = true
                }
            }
        ),
        Category(
            stringResource(R.string.software),
            R.drawable.category_software,
            onClick = {
                navController.navigate(Routes.SoftwareScreen) {
                    launchSingleTop = true
                }
            }
        ),
    )

    Column {
        Text(
            text = stringResource(R.string.categories),
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isExpanded.value) 770.dp else 300.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
                content = {
                    items(categories) { category ->
                        CategoryItem(category = category)
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 80.dp)
                .height(36.dp)
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
                    isExpanded.value = !isExpanded.value
                },
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.category_button),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.padding(horizontal = 16.dp))
                Text(
                    text = if (isExpanded.value) stringResource(R.string.show_less) else stringResource(
                        R.string.show_all
                    ),
                    color = textColor
                )
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .background(backgroundAccentColor, shape = RoundedCornerShape(8.dp))
            .clickable { category.onClick() },
        contentAlignment = Alignment.Center

    ) {
        Image(
            painter = painterResource(category.imageRes),
            contentDescription = category.name,
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category.name,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BestSellersSection(navController: NavController) {
    val guitar = GuitarThumbnail(
        name = stringResource(R.string.guitar4_name),
        type = stringResource(R.string.guitar4_type),
        price = stringResource(R.string.guitar4_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=195lxxsSHsMV8bdjlJc9oNqHfmOFv_YD8"
    )
    val bass = BassThumbnail(
        name = stringResource(R.string.bass2_name),
        type = stringResource(R.string.bass2_type),
        price = stringResource(R.string.bass2_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=1l0nG_CS-kmHXzV0Lak7ifSoHHpzFzvfD"
    )
    val piano = PianoThumbnail(
        name = stringResource(R.string.piano3_name),
        type = stringResource(R.string.piano3_type),
        price = stringResource(R.string.piano3_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=1OdCEII4-wVUX023rhmLV16nzUgVKZoA1"
    )
    val drums = DrumsThumbnail(
        name = stringResource(R.string.drums2_name),
        type = stringResource(R.string.drums2_type),
        price = stringResource(R.string.drums2_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=1_psB6r59B4wNxHeha7yqCNFJmVfBFWfH"
    )
    val dj = DjThumbnail(
        name = stringResource(R.string.dj1_name),
        type = stringResource(R.string.dj1_type),
        price = stringResource(R.string.dj1_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=15oHtjjhbNKgGa8ErXQipQM5REerrw0Lm"
    )
    val wind = WindThumbnail(
        name = stringResource(R.string.wind2_name),
        type = stringResource(R.string.wind2_type),
        price = stringResource(R.string.wind2_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=13wFFyCV28OScjx_MB_ckYoJsNnj-pTwe"
    )
    val software = SoftwareThumbnail(
        name = stringResource(R.string.software1_name),
        type = stringResource(R.string.software1_type),
        price = stringResource(R.string.software1_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=1y2fxtOYB043NMVdRGfx8EkjJgkivIdqx"
    )
    val mic = MicrophoneThumbnail(
        name = stringResource(R.string.mic3_name),
        type = stringResource(R.string.mic3_type),
        price = stringResource(R.string.mic3_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=1PyJvnVUlovC4oXd0jcSrys4JE47TiYSt"
    )
    val instrumentList = listOf(guitar, bass, piano, drums, dj, wind, software, mic)

    Column {
        Text(
            text = stringResource(R.string.most_sold),
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(instrumentList) { instrument ->
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(100.dp)
                        .clickable {
                            when (instrument) {
                                is GuitarThumbnail -> {
                                    navController.navigate("${Routes.GuitarDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is BassThumbnail -> {
                                    navController.navigate("${Routes.BassDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is PianoThumbnail -> {
                                    navController.navigate("${Routes.PianoDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is DrumsThumbnail -> {
                                    navController.navigate("${Routes.DrumsDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is DjThumbnail -> {
                                    navController.navigate("${Routes.DjDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is WindThumbnail -> {
                                    navController.navigate("${Routes.WindDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is MicrophoneThumbnail -> {
                                    navController.navigate("${Routes.MicrophoneDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is SoftwareThumbnail -> {
                                    navController.navigate("${Routes.SoftwareDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = instrument.thumbNailUrl,
                        contentDescription = instrument.name,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(backgroundAccentColor),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = instrument.name,
                        color = textColor,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun RecentlyAddedSection(navController: NavController) {
    val guitar = GuitarThumbnail(
        name = stringResource(R.string.guitar12_name),
        type = stringResource(R.string.guitar12_type),
        price = stringResource(R.string.guitar12_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=1R-RxUGz3vsy4Fp49xG2mX8RU4nwhGRi2"
    )
    val bass = BassThumbnail(
        name = stringResource(R.string.bass6_name),
        type = stringResource(R.string.bass6_type),
        price = stringResource(R.string.bass6_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=1k9_ZwW1YcO0FjUsj-9SVOM-C5kG1aVCU"
    )
    val piano = PianoThumbnail(
        name = stringResource(R.string.piano5_name),
        type = stringResource(R.string.piano5_type),
        price = stringResource(R.string.piano5_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=1wnswO0rvvAOCOkvm6rpK1utshZfu2oSA"
    )
    val drums = DrumsThumbnail(
        name = stringResource(R.string.drums5_name),
        type = stringResource(R.string.drums5_type),
        price = stringResource(R.string.drums5_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=16MhkJLZ6bruWN6-q0U0ianV5oTaPrKZq"
    )
    val dj = DjThumbnail(
        name = stringResource(R.string.dj5_name),
        type = stringResource(R.string.dj5_type),
        price = stringResource(R.string.dj5_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=10JM0T8f1ptjrvagQUBOjysHfP2r5byy8"
    )
    val wind = WindThumbnail(
        name = stringResource(R.string.wind5_name),
        type = stringResource(R.string.wind5_type),
        price = stringResource(R.string.wind5_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=1QShAl6CN1nL1sq6SXsXBABW5B3DaNVQs"
    )
    val software = SoftwareThumbnail(
        name = stringResource(R.string.software5_name),
        type = stringResource(R.string.software5_type),
        price = stringResource(R.string.software5_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=1uf5B7dlGjTwTB8UxcZkqk96XgilnWyL1"
    )
    val mic = MicrophoneThumbnail(
        name = stringResource(R.string.mic4_name),
        type = stringResource(R.string.mic4_type),
        price = stringResource(R.string.mic4_price),
        isAvailable = true,
        thumbNailUrl = "https://drive.google.com/uc?id=1u3UWsqq1rBF48INL5gKG8j5tlfau3UWf"
    )
    val instrumentList = listOf(guitar, bass, piano, drums, dj, wind, software, mic)

    Column {
        Text(
            text = stringResource(R.string.recently_added),
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(instrumentList) { instrument ->
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(100.dp)
                        .clickable {
                            when (instrument) {
                                is GuitarThumbnail -> {
                                    navController.navigate("${Routes.GuitarDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is BassThumbnail -> {
                                    navController.navigate("${Routes.BassDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is PianoThumbnail -> {
                                    navController.navigate("${Routes.PianoDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is DrumsThumbnail -> {
                                    navController.navigate("${Routes.DrumsDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is DjThumbnail -> {
                                    navController.navigate("${Routes.DjDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is WindThumbnail -> {
                                    navController.navigate("${Routes.WindDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is MicrophoneThumbnail -> {
                                    navController.navigate("${Routes.MicrophoneDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                                is SoftwareThumbnail -> {
                                    navController.navigate("${Routes.SoftwareDetailScreen}/${instrument.name}") {
                                        launchSingleTop = true
                                    }
                                }

                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = instrument.thumbNailUrl,
                        contentDescription = instrument.name,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(backgroundAccentColor),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = instrument.name,
                        color = textColor,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

fun getUsername(userId: String, onResult: (String?) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("users").document(userId)
        .get()
        .addOnSuccessListener { document ->
            if (document != null && document.exists()) {
                val username = document.getString("username")
                onResult(username)
            } else {
                onResult(null)
            }
        }
        .addOnFailureListener {
            onResult(null)
        }
}