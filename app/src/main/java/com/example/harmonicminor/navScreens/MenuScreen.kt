package com.example.harmonicminor.navScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.harmonicminor.R
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor

@Composable
fun Menu(modifier: Modifier = Modifier) {

    val customIcons = object {
        val country = ImageVector.vectorResource(R.drawable.language)
        val language = ImageVector.vectorResource(R.drawable.translate)
        val currency = ImageVector.vectorResource(R.drawable.paid)
    }

    val settingItems = listOf(
        Triple(customIcons.country, "País", "España"),
        Triple(customIcons.language, "Lenguaje", "Español"),
        Triple(customIcons.currency, "Moneda", "EUR")
    )

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor),
    ){ innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = backgroundColor)
                .padding(horizontal = 16.dp, vertical = 55.dp),
        ){
            item{
                LanguageSelectionItem()
            }
            item{
                Text(
                    text = "Configurar la tienda",
                    color = textColor,
                    fontSize = 20.sp
                )
            }
            items(settingItems) { (icon, label, value) ->
                SettingItem(icon = icon, label = label, value = value)
                if (label != "Moneda") {  // Don't add divider after the last item
                    HorizontalDivider(
                        color = Color.Gray,
                        thickness = 0.5.dp,
                    )
                }
            }
        }
    }
}

@Composable
fun Profile(){
    // Implement the Profile screen
}


@Composable
fun SettingItem(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(color = backgroundAccentColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            color = textColor,
            modifier = Modifier.weight(1f)
        )
        Text(
            modifier = Modifier.clickable {

            },
            text = value,
            color = Color.Gray
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectionItem(){
    // TODO
}