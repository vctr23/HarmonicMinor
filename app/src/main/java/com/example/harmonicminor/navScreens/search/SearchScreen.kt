package com.example.harmonicminor.navScreens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.navScreens.home.Category
import com.example.harmonicminor.navScreens.home.bass.Bass
import com.example.harmonicminor.navScreens.home.dj.Dj
import com.example.harmonicminor.navScreens.home.drums.Drums
import com.example.harmonicminor.navScreens.home.guitar.Guitar
import com.example.harmonicminor.navScreens.home.microphones.Microphone
import com.example.harmonicminor.navScreens.home.piano.Piano
import com.example.harmonicminor.navScreens.home.software.Software
import com.example.harmonicminor.navScreens.home.wind.Wind
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundAccentColor
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.iconColor
import com.example.harmonicminor.ui.theme.secondaryTextColor
import com.example.harmonicminor.ui.theme.textColor
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController) {
    var query by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<Searchable>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(query) {
        if (query.isNotEmpty()) {
            isLoading = true
            try {
                Log.d("SearchScreen", "Buscando con consulta: $query")
                searchResults = getSearchResults(query)
                Log.d("SearchScreen", "Resultados: ${searchResults.size}")
            } catch (e: Exception) {
                Log.d("SearchScreen", "Error: ${e.message}")
                searchResults = emptyList()
            } finally {
                isLoading = false
            }
        } else {
            searchResults = emptyList()
        }
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SearchBar(
                query = query,
                onQueryChange = { query = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SearchResultsList(results = searchResults, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    var state by remember { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = SearchBarDefaults.colors(
            containerColor = backgroundAccentColor,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                focusedPlaceholderColor = secondaryTextColor,
                unfocusedPlaceholderColor = secondaryTextColor
            )
        ),
        query = query,
        onQueryChange = {
            onQueryChange(it)
        }, onSearch = {
            state = false
        },
        active = state,
        onActiveChange = {
            state = it
        },
        placeholder = {
            Text(text = stringResource(R.string.search))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = iconColor,
                contentDescription = "Search icon"
            )
        },
        trailingIcon = {
            if (state) {
                Icon(
                    modifier = Modifier.clickable {
                        if (query.isNotEmpty()) {
                            onQueryChange("")
                        } else {
                            state = false
                        }
                    },
                    imageVector = Icons.Default.Close,
                    tint = iconColor,
                    contentDescription = "Close icon"
                )
            }
        }
    ) {}
}

@Composable
fun SearchResultsList(results: List<Searchable>, navController: NavController) {
    if (results.isEmpty()) {
        Text(text = stringResource(R.string.nothing_found), color = secondaryTextColor)

        val categories = listOf(
            Category(
                stringResource(R.string.guitars),
                R.drawable.category_guitar,
                onClick = {
                    navController.navigate(Routes.GuitarScreen) {
                        launchSingleTop = true
                    }
                }),
            Category(
                stringResource(R.string.bass),
                R.drawable.category_bass,
                onClick = {
                    navController.navigate(Routes.BassScreen) {
                        launchSingleTop = true
                    }
                }
            ),
            Category(
                stringResource(R.string.piano),
                R.drawable.category_piano,
                onClick = {
                    navController.navigate(Routes.PianoScreen) {
                        launchSingleTop = true
                    }
                }
            ),
            Category(
                stringResource(R.string.drums),
                R.drawable.category_drums,
                onClick = {
                    navController.navigate(Routes.DrumsScreen) {
                        launchSingleTop = true
                    }
                }
            ),
            Category(
                stringResource(R.string.wind),
                R.drawable.category_wind,
                onClick = {
                    navController.navigate(Routes.WindScreen) {
                        launchSingleTop = true
                    }
                }
            ),
            Category(
                stringResource(R.string.dj),
                R.drawable.category_dj,
                onClick = {
                    navController.navigate(Routes.DjScreen) {
                        launchSingleTop = true
                    }
                }
            ),
            Category(
                stringResource(R.string.microphones),
                R.drawable.category_microfones,
                onClick = {
                    navController.navigate(Routes.MicrophonesScreen) {
                        launchSingleTop = true
                    }
                }
            ),
            Category(
                stringResource(R.string.software),
                R.drawable.category_software,
                onClick = {
                    navController.navigate(Routes.SoftwareScreen) {
                        launchSingleTop = true
                    }
                }
            ),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            categories.forEach { category ->
                CategoryRowItem(category = category)
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(results) { item ->
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = item.name,
                        color = textColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    when (item) {
                        is Guitar -> Text("Name: ${item.name}", color = secondaryTextColor)
                        is Bass -> Text("Name: ${item.name}", color = secondaryTextColor)
                        is Software -> Text("Name: ${item.name}", color = secondaryTextColor)
                        is Drums -> Text("Name: ${item.name}", color = secondaryTextColor)
                        is Dj -> Text("Name: ${item.name}", color = secondaryTextColor)
                        is Piano -> Text("Name: ${item.name}", color = secondaryTextColor)
                        is Microphone -> Text("Name: ${item.name}", color = secondaryTextColor)
                        is Wind -> Text("Name: ${item.name}", color = secondaryTextColor)
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryRowItem(category: Category) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { category.onClick() }
            .padding(vertical = 8.dp)
            .background(backgroundAccentColor, shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(category.imageRes),
            contentDescription = category.name,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = category.name,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

suspend fun <T : Searchable> getItemsFromCollection(
    collectionName: String,
    classType: Class<T>
): List<T> {
    val db = FirebaseFirestore.getInstance()
    val results = mutableListOf<T>()

    return suspendCoroutine { continuation ->
        db.collection(collectionName)
            .orderBy("name")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val item = document.toObject(classType)
                    results.add(item)
                }
                continuation.resume(results)
            }
            .addOnFailureListener { exception ->
                continuation.resumeWithException(exception)
            }
    }
}

suspend fun getSearchResults(query: String): List<Searchable> {
    val guitars = getItemsFromCollection("guitars", Guitar::class.java)
    val basses = getItemsFromCollection("basses", Bass::class.java)
    val djs = getItemsFromCollection("djs", Dj::class.java)
    val softwares = getItemsFromCollection("softwares", Software::class.java)
    val drums = getItemsFromCollection("drums", Drums::class.java)
    val pianos = getItemsFromCollection("pianos", Piano::class.java)
    val mics = getItemsFromCollection("microphones", Microphone::class.java)
    val wind = getItemsFromCollection("winds", Wind::class.java)

    val allItems = guitars + basses + djs + softwares + drums + pianos + mics + wind
    return allItems.filter { it.name.contains(query, ignoreCase = true) }
}
