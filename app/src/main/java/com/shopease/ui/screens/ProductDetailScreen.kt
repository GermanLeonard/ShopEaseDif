package com.shopease.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shopease.data.model.Product

@Composable
fun ProductDetailScreen(navController: NavController) {
    val product = navController.previousBackStackEntry?.savedStateHandle?.get<Product>("product")
    product?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Detalle del producto")
            Spacer(modifier = Modifier.height(10.dp))
            Text("Nombre: ${it.title}")
            Text("Precio: $${it.price}")
        }
    }
}
