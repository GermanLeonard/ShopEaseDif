package com.shopease.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shopease.ui.components.ProductCard
import com.shopease.viewmodel.ShopViewModel

@Composable
fun CartScreen(navController: NavController, viewModel: ShopViewModel) {
    val cartItems = viewModel.cart.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver"
                )
            }
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cartItems) { item ->
                ProductCard(
                    product = item,
                    onAdd = { viewModel.removeFromCart(item) },
                    isInCart = true
                )
            }
        }
    }
}
