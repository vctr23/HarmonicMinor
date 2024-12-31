package com.example.harmonicminor.navScreens.home.guitar

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
fun GuitarScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.guitars))
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
        GuitarSection(innerPadding, navController)
    }
}


@Composable
fun GuitarSection(innerPadding: PaddingValues, navController: NavController) {
    val guitars = listOf(
        GuitarThumbnail(
            name = stringResource(R.string.guitar1_name),
            type = stringResource(R.string.guitar1_type),
            price = stringResource(R.string.guitar1_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1PEr3VJJL39aCpaLQKAubioIUnt6HXFE-"
        ),
        GuitarThumbnail(
            name = stringResource(R.string.guitar2_name),
            type = stringResource(R.string.guitar2_type),
            price = stringResource(R.string.guitar2_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1MSUudHlpnyxLj1riirGvjGYrGQBRRs9A"
        ),
        GuitarThumbnail(
            name = stringResource(R.string.guitar3_name),
            type = stringResource(R.string.guitar3_type),
            price = stringResource(R.string.guitar3_price),
            isAvailable = false,
            thumbNailUrl = "https://drive.google.com/uc?id=1GDBiWNy8F-R2C6YO9zwdu3N0ltAfzmnT"
        ),
        GuitarThumbnail(
            name = stringResource(R.string.guitar4_name),
            type = stringResource(R.string.guitar4_type),
            price = stringResource(R.string.guitar4_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=195lxxsSHsMV8bdjlJc9oNqHfmOFv_YD8"
        ),
        GuitarThumbnail(
            name = stringResource(R.string.guitar5_name),
            type = stringResource(R.string.guitar5_type),
            price = stringResource(R.string.guitar5_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=17tqQmo2fPCOqVoj387_N3WfREEpojpCD"
        ),
        GuitarThumbnail(
            name = stringResource(R.string.guitar6_name),
            type = stringResource(R.string.guitar6_type),
            price = stringResource(R.string.guitar6_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1xGNdfOCJKFDXEj08YHWMeguXh_nDgtLK"
        ),
        GuitarThumbnail(
            name = stringResource(R.string.guitar7_name),
            type = stringResource(R.string.guitar7_type),
            price = stringResource(R.string.guitar7_price),
            isAvailable = false,
            thumbNailUrl = "https://drive.google.com/uc?id=1MNjtsTmQsFT_YDS3Z9nG0fca1xNkNLCz"
        ),
        GuitarThumbnail(
            name = stringResource(R.string.guitar8_name),
            type = stringResource(R.string.guitar8_type),
            price = stringResource(R.string.guitar8_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1PjpKAbUoJvt8MLIyG0a2dV85qMNgNd5D"
        ),
        GuitarThumbnail(
            name = stringResource(R.string.guitar9_name),
            type = stringResource(R.string.guitar9_type),
            price = stringResource(R.string.guitar9_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1UGQT2KymfTN30u9P1l2fokn-CjSTFgi1"
        ),
        GuitarThumbnail(
            name = stringResource(R.string.guitar10_name),
            type = stringResource(R.string.guitar10_type),
            price = stringResource(R.string.guitar10_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1cskp2icSNFKCxEbEipzkCFE5lwbnQvL4"
        ),
        GuitarThumbnail(
            name = stringResource(R.string.guitar11_name),
            type = stringResource(R.string.guitar11_type),
            price = stringResource(R.string.guitar11_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1OOAwKZkumJheWcxkwrYMm660aQb2XiU0"
        ),
        GuitarThumbnail(
            name = stringResource(R.string.guitar12_name),
            type = stringResource(R.string.guitar12_type),
            price = stringResource(R.string.guitar12_price),
            isAvailable = true,
            thumbNailUrl = "https://drive.google.com/uc?id=1R-RxUGz3vsy4Fp49xG2mX8RU4nwhGRi2"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        items(guitars) { guitar ->
            GuitarSectionItem(guitarThumbnail = guitar, navController)
        }
    }
}

@Composable
fun GuitarSectionItem(guitarThumbnail: GuitarThumbnail, navController: NavController) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .height(101.dp)
            .padding(vertical = 2.dp)
            .shadow(16.dp, shape = RoundedCornerShape(12.dp))
            .background(backgroundAccentColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navController.navigate("${Routes.GuitarDetailScreen}/${guitarThumbnail.name}"){
                    launchSingleTop = true
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(guitarThumbnail.thumbNailUrl),
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
                    text = guitarThumbnail.name,
                    color = textColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = guitarThumbnail.type,
                    color = textColor,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = if (guitarThumbnail.isAvailable) stringResource(R.string.available) else stringResource(
                        R.string.not_available
                    ),
                    color = if (guitarThumbnail.isAvailable) stockColor else notOnStockColor,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = guitarThumbnail.price,
                    color = textColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}
