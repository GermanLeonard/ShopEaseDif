package com.shopease.repository

import com.shopease.data.local.AppDatabase
import com.shopease.data.model.CartItem
import com.shopease.data.model.Product
import com.shopease.data.remote.ApiService
import kotlinx.coroutines.flow.Flow


class ShopRepository(
    private val db: AppDatabase,
    private val api: ApiService
) {
    fun getProducts(): Flow<List<Product>> = db.productDao().getAllProducts()

    suspend fun refreshProductsFromApi() {
        val apiProducts = api.getProducts()
        db.productDao().insertAll(apiProducts)
    }

    fun getCart(): Flow<List<CartItem>> = db.cartDao().getCartItems()

    suspend fun addToCart(item: CartItem) = db.cartDao().addToCart(item)

    suspend fun removeFromCart(item: CartItem) = db.cartDao().removeFromCart(item)
}
