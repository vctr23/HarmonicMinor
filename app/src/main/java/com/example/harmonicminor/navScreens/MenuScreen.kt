package com.example.harmonicminor.navScreens

import android.app.LocaleManager
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.harmonicminor.R
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.borderColor
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.secondaryTextColor
import com.example.harmonicminor.ui.theme.textColor


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Menu(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
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
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
            CurrencySelectionItem()
        }
    }
}

@Composable
fun Profile() {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(110.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, borderColor),
                shape = RoundedCornerShape(20.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
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
        ) {
            Text(
                text = "Nombre de usuario",
                color = textColor,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = "Correo del usuario",
                color = textColor,
                fontSize = 14.sp
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectionItem() {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val customIcon = ImageVector.vectorResource(R.drawable.translate)
    var selectedLanguage by rememberSaveable { mutableStateOf("es") }

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                showBottomSheet = true
            }
            .border(
                BorderStroke(2.dp, borderColor),
                shape = RoundedCornerShape(10.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp))
        Icon(customIcon, contentDescription = null, tint = iconColor)
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Text(
            text = stringResource(R.string.language),
            color = textColor,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = stringResource(R.string.app_language),
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
                    Text(
                        text = stringResource(R.string.available_languages),
                        color = textColor,
                        fontSize = 16.sp
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = selectedLanguage == "en",
                            onCheckedChange = {
                                if (it) {
                                    selectedLanguage = "en"
                                    context
                                        .getSystemService(LocaleManager::class.java)
                                        .applicationLocales =
                                        android.os.LocaleList(
                                            java.util.Locale(
                                                "en".lowercase(),
                                                "en".uppercase()
                                            )
                                        )
                                }
                            }
                        )
                        Text(
                            text = "English  (en)",
                            color = textColor,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    HorizontalDivider(thickness = 0.5.dp, color = secondaryTextColor)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Checkbox(
                            checked = selectedLanguage == "es",
                            onCheckedChange = {
                                if (it) {
                                    selectedLanguage = "es"
                                    context
                                        .getSystemService(LocaleManager::class.java)
                                        .applicationLocales =
                                        android.os.LocaleList(
                                            java.util.Locale(
                                                "es".lowercase(),
                                                "es".uppercase()
                                            )
                                        )
                                }
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
fun CountrySelectionItem() {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var selectedCountry by rememberSaveable { mutableStateOf("") }
    val customIcon = ImageVector.vectorResource(R.drawable.language)

    val countries = listOf(
        stringResource(R.string.spain),
        stringResource(R.string.france),
        stringResource(R.string.portugal),
        stringResource(R.string.germany),
        stringResource(R.string.austria),
        stringResource(R.string.luxembourg),
        stringResource(R.string.italy),
        stringResource(R.string.belgium),
        stringResource(R.string.czech_republic),
        stringResource(R.string.bulgaria),
        stringResource(R.string.croatia),
        stringResource(R.string.denmark),
        stringResource(R.string.finland),
        stringResource(R.string.switzerland),
        stringResource(R.string.sweden),
        stringResource(R.string.greece),
        stringResource(R.string.united_kingdom),
        stringResource(R.string.romania),
        stringResource(R.string.russia),
        stringResource(R.string.ukraine),
        stringResource(R.string.serbia),
        stringResource(R.string.poland),
        stringResource(R.string.netherlands),
        stringResource(R.string.ireland),
        stringResource(R.string.slovakia),
        stringResource(R.string.slovenia),
        stringResource(R.string.estonia),
        stringResource(R.string.hungary),
        stringResource(R.string.albania),
        stringResource(R.string.belarus)
    ).sortedBy { it }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                showBottomSheet = true
            }
            .border(
                BorderStroke(2.dp, borderColor),
                shape = RoundedCornerShape(10.dp)
            ),
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
            text = selectedCountry,
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
                LazyColumn(
                    modifier = Modifier.padding(8.dp)
                ) {
                    item {
                        Text(
                            text = stringResource(R.string.available_countries),
                            color = textColor,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.padding(vertical = 3.dp))
                        for (country in countries) {
                            HorizontalDivider(thickness = 0.5.dp, color = secondaryTextColor)
                            Row(
                                modifier = Modifier.padding(1.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = country == selectedCountry,
                                    onCheckedChange = {
                                        selectedCountry = country
                                    }
                                )
                                Text(
                                    text = country,
                                    color = textColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencySelectionItem() {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var selectedCurrency by rememberSaveable { mutableStateOf("") }
    val customIcon = ImageVector.vectorResource(R.drawable.paid)

    val coins = listOf(
        stringResource(R.string.euro),
        stringResource(R.string.pound),
        stringResource(R.string.dolar),
        stringResource(R.string.franc),
        stringResource(R.string.krone),
        stringResource(R.string.krona)
    ).sortedBy { it }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                showBottomSheet = true
            }
            .border(
                BorderStroke(2.dp, borderColor),
                shape = RoundedCornerShape(10.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp))
        Icon(customIcon, contentDescription = null, tint = iconColor)
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Text(
            text = stringResource(R.string.currency),
            color = textColor,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = selectedCurrency,
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
                    Text(
                        text = stringResource(R.string.available_currencies),
                        color = textColor,
                        fontSize = 16.sp
                    )
                    for (coin in coins) {
                        HorizontalDivider(thickness = 0.5.dp, color = secondaryTextColor)
                        Row(
                            modifier = Modifier.padding(1.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = coin == selectedCurrency,
                                onCheckedChange = {
                                    selectedCurrency = coin
                                }
                            )
                            Text(
                                text = coin,
                                color = textColor
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdressItem(modifier: Modifier = Modifier) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val customIcon = ImageVector.vectorResource(R.drawable.location_on)
    var id by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(
                BorderStroke(2.dp, borderColor),
                shape = RoundedCornerShape(10.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp))
        Icon(customIcon, contentDescription = null, tint = iconColor)
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Text(
            text = stringResource(R.string.address),
            color = textColor,
            modifier = Modifier.weight(1f)
        )
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = iconColor
        )
        if (showBottomSheet){
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                modifier = Modifier.fillMaxSize(),
                containerColor = backgroundAccentColor
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ){
                    OutlinedTextField(value = id,
                        onValueChange = {
                            id = it
                        }, label = {
                            Text(text = "", color = Color.White)
                        },
                        modifier = Modifier.width(280.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = textColor,
                            unfocusedTextColor = textColor
                        ),
                        singleLine = true,
                        maxLines = 1
                    )
                }
            }
        }
    }
}