package com.example.harmonicminor.navScreens.home.microphones

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
fun MicrophonesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.microphones))
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
        MicrophoneSection(innerPadding, navController)
    }
}

@Composable
fun MicrophoneSection(innerPadding: PaddingValues, navController: NavController) {
    val microphones = listOf(
        MicrophoneThumbnail(
            name = stringResource(R.string.mic1_name),
            type = stringResource(R.string.mic1_type),
            price = stringResource(R.string.mic1_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=16vw5IpXDmn03vwGPTpz7dnWiGDUhdSQY"
        ),
        MicrophoneThumbnail(
            name = stringResource(R.string.mic2_name),
            type = stringResource(R.string.mic2_type),
            price = stringResource(R.string.mic2_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1cfIxTOIr5USKBsj5DM6dNRPaK06vwatt"
        ),
        MicrophoneThumbnail(
            name = stringResource(R.string.mic3_name),
            type = stringResource(R.string.mic3_type),
            price = stringResource(R.string.mic3_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1PyJvnVUlovC4oXd0jcSrys4JE47TiYSt"
        ),
        MicrophoneThumbnail(
            name = stringResource(R.string.mic4_name),
            type = stringResource(R.string.mic4_type),
            price = stringResource(R.string.mic4_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1u3UWsqq1rBF48INL5gKG8j5tlfau3UWf"
        )
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        items(microphones) { micro ->
            DjSectionItem(microphoneThumbnail = micro, navController)
        }
    }
}

@Composable
fun DjSectionItem(microphoneThumbnail: MicrophoneThumbnail, navController: NavController) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .height(101.dp)
            .padding(vertical = 2.dp)
            .shadow(16.dp, shape = RoundedCornerShape(12.dp))
            .background(backgroundAccentColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navController.navigate("${Routes.DjDetailScreen}/${microphoneThumbnail.name}")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(microphoneThumbnail.thumbNailUrl),
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
                    microphoneThumbnail.name,
                    color = textColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = microphoneThumbnail.type,
                    color = textColor,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = if (microphoneThumbnail.isAvailable) stringResource(R.string.available) else stringResource(
                        R.string.not_available
                    ),
                    color = if (microphoneThumbnail.isAvailable) stockColor else notOnStockColor,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = microphoneThumbnail.price,
                    color = textColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}