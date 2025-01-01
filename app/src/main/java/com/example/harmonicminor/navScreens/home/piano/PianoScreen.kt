package com.example.harmonicminor.navScreens.home.piano

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.harmonicminor.R
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.notOnStockColor
import com.example.harmonicminor.ui.theme.stockColor
import com.example.harmonicminor.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PianoScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.piano))
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
        PianoSection(innerPadding, navController)
    }
}

@Composable
fun PianoSection(innerPadding: PaddingValues, navController: NavController) {
    val pianos = listOf(
        PianoThumbnail(
            name = stringResource(R.string.piano1_name),
            type = stringResource(R.string.piano1_type),
            price = stringResource(R.string.piano1_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1w_JjUkn0xA8rw4kYiQxDMB_qoObkz1P0"
        ),
        PianoThumbnail(
            name = stringResource(R.string.piano2_name),
            type = stringResource(R.string.piano2_type),
            price = stringResource(R.string.piano2_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1zyQiVzVnG6Mgavyb6Oahwrc1gJ3uUyfJ"
        ),
        PianoThumbnail(
            name = stringResource(R.string.piano3_name),
            type = stringResource(R.string.piano3_type),
            price = stringResource(R.string.piano3_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1OdCEII4-wVUX023rhmLV16nzUgVKZoA1"
        ),
        PianoThumbnail(
            name = stringResource(R.string.piano4_name),
            type = stringResource(R.string.piano4_type),
            price = stringResource(R.string.piano4_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1ABNqZ8XVQihIR9e1WaQNYGeBP78-tQke"
        ),
        PianoThumbnail(
            name = stringResource(R.string.piano5_name),
            type = stringResource(R.string.piano5_type),
            price = stringResource(R.string.piano5_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1wnswO0rvvAOCOkvm6rpK1utshZfu2oSA"
        )
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        items(pianos) { piano ->
            PianoSectionItem(pianoThumbnail = piano, navController)
        }
    }
}

@Composable
fun PianoSectionItem(pianoThumbnail: PianoThumbnail, navController: NavController) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .height(101.dp)
            .padding(vertical = 2.dp)
            .shadow(16.dp, shape = RoundedCornerShape(12.dp))
            .background(backgroundAccentColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navController.navigate("${Routes.PianoDetailScreen}/${pianoThumbnail.name}")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(pianoThumbnail.thumbNailUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text =
                    pianoThumbnail.name,
                    color = textColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = pianoThumbnail.type,
                    color = textColor,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = if (pianoThumbnail.isAvailable) stringResource(R.string.available) else stringResource(
                        R.string.not_available
                    ),
                    color = if (pianoThumbnail.isAvailable) stockColor else notOnStockColor,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = pianoThumbnail.price,
                    color = textColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}