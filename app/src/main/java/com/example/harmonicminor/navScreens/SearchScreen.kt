package com.example.harmonicminor.navScreens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.secondaryTextColor
import com.example.harmonicminor.ui.theme.textColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(modifier: Modifier = Modifier) {
    var textInput by remember { mutableStateOf("") }
    var state by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = backgroundColor)
        ) {
            SearchBar(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                colors = SearchBarDefaults.colors(
                    containerColor = backgroundAccentColor,
                    inputFieldColors = TextFieldDefaults.colors(
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor,
                        focusedPlaceholderColor = secondaryTextColor,
                        unfocusedPlaceholderColor = secondaryTextColor
                    )
                ),
                query = textInput,
                onQueryChange = {
                    textInput = it
                }, onSearch = {
                    state = false
                }, active = state,
                onActiveChange = {
                    state = it
                },
                placeholder = {
                    Text(text = "Buscar")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = iconColor,
                        contentDescription = "Search icon"
                        )
                },
                trailingIcon = {
                    if (state){
                        Icon(
                            modifier = Modifier.clickable {
                                if (textInput.isNotEmpty()) {
                                    textInput = ""
                                } else{
                                    state = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            tint = iconColor,
                            contentDescription = "Close icon"
                        )
                    }
                }
            )
            {

            }
        }
    }
}