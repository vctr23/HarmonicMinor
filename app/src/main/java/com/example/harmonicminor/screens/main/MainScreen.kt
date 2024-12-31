package com.example.harmonicminor.screens.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.navScreens.favourite.FavouriteScreen
import com.example.harmonicminor.navScreens.home.HomeScreen
import com.example.harmonicminor.navScreens.menu.MenuScreen
import com.example.harmonicminor.navScreens.search.SearchScreen
import com.example.harmonicminor.navScreens.shopping.ShoppingCartScreen
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.darkAccentColor
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor
import com.google.firebase.auth.FirebaseAuth

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main(navController: NavController, modifier: Modifier = Modifier, auth: FirebaseAuth) {
    var selected by rememberSaveable { mutableIntStateOf(0) }
    val navItemList = listOf(
        NavItem(Icons.Default.Home),
        NavItem(Icons.Default.FavoriteBorder),
        NavItem(Icons.Default.Search),
        NavItem(Icons.Default.ShoppingCart),
        NavItem(Icons.Default.Menu)
    )

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = { Topbar() },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.height(60.dp),
                containerColor = backgroundAccentColor,
            ) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = {
                            selected = index
                        },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = "Icon",
                                tint = iconColor
                            )
                        },
                        colors = NavigationBarItemColors(
                            selectedIndicatorColor = darkAccentColor,
                            unselectedIconColor = iconColor,
                            selectedIconColor = iconColor,
                            selectedTextColor = textColor,
                            unselectedTextColor = textColor,
                            disabledIconColor = iconColor,
                            disabledTextColor = iconColor,
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(navController, innerPadding, selected, auth)
    }
}

@SuppressLint("NewApi")
@Composable
fun ContentScreen(
    navController: NavController,
    modifier: PaddingValues,
    selected: Int,
    auth: FirebaseAuth
) {
    Box(modifier = Modifier.padding(modifier)) {
        when (selected) {
            0 -> HomeScreen(navController)
            1 -> FavouriteScreen(navController)
            2 -> SearchScreen()
            3 -> ShoppingCartScreen(navController)
            4 -> MenuScreen(navController, auth)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = TopAppBarColors(
            containerColor = backgroundAccentColor,
            scrolledContainerColor = darkAccentColor,
            navigationIconContentColor = iconColor,
            titleContentColor = textColor,
            actionIconContentColor = iconColor
        ),
        title = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 28.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(150.dp)
                )
            }
        },
        navigationIcon = {},
        actions = {}
    )
}