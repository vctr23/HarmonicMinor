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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.secondaryTextColor
import com.example.harmonicminor.ui.theme.textColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            contentPadding = PaddingValues(bottom = 10.dp)
        ) {
            item {
                HeaderSection()
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                CategoriesSection(navController)
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                BestSellersSection()
                HorizontalDivider(
                    thickness = 1.dp,
                    color = secondaryTextColor,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                RecentlySeenSection()
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
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.hello) + "", // Query para sacar el username
            color = textColor,
            fontSize = 24.sp
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
            onClick = { navController.navigate(Routes.GuitarScreen)}),
        Category(
            stringResource(R.string.bass),
            R.drawable.category_bass,
            onClick = { navController.navigate(Routes.BassScreen) }
        ),
        Category(
            stringResource(R.string.piano),
            R.drawable.category_piano,
            onClick = { navController.navigate(Routes.PianoScreen) }
        ),
        Category(
            stringResource(R.string.drums),
            R.drawable.category_drums,
            onClick = { navController.navigate(Routes.DrumsScreen) }
        ),
        Category(
            stringResource(R.string.wind),
            R.drawable.category_wind,
            onClick = { navController.navigate(Routes.WindScreen) }
        ),
        Category(
            stringResource(R.string.dj),
            R.drawable.category_dj,
            onClick = { navController.navigate(Routes.DjScreen) }
        ),
        Category(
            stringResource(R.string.microphones),
            R.drawable.category_microfones,
            onClick = { navController.navigate(Routes.MicrophonesScreen) }
        ),
        Category(
            stringResource(R.string.software),
            R.drawable.category_software,
            onClick = { navController.navigate(Routes.SoftwareScreen) }
        ),
    )

    Column {
        Text(
            text = stringResource(R.string.categories),
            color = textColor,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isExpanded.value) 650.dp else 300.dp)
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
fun BestSellersSection() {
    Column {
        Text(
            text = stringResource(R.string.most_sold),
            color = textColor,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(8) {
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 80.dp)
                        .padding(horizontal = 8.dp)
                        .background(backgroundAccentColor, shape = RoundedCornerShape(8.dp))
                        .clickable { /* Acceder al producto */ }
                ) {
                    Text(text = "Product", color = textColor)
                }
            }

        }
    }
}

@Composable
fun RecentlySeenSection() {
    Column {
        Text(
            text = stringResource(R.string.recently_seen),
            color = textColor,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(8) {
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 80.dp)
                        .padding(horizontal = 8.dp)
                        .background(backgroundAccentColor, shape = RoundedCornerShape(8.dp))
                        .clickable { /* Acceder al producto */ }
                ) {
                    Text(text = "Product", color = textColor)
                }
            }

        }
    }
}