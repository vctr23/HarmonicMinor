package com.example.harmonicminor.navScreens.menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.secondaryTextColor
import com.example.harmonicminor.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAQScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.frequently_asked_questions)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(backgroundColor),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundAccentColor)
            ) {
                val faqs = listOf(
                    R.string.faq_1 to R.string.answer_1,
                    R.string.faq_2 to R.string.answer_2,
                    R.string.faq_3 to R.string.answer_3,
                    R.string.faq_4 to R.string.answer_4,
                    R.string.faq_5 to R.string.answer_5,
                    R.string.faq_6 to R.string.answer_6,
                    R.string.faq_7 to R.string.answer_7,
                    R.string.faq_8 to R.string.answer_8
                )
                LazyColumn(
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(faqs) { (questionResId, answerResId) ->
                        var isExpanded by remember { mutableStateOf(false) }
                        Column(
                            modifier = Modifier
                                .clickable { isExpanded = !isExpanded }
                                .padding(vertical = 8.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(vertical = 8.dp)
                            ) {
                                Text(
                                    text = stringResource(id = questionResId),
                                    color = textColor,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                                Spacer(modifier = Modifier.weight(1f))

                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.keyboard_arrow_down),
                                    contentDescription = null,
                                    tint = textColor
                                )
                            }
                            AnimatedVisibility(visible = isExpanded) {
                                Text(
                                    text = stringResource(id = answerResId),
                                    color = secondaryTextColor,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(
                                        start = 16.dp,
                                        top = 8.dp,
                                        bottom = 8.dp
                                    )
                                )
                            }
                            HorizontalDivider(thickness = 0.5.dp, color = secondaryTextColor)
                        }
                    }
                }
            }
        }
    }
}