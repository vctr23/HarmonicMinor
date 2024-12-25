package com.example.harmonicminor.navScreens.menu

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Terms(navController: NavController) {
    var accepted by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    val termsList = listOf(
        stringResource(R.string.terms_intro_line_1),
        stringResource(R.string.terms_intro_line_2),
        stringResource(R.string.terms_intro_line_3),
        stringResource(R.string.terms_general_1),
        stringResource(R.string.terms_general_2),
        stringResource(R.string.terms_general_3),
        stringResource(R.string.terms_general_4),
        stringResource(R.string.terms_products_1),
        stringResource(R.string.terms_products_2),
        stringResource(R.string.terms_products_3),
        stringResource(R.string.terms_pricing_1),
        stringResource(R.string.terms_pricing_2),
        stringResource(R.string.terms_pricing_3),
        stringResource(R.string.terms_pricing_4),
        stringResource(R.string.terms_shipping_1),
        stringResource(R.string.terms_shipping_2),
        stringResource(R.string.terms_shipping_3),
        stringResource(R.string.terms_shipping_4),
        stringResource(R.string.terms_shipping_5),
        stringResource(R.string.terms_shipping_6),
        stringResource(R.string.terms_returns_1),
        stringResource(R.string.terms_returns_2),
        stringResource(R.string.terms_returns_3),
        stringResource(R.string.terms_returns_4),
        stringResource(R.string.terms_returns_5),
        stringResource(R.string.terms_returns_6),
        stringResource(R.string.terms_warranty_1),
        stringResource(R.string.terms_warranty_2),
        stringResource(R.string.terms_warranty_3),
        stringResource(R.string.terms_ip_1),
        stringResource(R.string.terms_ip_2),
        stringResource(R.string.terms_ip_3),
        stringResource(R.string.terms_liability_1),
        stringResource(R.string.terms_liability_2),
        stringResource(R.string.terms_liability_3),
        stringResource(R.string.terms_law_1),
        stringResource(R.string.terms_law_2),
        stringResource(R.string.terms_acceptance_1),
        stringResource(R.string.terms_acceptance_2)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.terms_conditions)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = iconColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = textColor,
                    navigationIconContentColor = iconColor
                )
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundAccentColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    items(termsList) { term ->
                        Text(
                            text = term,
                            color = textColor,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = accepted,
                        onCheckedChange = { accepted = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = buttonColor1
                        )
                    )
                    Text(
                        text = stringResource(R.string.accept_terms),
                        color = textColor,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 80.dp)
                        .height(38.dp)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(buttonColor1, buttonColor2)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            if (accepted) {
                                navController.popBackStack()
                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        context.getString(R.string.terms_not_accepted),
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.done),
                        color = textColor,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


