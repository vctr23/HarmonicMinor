package com.example.harmonicminor.navScreens.home.dj

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
fun DjScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.dj))
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
        DjSection(innerPadding, navController)
    }
}

@Composable
fun DjSection(innerPadding: PaddingValues, navController: NavController) {
    val djs = listOf(
        DjThumbnail(
            name = stringResource(R.string.dj1_name),
            type = stringResource(R.string.dj1_type),
            price = stringResource(R.string.dj1_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=15oHtjjhbNKgGa8ErXQipQM5REerrw0Lm"
        ),
        DjThumbnail(
            name = stringResource(R.string.dj2_name),
            type = stringResource(R.string.dj2_type),
            price = stringResource(R.string.dj2_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1lkR8wWsJI7o7oMfEYleFPqnGpyk40LiN"
        ),
        DjThumbnail(
            name = stringResource(R.string.dj3_name),
            type = stringResource(R.string.dj3_type),
            price = stringResource(R.string.dj3_price),
            isAvailable = false,
            thumbNailUrl = "https://drive.google.com/uc?id=1eTxE3c5bO5rRI_s3zdjzE13PUfW87fbA"
        ),
        DjThumbnail(
            name = stringResource(R.string.dj4_name),
            type = stringResource(R.string.dj4_type),
            price = stringResource(R.string.dj4_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1aS4NqvT8086umemX9Ypwt8qU3grW0Tl3"
        ),
        DjThumbnail(
            name = stringResource(R.string.dj5_name),
            type = stringResource(R.string.dj5_type),
            price = stringResource(R.string.dj5_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=10JM0T8f1ptjrvagQUBOjysHfP2r5byy8"
        )
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        items(djs) { dj ->
            DjSectionItem(djThumbnail = dj, navController)
        }
    }
}

@Composable
fun DjSectionItem(djThumbnail: DjThumbnail, navController: NavController) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .height(101.dp)
            .padding(vertical = 2.dp)
            .shadow(16.dp, shape = RoundedCornerShape(12.dp))
            .background(backgroundAccentColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navController.navigate("${Routes.DjDetailScreen}/${djThumbnail.name}")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(djThumbnail.thumbNailUrl),
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
                    djThumbnail.name,
                    color = textColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = djThumbnail.type,
                    color = textColor,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = if (djThumbnail.isAvailable) stringResource(R.string.available) else stringResource(
                        R.string.not_available
                    ),
                    color = if (djThumbnail.isAvailable) stockColor else notOnStockColor,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = djThumbnail.price,
                    color = textColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}

//