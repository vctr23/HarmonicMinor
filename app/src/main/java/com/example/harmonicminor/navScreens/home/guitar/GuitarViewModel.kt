package com.example.harmonicminor.navScreens.home.guitar

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonicminor.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GuitarViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    private val _guitars = mutableStateListOf<Guitar>()
    val guitars: List<Guitar> get() = _guitars

    private val _favourites = mutableStateOf<List<Guitar>>(emptyList())
    val favourites: State<List<Guitar>> get() = _favourites

    private val _cart = mutableStateOf<List<Guitar>>(emptyList())
    val cart: State<List<Guitar>> get() = _cart


    fun readGuitars() {
        viewModelScope.launch {
            try {
                val result = db.collection("guitars").get().await()
                _guitars.clear()
                for (document in result) {
                    val guitar = document.toObject(Guitar::class.java)
                    _guitars.add(guitar)
                }
            } catch (e: Exception) {
                Log.e("GuitarViewModel", "Error getting documents: $e")
            }
        }
    }

    fun loadFavourites(userId: String) {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                val favoriteIds = document.get("favorites") as? List<String> ?: emptyList()
                readFavourites(favoriteIds)
            }
            .addOnFailureListener { e ->
                Log.e("GuitarViewModel", "Error loading favourites: ${e.message}")
            }
    }

    private fun readFavourites(favoriteIds: List<String>) {
        if (favoriteIds.isEmpty()) {
            _favourites.value = emptyList()
            return
        }

        val favouriteGuitars = mutableListOf<Guitar>()
        favoriteIds.forEach { id ->
            db.collection("guitars").document(id).get()
                .addOnSuccessListener { guitarDoc ->
                    val guitar = guitarDoc.toObject(Guitar::class.java)
                    guitar?.let { favouriteGuitars.add(it) }
                    _favourites.value = favouriteGuitars.toList()
                }
                .addOnFailureListener { e ->
                    Log.e("GuitarViewModel", "Error fetching guitar with ID $id: ${e.message}")
                }
        }
    }

    fun toggleFavourite(guitar: Guitar, context: Context) {
        currentUserId?.let { userId ->
            val userDoc = db.collection("users").document(userId)
            if (_favourites.value.any { it.id == guitar.id }) {
                userDoc.update("favorites", FieldValue.arrayRemove(guitar.id))
                    .addOnSuccessListener {
                        Toast.makeText(context, context.getText(R.string.removed_favourite), Toast.LENGTH_SHORT).show()
                        loadFavourites(userId)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error al eliminar favorito: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                userDoc.update("favorites", FieldValue.arrayUnion(guitar.id))
                    .addOnSuccessListener {
                        Toast.makeText(context, context.getString(R.string.added_favourite), Toast.LENGTH_SHORT).show()
                        loadFavourites(userId)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error al añadir favorito: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    fun loadCart(userId: String) {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                val cartIds = document.get("cart") as? List<String> ?: emptyList()
                readCart(cartIds)
            }
            .addOnFailureListener { e ->
                Log.e("GuitarViewModel", "Error loading favourites: ${e.message}")
            }
    }

    private fun readCart(cartIds: List<String>) {
        if (cartIds.isEmpty()) {
            _cart.value = emptyList()
            return
        }

        val cartGuitars = mutableListOf<Guitar>()
        cartIds.forEach { id ->
            db.collection("guitars").document(id).get()
                .addOnSuccessListener { guitarDoc ->
                    val guitar = guitarDoc.toObject(Guitar::class.java)
                    guitar?.let { cartGuitars.add(it) }
                    _cart.value = cartGuitars.toList()
                }
                .addOnFailureListener { e ->
                    Log.e("GuitarViewModel", "Error fetching guitar with ID $id: ${e.message}")
                }
        }
    }

    fun toggleCart(guitar: Guitar, context: Context) {
        currentUserId?.let { userId ->
            val userDoc = db.collection("users").document(userId)
            if (_cart.value.any { it.id == guitar.id }) {
                userDoc.update("cart", FieldValue.arrayRemove(guitar.id))
                    .addOnSuccessListener {
                        Toast.makeText(context, context.getText(R.string.removed_cart), Toast.LENGTH_SHORT).show()
                        loadFavourites(userId)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error al eliminar del carro: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                userDoc.update("cart", FieldValue.arrayUnion(guitar.id))
                    .addOnSuccessListener {
                        Toast.makeText(context, context.getString(R.string.added_cart), Toast.LENGTH_SHORT).show()
                        loadFavourites(userId)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error al añadir al carro: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}
