package com.shopease.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.shopease.data.model.Product
import com.shopease.data.model.CartItem

@Composable
fun ProductCard(
    product: Any,
    onAdd: () -> Unit,
    isInCart: Boolean = false
) {
    val title = (product as? Product)?.title ?: (product as? CartItem)?.title ?: ""
    val price = (product as? Product)?.price ?: (product as? CartItem)?.price ?: 0.0
    val imageUrl = (product as? Product)?.image ?: (product as? CartItem)?.image ?: ""

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (imageUrl.isNotBlank()) {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(title, style = MaterialTheme.typography.titleMedium)
            Text("$${price}", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onAdd,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isInCart) "Eliminar del carrito" else "Agregar al carrito")
            }

        }
    }
}
