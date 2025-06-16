package com.shopease.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopease.data.model.CartItem
import com.shopease.data.model.Product
import com.shopease.repository.ShopRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException

class ShopViewModel(private val repository: ShopRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    val products = repository.getProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val cart = repository.getCart()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        syncWithApi()
    }

    private fun syncWithApi() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.refreshProductsFromApi() // guarda en Room
            } catch (e: IOException) {
                if (products.value.isEmpty()) {
                    _error.value = "No se pudo actualizar los productos."
                }
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun addToCart(product: Product) {
        viewModelScope.launch {
            repository.addToCart(
                CartItem(productId = product.id, title = product.title, price = product.price, image = product.image)
            )
        }
    }

    fun removeFromCart(item: CartItem) {
        viewModelScope.launch {
            repository.removeFromCart(item)
        }
    }
}
