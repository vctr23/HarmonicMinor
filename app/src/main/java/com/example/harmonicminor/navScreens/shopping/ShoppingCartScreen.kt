package com.example.harmonicminor.navScreens.shopping

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.navScreens.home.bass.Bass
import com.example.harmonicminor.navScreens.home.bass.BassSectionItem
import com.example.harmonicminor.navScreens.home.bass.BassThumbnail
import com.example.harmonicminor.navScreens.home.dj.Dj
import com.example.harmonicminor.navScreens.home.dj.DjSectionItem
import com.example.harmonicminor.navScreens.home.dj.DjThumbnail
import com.example.harmonicminor.navScreens.home.drums.Drums
import com.example.harmonicminor.navScreens.home.drums.DrumsSectionItem
import com.example.harmonicminor.navScreens.home.drums.DrumsThumbnail
import com.example.harmonicminor.navScreens.home.guitar.Guitar
import com.example.harmonicminor.navScreens.home.guitar.GuitarSectionItem
import com.example.harmonicminor.navScreens.home.guitar.GuitarThumbnail
import com.example.harmonicminor.navScreens.home.microphones.Microphone
import com.example.harmonicminor.navScreens.home.microphones.MicrophoneSectionItem
import com.example.harmonicminor.navScreens.home.microphones.MicrophoneThumbnail
import com.example.harmonicminor.navScreens.home.piano.Piano
import com.example.harmonicminor.navScreens.home.piano.PianoSectionItem
import com.example.harmonicminor.navScreens.home.piano.PianoThumbnail
import com.example.harmonicminor.navScreens.home.software.Software
import com.example.harmonicminor.navScreens.home.software.SoftwareSectionItem
import com.example.harmonicminor.navScreens.home.software.SoftwareThumbnail
import com.example.harmonicminor.navScreens.home.wind.Wind
import com.example.harmonicminor.navScreens.home.wind.WindSectionItem
import com.example.harmonicminor.navScreens.home.wind.WindThumbnail
import com.example.harmonicminor.navScreens.menu.address.Address
import com.example.harmonicminor.navScreens.menu.address.getAddressData
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShoppingCartScreen(navController: NavController, shoppingCartViewModel: ShoppingCartViewModel = viewModel()) {

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    var address by remember { mutableStateOf<Address?>(null) }
    val totalPrice = shoppingCartViewModel.cartItems.collectAsState().value.sumOf {
        it.price.replace("[^0-9]".toRegex(), "").toIntOrNull() ?: 0
    }
    val cartItems by shoppingCartViewModel.cartItems.collectAsState()

    LaunchedEffect(Unit) {
        shoppingCartViewModel.loadCart(FirebaseAuth.getInstance().currentUser?.uid ?: "")

        val userId = auth.currentUser?.uid
        if (userId != null) {
            getAddressData(
                userId = userId,
                context = context,
                onSuccess = { loadedAddress ->
                    address = loadedAddress
                },
                onFailure = { errorMessage ->
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    Scaffold {
        if (cartItems.isEmpty()) {
            EmptyCartScreen()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = stringResource(R.string.cart),
                        fontSize = 20.sp,
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
                items(cartItems) { item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        when (item) {
                            is Guitar -> {
                                GuitarSectionItem(
                                    guitarThumbnail = GuitarThumbnail(
                                        name = item.name,
                                        type = item.type,
                                        price = item.price,
                                        isAvailable = item.isAvailable,
                                        thumbNailUrl = item.thumbnailUrl
                                    ),
                                    navController
                                )
                            }
                            is Bass -> {
                                BassSectionItem(
                                    bassThumbnail = BassThumbnail(
                                        name = item.name,
                                        type = item.type,
                                        price = item.price,
                                        isAvailable = item.isAvailable,
                                        thumbNailUrl = item.thumbnailUrl
                                    ),
                                    navController
                                )
                            }

                            is Drums -> DrumsSectionItem(
                                drumsThumbnail = DrumsThumbnail(
                                    name = item.name,
                                    type = item.type,
                                    price = item.price,
                                    isAvailable = item.isAvailable,
                                    thumbNailUrl = item.thumbnailUrl
                                ),
                                navController = navController
                            )

                            is Dj -> DjSectionItem(
                                djThumbnail = DjThumbnail(
                                    name = item.name,
                                    type = item.type,
                                    price = item.price,
                                    isAvailable = item.isAvailable,
                                    thumbNailUrl = item.thumbnailUrl
                                ),
                                navController = navController
                            )

                            is Piano -> PianoSectionItem(
                                pianoThumbnail = PianoThumbnail(
                                    name = item.name,
                                    type = item.type,
                                    price = item.price,
                                    isAvailable = item.isAvailable,
                                    thumbNailUrl = item.thumbnailUrl
                                ),
                                navController = navController
                            )

                            is Software -> SoftwareSectionItem(
                                softwareThumbnail = SoftwareThumbnail(
                                    name = item.name,
                                    type = item.type,
                                    price = item.price,
                                    isAvailable = item.isAvailable,
                                    thumbNailUrl = item.thumbnailUrl
                                ),
                                navController = navController
                            )

                            is Wind -> WindSectionItem(
                                windThumbnail = WindThumbnail(
                                    name = item.name,
                                    type = item.type,
                                    price = item.price,
                                    isAvailable = item.isAvailable,
                                    thumbNailUrl = item.thumbnailUrl
                                ),
                                navController = navController
                            )

                            is Microphone -> MicrophoneSectionItem(
                                microphoneThumbnail = MicrophoneThumbnail(
                                    name = item.name,
                                    type = item.type,
                                    price = item.price,
                                    isAvailable = item.isAvailable,
                                    thumbNailUrl = item.thumbnailUrl
                                ),
                                navController = navController
                            )
                        }
                        Box(
                            modifier = Modifier
                                .height(42.dp)
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                                .padding(bottom = 8.dp)
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(buttonColor1, buttonColor2)
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    shoppingCartViewModel.toggleCart(item, context)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.remove_shopping_cart),
                                contentDescription = null,
                                modifier = Modifier.size(28.dp),
                                tint = iconColor
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(
                                backgroundAccentColor,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        address?.let { addr ->
                            Column(
                                modifier = Modifier.padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(R.string.shipping_address),
                                    color = textColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                                if (addr.id.isNotEmpty()) {
                                    Text(
                                        text = stringResource(R.string.id) + ": " + addr.id,
                                        color = textColor,
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    )
                                }
                                Text(
                                    text = stringResource(R.string.name) + ": "
                                            + "${addr.name} ${addr.lastname}",
                                    color = textColor,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                                Text(
                                    text = stringResource(R.string.street) + ": "
                                            + addr.street,
                                    color = textColor,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                                Text(
                                    text = stringResource(R.string.locality) + ": "
                                            + "${addr.postcode} ${addr.locality}",
                                    color = textColor,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                                Text(
                                    text = stringResource(R.string.country) + ": "
                                            + addr.country,
                                    color = textColor,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                                if (addr.phone.isNotEmpty()) {
                                    Text(
                                        text = stringResource(R.string.phone) + ": "
                                                + addr.phone,
                                        color = textColor,
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    )
                                }
                            }
                        } ?: run {
                            Text(
                                text = stringResource(R.string.loading),
                                color = textColor
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(
                                backgroundAccentColor,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(R.string.total_price) + " " + totalPrice.toString() + "€",
                                fontSize = 18.sp,
                                color = textColor,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Box(
                                modifier = Modifier
                                    .height(42.dp)
                                    .width(150.dp)
                                    .padding(vertical = 4.dp)
                                    .padding(bottom = 8.dp)
                                    .background(
                                        Brush.linearGradient(
                                            colors = listOf(buttonColor1, buttonColor2)
                                        ),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .clickable(enabled = address != null) {
                                        val userId = auth.currentUser?.uid
                                        if (userId != null) {
                                            shoppingCartViewModel.clearCart(
                                                userId = userId,
                                                onSuccess = {
                                                    navController.navigate(Routes.PaymentScreen) {
                                                        launchSingleTop = true
                                                    }
                                                },
                                                onFailure = { errorMessage ->
                                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                                }
                                            )
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.add_card),
                                        contentDescription = null,
                                        modifier = Modifier.size(28.dp)
                                    )

                                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                                    Text(
                                        text = stringResource(R.string.pay),
                                        color = textColor,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyCartScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.cart_empty),
            fontSize = 24.sp,
            color = textColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = stringResource(R.string.cart_empty_message),
            fontSize = 16.sp,
            color = textColor,
            modifier = Modifier.padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(R.drawable.cart_empty),
            contentDescription = null,
            modifier = Modifier
                .size(500.dp)
                .padding(vertical = 20.dp),
            contentScale = ContentScale.Crop
        )
    }
}