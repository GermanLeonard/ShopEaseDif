package com.shopease.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shopease.data.model.Product
import com.shopease.data.model.CartItem

@Database(entities = [Product::class, CartItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
}
