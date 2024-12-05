package com.example.harmonicminor.navScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.harmonicminor.R
import com.example.harmonicminor.contextLocale.LocalLanguageManager
import com.example.harmonicminor.contextLocale.updateLocale
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.borderColor
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor


@Composable
fun Menu(modifier: Modifier = Modifier) {
    val languageManager = LocalLanguageManager.current
    val context = LocalContext.current
    val locale = languageManager.currentLocale.value

    SideEffect {
        context.updateLocale(locale)
    }

    Scaffold(){ innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(backgroundColor),
        ) {
            Spacer(modifier = Modifier.padding(vertical = 30.dp))
            Profile()
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            Text(
                stringResource(R.string.shop_config),
                color = textColor,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(horizontal = 16.dp),
                fontSize = 16.sp,

            )
            CountrySelectionItem()
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
            LanguageSelectionItem()
        }
    }
}

@Composable
fun Profile(){
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(110.dp)
                .fillMaxWidth()
                .border(border = BorderStroke(2.dp, borderColor),
                    shape = RoundedCornerShape(20.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Image(
                painter = painterResource(R.drawable.profile_picture),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                Text(text = "Nombre de usuario",
                    color = textColor,
                    fontSize = 14.sp)
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Text(text = "Correo del usuario",
                    color = textColor,
                    fontSize = 14.sp)
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectionItem(){
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val customIcon = ImageVector.vectorResource(R.drawable.translate)
    val languageManager = LocalLanguageManager.current
    val currentLanguage = languageManager.currentLocale.value.language

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                showBottomSheet = true
            }
            .border(BorderStroke(2.dp, borderColor),
                shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp))
        Icon(customIcon, contentDescription = null, tint = iconColor)
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Text(text = stringResource(R.string.language), color = textColor, modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.app_language),
            color = textColor
        )
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = iconColor)
        if (showBottomSheet){
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                modifier = Modifier.fillMaxSize(),
                containerColor = backgroundAccentColor
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = stringResource(R.string.available_languages), color = textColor)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = currentLanguage == "en",
                            onCheckedChange = {
                                if (it) languageManager.updateLanguage("en")
                            }
                        )
                        Text(
                            text = "English  (en)",
                            color = textColor,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = currentLanguage == "es",
                            onCheckedChange = {
                                if (it) languageManager.updateLanguage("es")
                            }
                        )
                        Text(
                            text = "Español  (es)",
                            color = textColor,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelectionItem(){
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val customIcon = ImageVector.vectorResource(R.drawable.language)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                showBottomSheet = true
            }
            .border(BorderStroke(2.dp, borderColor),
                shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp))
        Icon(customIcon, contentDescription = null, tint = iconColor)
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Text(
            text = stringResource(R.string.country),
            color = textColor,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = stringResource(R.string.app_country),
            color = textColor
        )
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = iconColor
        )
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                modifier = Modifier.fillMaxSize(),
                containerColor = backgroundAccentColor
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = stringResource(R.string.available_languages), color = textColor)
                    Row(){

                    }
                }
            }
        }
    }
}