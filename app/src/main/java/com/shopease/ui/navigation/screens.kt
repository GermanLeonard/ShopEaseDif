package com.shopease.ui.navigation

sealed class Routes(val route: String) {
    object ProductListScreen : Routes("product_list")
    object CartScreen : Routes("cart")
    object ProductDetailScreen : Routes("product_detail")
}

