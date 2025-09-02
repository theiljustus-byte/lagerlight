package com.example.lagerlite.ui
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lagerlite.InventoryViewModel
import com.example.lagerlite.data.Order
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
@Composable
fun OrdersScreen(vm: InventoryViewModel) {
    val state by vm.uiState.collectAsState()
    val ctx = LocalContext.current
    Column(Modifier.fillMaxSize().padding(8.dp)) {
        Button(onClick = {
            val now = System.currentTimeMillis()
            vm.insertOrder(Order(orderNumber = "ORD-${now}", customer = "Kunde A", date = now)) { id -> }
        }) { Text("Neuer Auftrag (Test)") }
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(state.orders) { o ->
                Card(Modifier.fillMaxWidth().padding(4.dp).clickable {
                    if (o.photoPath.isNotBlank()) {
                        val f = File(o.photoPath)
                        if (f.exists()) {
                            val uri = FileProvider.getUriForFile(ctx, ctx.packageName + ".provider", f)
                            val i = Intent(Intent.ACTION_VIEW).apply { setDataAndType(uri, "image/*"); addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) }
                            ctx.startActivity(i)
                        }
                    }
                }) {
                    Column(Modifier.padding(8.dp)) {
                        Text(o.orderNumber)
                        Text(o.customer)
                    }
                }
            }
        }
    }
}