package com.example.harmonicminor.navScreens.home.software

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonicminor.R
import com.example.harmonicminor.navScreens.home.Identifiable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ItemViewModel<T : Identifiable>(private val itemClass: Class<T>) : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    private val _items = mutableStateListOf<T>()
    val items: List<T> get() = _items

    private val _favourites = mutableStateOf<List<T>>(emptyList())
    val favourites: State<List<T>> get() = _favourites

    private val _cart = mutableStateOf<List<T>>(emptyList())
    val cart: State<List<T>> get() = _cart

    // Función para cargar todos los items, sean del tipo que sean
    fun readItems(collection: String) {
        viewModelScope.launch {
            try {
                val result = db.collection(collection).get().await()
                _items.clear()
                for (document in result) {
                    val item = document.toObject(itemClass)
                    _items.add(item)
                }
            } catch (e: Exception) {
                Log.e("ItemViewModel", "Error getting documents: $e")
            }
        }
    }

    // Función para cargar favoritos de cualquier tipo de item
    fun loadFavourites(userId: String, collection: String) {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                val favouriteIds = document.get("favorites") as? List<String> ?: emptyList()
                readFavourites(favouriteIds, collection)
            }
            .addOnFailureListener { e ->
                Log.e("ItemViewModel", "Error loading favourites: ${e.message}")
            }
    }

    private fun readFavourites(favoriteIds: List<String>, collection: String) {
        if (favoriteIds.isEmpty()) {
            _favourites.value = emptyList()
            return
        }

        val favouriteItems = mutableListOf<T>()
        favoriteIds.forEach { id ->
            db.collection(collection).document(id).get()
                .addOnSuccessListener { itemDoc ->
                    val item = itemDoc.toObject(itemClass)
                    item?.let { favouriteItems.add(it) }
                    _favourites.value = favouriteItems.toList()
                }
                .addOnFailureListener { e ->
                    Log.e("ItemViewModel", "Error fetching item with ID $id: ${e.message}")
                }
        }
    }

    // Función para añadir o eliminar de favoritos de cualquier tipo de item
    fun toggleFavourite(item: T, context: Context, collection: String) {
        currentUserId?.let { userId ->
            val userDoc = db.collection("users").document(userId)
            if (_favourites.value.any { it.id == item.id }) {
                userDoc.update("favorites", FieldValue.arrayRemove(item.id))
                    .addOnSuccessListener {
                        Toast.makeText(context, context.getString(R.string.removed_favourite), Toast.LENGTH_SHORT).show()
                        loadFavourites(userId, collection)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error removing from favourites: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                userDoc.update("favorites", FieldValue.arrayUnion(item.id))
                    .addOnSuccessListener {
                        Toast.makeText(context, context.getString(R.string.added_favourite), Toast.LENGTH_SHORT).show()
                        loadFavourites(userId, collection)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error adding to favourites: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    // Función para cargar el carrito de cualquier tipo de item
    fun loadCart(userId: String, collection: String) {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                val cartIds = document.get("cart") as? List<String> ?: emptyList()
                readCart(cartIds, collection)
            }
            .addOnFailureListener { e ->
                Log.e("ItemViewModel", "Error loading cart: ${e.message}")
            }
    }

    private fun readCart(cartIds: List<String>, collection: String) {
        if (cartIds.isEmpty()) {
            _cart.value = emptyList()
            return
        }

        val cartItems = mutableListOf<T>()
        cartIds.forEach { id ->
            db.collection(collection).document(id).get()
                .addOnSuccessListener { itemDoc ->
                    val item = itemDoc.toObject(itemClass)
                    item?.let { cartItems.add(it) }
                    _cart.value = cartItems.toList()
                }
                .addOnFailureListener { e ->
                    Log.e("ItemViewModel", "Error fetching item with ID $id: ${e.message}")
                }
        }
    }

    // Función para añadir o eliminar del carrito de cualquier tipo de item
    fun toggleCart(item: T, context: Context, collection: String) {
        currentUserId?.let { userId ->
            val userDoc = db.collection("users").document(userId)
            if (_cart.value.any { it.id == item.id }) {
                userDoc.update("cart", FieldValue.arrayRemove(item.id))
                    .addOnSuccessListener {
                        Toast.makeText(context, context.getString(R.string.removed_cart), Toast.LENGTH_SHORT).show()
                        loadFavourites(userId, collection)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error removing from cart: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                userDoc.update("cart", FieldValue.arrayUnion(item.id))
                    .addOnSuccessListener {
                        Toast.makeText(context, context.getString(R.string.added_cart), Toast.LENGTH_SHORT).show()
                        loadFavourites(userId, collection)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error adding to cart: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}
