package com.shopease.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val image: String
) : java.io.Serializable
