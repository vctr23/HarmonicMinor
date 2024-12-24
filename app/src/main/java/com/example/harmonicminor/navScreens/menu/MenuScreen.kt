package com.example.harmonicminor.navScreens.menu

import android.app.LocaleManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.borderColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.secondaryTextColor
import com.example.harmonicminor.ui.theme.textColor
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Menu(
    navController: NavController,
    auth: FirebaseAuth,
    viewModel: MenuViewModel = viewModel()
) {
    val context = LocalContext.current
    val selectedLanguage by viewModel.selectedLanguage.collectAsState()
    val languageChange: (String) -> Unit = { newLanguage ->
        if (selectedLanguage != newLanguage) {
            viewModel.updateLanguage(newLanguage)
        }
    }

    LaunchedEffect(selectedLanguage) {
        context
            .getSystemService(LocaleManager::class.java)
            .applicationLocales = android.os.LocaleList(
            Locale(selectedLanguage.lowercase(), selectedLanguage.uppercase())
        )
    }

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(backgroundColor),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                Spacer(modifier = Modifier.padding(vertical = 30.dp))
                Profile()
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
            }
            item {
                Text(
                    stringResource(R.string.shop_config),
                    color = textColor,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(horizontal = 16.dp),
                    fontSize = 18.sp,
                )
            }
            item {
                CountrySelectionItem(viewModel)
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
            }
            item {
                LanguageSelectionItem(
                    selectedLanguage = selectedLanguage,
                    onLanguageChange = languageChange
                )
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
            }
            item {
                CurrencySelectionItem(viewModel)
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
            }
            item {
                Text(
                    stringResource(R.string.help_config),
                    color = textColor,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(horizontal = 16.dp),
                    fontSize = 18.sp,
                )
            }
            item {
                FAQItem(onClick = { navController.navigate(Routes.FAQScreen) })
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
            }
            item {
                TermsItem(onClick = { navController.navigate(Routes.TermsScreen) })
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
            }
            item {
                Text(
                    text = stringResource(R.string.communication_config),
                    color = textColor,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(horizontal = 16.dp),
                    fontSize = 18.sp,
                )
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
            }
            item {
                FeedbackItem(onClick = { navController.navigate(Routes.FeedbackScreen) })
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 50.dp))
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 80.dp)
                        .height(48.dp)
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
                            auth.signOut()
                            navController.navigate(Routes.InitialScreen) {
                                launchSingleTop = true
                                navController.popBackStack()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.sign_out),
                        color = textColor
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
            }
            item {
                Text(
                    text = stringResource(R.string.app_version),
                    color = secondaryTextColor,
                    fontSize = 20.sp,
                    style = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
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
fun LanguageSelectionItem(selectedLanguage: String, onLanguageChange: (String) -> Unit) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val customIcon = ImageVector.vectorResource(R.drawable.translate)

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
                                    onLanguageChange("en")
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
                                    onLanguageChange("es")
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
fun CountrySelectionItem(viewModel: MenuViewModel) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val selectedCountry by viewModel.selectedCountry.collectAsState()
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
                                        viewModel.updateCountry(country)
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
fun CurrencySelectionItem(viewModel: MenuViewModel) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val selectedCurrency by viewModel.selectedCurrency.collectAsState()
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
                                    viewModel.updateCurrency(coin)
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
    ) {
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
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                modifier = Modifier.fillMaxSize(),
                containerColor = backgroundAccentColor
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
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

@Composable
fun TermsItem(onClick: () -> Unit) {
    val customIcon = ImageVector.vectorResource(R.drawable.book_2)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() }
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
            text = stringResource(R.string.terms_conditions),
            color = textColor,
            modifier = Modifier.weight(1f)
        )
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = iconColor
        )
    }
}

@Composable
fun FAQItem(onClick: () -> Unit) {
    val customIcon = ImageVector.vectorResource(R.drawable.help)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() }
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
            text = stringResource(R.string.faq),
            color = textColor,
            modifier = Modifier.weight(1f)
        )
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = iconColor
        )
    }
}

@Composable
fun FeedbackItem(onClick: () -> Unit) {
    val customIcon = ImageVector.vectorResource(R.drawable.reviews)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() }
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
            text = stringResource(R.string.feedback),
            color = textColor,
            modifier = Modifier.weight(1f)
        )
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = iconColor
        )
    }
}
