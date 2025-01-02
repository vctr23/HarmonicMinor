package com.example.harmonicminor.navScreens.home.wind

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
fun WindScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.wind))
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
        WindSection(innerPadding, navController)
    }
}

@Composable
fun WindSection(innerPadding: PaddingValues, navController: NavController) {
    val winds = listOf(
        WindThumbnail(
            name = stringResource(R.string.wind1_name),
            type = stringResource(R.string.wind1_type),
            price = stringResource(R.string.wind1_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1ywbLeqat7ogHPP092fVNnOG4sHgYD3Q0"
        ),
        WindThumbnail(
            name = stringResource(R.string.wind2_name),
            type = stringResource(R.string.wind2_type),
            price = stringResource(R.string.wind2_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=13wFFyCV28OScjx_MB_ckYoJsNnj-pTwe"
        ),
        WindThumbnail(
            name = stringResource(R.string.wind3_name),
            type = stringResource(R.string.wind3_type),
            price = stringResource(R.string.wind3_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=12uWfpqs-REXK3CrtbkTsietXMdY80ps7"
        ),
        WindThumbnail(
            name = stringResource(R.string.wind4_name),
            type = stringResource(R.string.wind4_type),
            price = stringResource(R.string.wind4_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1h_D5Palt0ApPa6OcUiBOYyVZW9Z2R4cB"
        ),
        WindThumbnail(
            name = stringResource(R.string.wind5_name),
            type = stringResource(R.string.wind5_type),
            price = stringResource(R.string.wind5_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1QShAl6CN1nL1sq6SXsXBABW5B3DaNVQs"
        )
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        items(winds) { wind ->
            WindSectionItem(windThumbnail = wind, navController)
        }
    }
}

@Composable
fun WindSectionItem(windThumbnail: WindThumbnail, navController: NavController) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .height(101.dp)
            .padding(vertical = 2.dp)
            .shadow(16.dp, shape = RoundedCornerShape(12.dp))
            .background(backgroundAccentColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navController.navigate("${Routes.WindDetailScreen}/${windThumbnail.name}")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(windThumbnail.thumbNailUrl),
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
                    windThumbnail.name,
                    color = textColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = windThumbnail.type,
                    color = textColor,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = if (windThumbnail.isAvailable) stringResource(R.string.available) else stringResource(
                        R.string.not_available
                    ),
                    color = if (windThumbnail.isAvailable) stockColor else notOnStockColor,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = windThumbnail.price,
                    color = textColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}