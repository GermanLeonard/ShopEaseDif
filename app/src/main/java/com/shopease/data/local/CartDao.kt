package com.shopease.data.local

import androidx.room.*
import com.shopease.data.model.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    fun getCartItems(): Flow<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(item: CartItem)

    @Delete
    suspend fun removeFromCart(item: CartItem)
}