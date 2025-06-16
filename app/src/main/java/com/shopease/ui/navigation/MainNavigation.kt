package com.shopease.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shopease.ui.screens.CartScreen
import com.shopease.ui.screens.ProductDetailScreen
import com.shopease.ui.screens.ProductListScreen
import com.shopease.viewmodel.ShopViewModel

@Composable
fun MainNavigation(navController: NavHostController, viewModel: ShopViewModel) {
    NavHost(navController = navController, startDestination = Routes.ProductListScreen.route) {
        composable(Routes.ProductListScreen.route) {
            ProductListScreen(navController, viewModel)
        }
        composable(Routes.CartScreen.route) {
            CartScreen(navController, viewModel)
        }
        composable(Routes.ProductDetailScreen.route) {
            ProductDetailScreen(navController)
        }
    }
}

