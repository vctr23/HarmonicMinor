package com.example.harmonicminor.Screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.harmonicminor.NavItem
import com.example.harmonicminor.navScreens.Favourite
import com.example.harmonicminor.navScreens.Home
import com.example.harmonicminor.navScreens.Menu
import com.example.harmonicminor.navScreens.Search
import com.example.harmonicminor.navScreens.ShoppingCart

@Composable
fun Main(modifier: Modifier = Modifier) {

    val navItemList = listOf(
        NavItem(Icons.Default.Home),
        NavItem(Icons.Default.FavoriteBorder),
        NavItem(Icons.Default.Search),
        NavItem(Icons.Default.ShoppingCart),
        NavItem(Icons.Default.Menu)
    )

    var selected by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = {
                            selected = index
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = "Icon")
                        }
                    )
                }
            }
        }

    ) {  innerPadding ->
        ContentScreen(modifier = modifier.padding(innerPadding), selected)
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selected: Int) {
    when (selected) {
        0 -> Home()
        1 -> Favourite()
        2 -> Search()
        3 -> ShoppingCart()
        4 -> Menu()
    }
}