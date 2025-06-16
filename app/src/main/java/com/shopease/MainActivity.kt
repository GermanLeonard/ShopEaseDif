package com.shopease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.shopease.data.local.AppDatabase
import com.shopease.data.remote.NetworkModule
import com.shopease.repository.ShopRepository
import com.shopease.ui.navigation.MainNavigation
import com.shopease.viewmodel.ShopViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "shopease-db"
        ).build()
        val api = NetworkModule.provideApiService()
        val repository = ShopRepository(db, api)
        val viewModel = ShopViewModel(repository)

        setContent {
            val navController = rememberNavController()
            MainNavigation(navController, viewModel)
        }
    }
}
