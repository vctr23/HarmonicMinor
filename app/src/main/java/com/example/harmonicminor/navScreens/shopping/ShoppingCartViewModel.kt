package com.example.harmonicminor.navScreens.shopping

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
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ShoppingCartViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> get() = _cartItems

    fun loadCart(userId: String) {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                val cartIds = document.get("cart") as? Map<String, List<String>> ?: emptyMap()
                readCartItems(cartIds)
            }
            .addOnFailureListener { e ->
                Log.e("ShoppingCartViewModel", "Error loading cart: ${e.message}")
            }
    }

    private fun readCartItems(cartIds: Map<String, List<String>>) {
        val cartList = mutableListOf<CartItem>()

        cartIds["guitars"]?.forEach { id ->
            db.collection("guitars").document(id).get()
                .addOnSuccessListener { guitarDoc ->
                    val guitar = guitarDoc.toObject(Guitar::class.java)
                    guitar?.let { cartList.add(it) }
                    _cartItems.value = cartList.toList()
                }
        }
        cartIds["basses"]?.forEach { id ->
            db.collection("basses").document(id).get()
                .addOnSuccessListener { bassDoc ->
                    val bass = bassDoc.toObject(Bass::class.java)
                    bass?.let { cartList.add(it) }
                    _cartItems.value = cartList.toList()
                }
        }
        cartIds["drums"]?.forEach { id ->
            db.collection("drums").document(id).get()
                .addOnSuccessListener { drumsDoc ->
                    val drums = drumsDoc.toObject(Drums::class.java)
                    drums?.let { cartList.add(it) }
                    _cartItems.value = cartList.toList()
                }
        }
        cartIds["djs"]?.forEach { id ->
            db.collection("djs").document(id).get()
                .addOnSuccessListener { djsDoc ->
                    val djs = djsDoc.toObject(Dj::class.java)
                    djs?.let { cartList.add(it) }
                    _cartItems.value = cartList.toList()
                }
        }
        cartIds["winds"]?.forEach { id ->
            db.collection("winds").document(id).get()
                .addOnSuccessListener { windDoc ->
                    val wind = windDoc.toObject(Wind::class.java)
                    wind?.let { cartList.add(it) }
                    _cartItems.value = cartList.toList()
                }
        }
        cartIds["pianos"]?.forEach { id ->
            db.collection("pianos").document(id).get()
                .addOnSuccessListener { pianoDoc ->
                    val piano = pianoDoc.toObject(Piano::class.java)
                    piano?.let { cartList.add(it) }
                    _cartItems.value = cartList.toList()
                }
        }
        cartIds["softwares"]?.forEach { id ->
            db.collection("softwares").document(id).get()
                .addOnSuccessListener { softwareDoc ->
                    val software = softwareDoc.toObject(Software::class.java)
                    software?.let { cartList.add(it) }
                    _cartItems.value = cartList.toList()
                }
        }
        cartIds["microphones"]?.forEach { id ->
            db.collection("microphones").document(id).get()
                .addOnSuccessListener { micDoc ->
                    val mic = micDoc.toObject(Microphone::class.java)
                    mic?.let { cartList.add(it) }
                    _cartItems.value = cartList.toList()
                }
        }
    }

    fun toggleCart(item: CartItem, context: Context) {
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

            val updatedCart = _cartItems.value.toMutableList()

            if (_cartItems.value.any { it.id == item.id }) {
                updatedCart.removeIf { it.id == item.id }
                _cartItems.value = updatedCart

                userDoc.update("cart.$collectionKey", FieldValue.arrayRemove(item.id))
                    .addOnSuccessListener {
                        Toast.makeText(
                            context,
                            context.getText(R.string.removed_cart),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                updatedCart.add(item)
                _cartItems.value = updatedCart

                userDoc.update("cart.$collectionKey", FieldValue.arrayUnion(item.id))
                    .addOnSuccessListener {
                        Toast.makeText(
                            context,
                            context.getString(R.string.added_cart),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }

    fun clearCart(userId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val userDoc = db.collection("users").document(userId)

        // Get cart items and update stock
        userDoc.get()
            .addOnSuccessListener { document ->
                val cartIds = document.get("cart") as? Map<String, List<String>> ?: emptyMap()
                val batch = db.batch()

                // Decrement stock for each product in the cart
                val tasks = cartIds.flatMap { (collection, ids) ->
                    ids.map { id ->
                        val productDoc = db.collection(collection).document(id)
                        productDoc.get().continueWith { task ->
                            val currentStockStr = task.result?.getString("stock") ?: "0"
                            val currentStock = currentStockStr.toLongOrNull() ?: 0
                            if (currentStock > 0) {
                                val newStock = (currentStock - 1).toString()
                                batch.update(productDoc, "stock", newStock)
                            }
                        }
                    }
                }

                // Wait for all tasks to complete
                Tasks.whenAll(tasks).addOnSuccessListener {
                    // Clear the cart after updating stock
                    batch.update(userDoc, "cart", emptyMap<String, List<String>>())
                    batch.commit()
                        .addOnSuccessListener {
                            // Clear the cart items in the view model
                            _cartItems.value = emptyList()
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            onFailure(e.message ?: "Unknown error")
                        }
                }.addOnFailureListener { e ->
                    onFailure(e.message ?: "Unknown error")
                }
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Unknown error")
            }
    }
}

