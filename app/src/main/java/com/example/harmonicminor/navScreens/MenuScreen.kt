package com.example.harmonicminor.navScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.harmonicminor.R
import com.example.harmonicminor.contextLocale.LocalLanguageManager
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor


@Composable
fun Menu(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(vertical = 85.dp, horizontal = 16.dp),
    ){
        Text("Configuración de la tienda:",
            color = textColor,
            modifier = Modifier.padding(vertical = 8.dp),
            fontSize = 16.sp
        )
        LanguageSelectionItem()
    }
}

@Composable
fun Profile(){
    // Implement the Profile screen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectionItem(){
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val customIcon = ImageVector.vectorResource(R.drawable.language)
    val languageManager = LocalLanguageManager.current
    val currentLanguage = languageManager.currentLocale.value.language


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundAccentColor)
            .padding(vertical = 8.dp)
            .clickable {
                showBottomSheet = true
            },
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(customIcon, contentDescription = null, tint = iconColor)
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Text(text = "Lenguaje", color = textColor, modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.app_language),
            color = textColor
        )

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