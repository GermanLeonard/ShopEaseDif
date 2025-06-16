package com.shopease.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true) val cartId: Int = 0,
    val productId: Int,
    val title: String,
    val price: Double,
    val image: String
) : java.io.Serializable
