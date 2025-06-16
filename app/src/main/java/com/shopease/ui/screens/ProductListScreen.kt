package com.shopease.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shopease.ui.components.ProductCard
import com.shopease.ui.navigation.Routes
import com.shopease.viewmodel.ShopViewModel

@Composable
fun ProductListScreen(navController: NavController, viewModel: ShopViewModel) {
    val products by viewModel.products.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        if (isLoading && products.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (error != null && products.isEmpty()) {
            Text(
                text = "Error: $error",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(products) { product ->
                    ProductCard(
                        product = product,
                        onAdd = { viewModel.addToCart(product) }
                    )
                }
            }
        }

        Button(
            onClick = { navController.navigate(Routes.CartScreen.route) },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text("ir al carrito")
        }
    }
}
