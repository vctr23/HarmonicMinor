package com.example.harmonicminor.navScreens.favourite

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.harmonicminor.R
import com.example.harmonicminor.navScreens.home.bass.Bass
import com.example.harmonicminor.navScreens.home.dj.Dj
import com.example.harmonicminor.navScreens.home.drums.Drums
import com.example.harmonicminor.navScreens.home.guitar.Guitar
import com.example.harmonicminor.navScreens.home.microphones.Microphone
import com.example.harmonicminor.navScreens.home.piano.Piano
import com.example.harmonicminor.navScreens.home.software.Software
import com.example.harmonicminor.navScreens.home.wind.Wind
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavouriteViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    private val _favourites = MutableStateFlow<List<FavouriteItem>>(emptyList())
    val favourites: StateFlow<List<FavouriteItem>> get() = _favourites

    fun loadFavourites(userId: String) {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                val favoriteIds =
                    document.get("favorites") as? Map<String, List<String>> ?: emptyMap()
                readFavourites(favoriteIds)
            }
            .addOnFailureListener { e ->
                Log.e("FavouriteViewModel", "Error loading favourites: ${e.message}")
            }
    }

    private fun readFavourites(favoriteIds: Map<String, List<String>>) {
        val favouritesList = mutableListOf<FavouriteItem>()

        favoriteIds["guitars"]?.forEach { id ->
            db.collection("guitars").document(id).get()
                .addOnSuccessListener { guitarDoc ->
                    val guitar = guitarDoc.toObject(Guitar::class.java)
                    guitar?.let { favouritesList.add(it) }
                    _favourites.value = favouritesList.toList()
                }
        }
        favoriteIds["basses"]?.forEach { id ->
            db.collection("basses").document(id).get()
                .addOnSuccessListener { bassDoc ->
                    val bass = bassDoc.toObject(Bass::class.java)
                    bass?.let { favouritesList.add(it) }
                    _favourites.value = favouritesList.toList()
                }
        }
        favoriteIds["drums"]?.forEach { id ->
            db.collection("drums").document(id).get()
                .addOnSuccessListener { drumsDoc ->
                    val drums = drumsDoc.toObject(Drums::class.java)
                    drums?.let { favouritesList.add(it) }
                    _favourites.value = favouritesList.toList()
                }
        }
        favoriteIds["djs"]?.forEach { id ->
            db.collection("djs").document(id).get()
                .addOnSuccessListener { djDoc ->
                    val djs = djDoc.toObject(Dj::class.java)
                    djs?.let { favouritesList.add(it) }
                    _favourites.value = favouritesList.toList()
                }
        }
        favoriteIds["winds"]?.forEach { id ->
            db.collection("winds").document(id).get()
                .addOnSuccessListener { windDoc ->
                    val wind = windDoc.toObject(Wind::class.java)
                    wind?.let { favouritesList.add(it) }
                    _favourites.value = favouritesList.toList()
                }
        }
        favoriteIds["pianos"]?.forEach { id ->
            db.collection("pianos").document(id).get()
                .addOnSuccessListener { pianoDoc ->
                    val piano = pianoDoc.toObject(Piano::class.java)
                    piano?.let { favouritesList.add(it) }
                    _favourites.value = favouritesList.toList()
                }
        }
        favoriteIds["softwares"]?.forEach { id ->
            db.collection("softwares").document(id).get()
                .addOnSuccessListener { softwareDoc ->
                    val software = softwareDoc.toObject(Software::class.java)
                    software?.let { favouritesList.add(it) }
                    _favourites.value = favouritesList.toList()
                }
        }
        favoriteIds["microphones"]?.forEach { id ->
            db.collection("microphones").document(id).get()
                .addOnSuccessListener { microphoneDoc ->
                    val mic = microphoneDoc.toObject(Microphone::class.java)
                    mic?.let { favouritesList.add(it) }
                    _favourites.value = favouritesList.toList()
                }
        }
    }

    fun toggleFavourite(item: FavouriteItem, context: Context) {
        currentUserId?.let { userId ->
            val userDoc = db.collection("users").document(userId)
            val collectionKey = when (item) {
                is Guitar -> "guitars"
                is Bass -> "basses"
                is Drums -> "drums"
                is Dj -> "djs"
                is Wind -> "winds"
                is Piano -> "pianos"
                is Software -> "softwares"
                is Microphone -> "microphones"
                else -> return
            }

            val updatedFavourites = _favourites.value.toMutableList()

            if (_favourites.value.any { it.id == item.id }) {
                updatedFavourites.removeIf { it.id == item.id }
                _favourites.value = updatedFavourites

                userDoc.update("favorites.$collectionKey", FieldValue.arrayRemove(item.id))
                    .addOnSuccessListener {
                        Toast.makeText(
                            context,
                            context.getText(R.string.removed_favourite),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                updatedFavourites.add(item)
                _favourites.value = updatedFavourites

                userDoc.update("favorites.$collectionKey", FieldValue.arrayUnion(item.id))
                    .addOnSuccessListener {
                        Toast.makeText(
                            context,
                            context.getString(R.string.added_favourite),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }

}
