package com.example.lagerlite.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lagerlite.data.Item
import com.example.lagerlite.InventoryViewModel
@Composable
fun StockScreen(vm: InventoryViewModel) {
    val state by vm.uiState.collectAsState()
    Column(Modifier.fillMaxSize().padding(8.dp)) {
        Button(onClick = { /* open scanner / new item */ }) { Text("Artikel scannen / anlegen") }
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(state.items) { it ->
                Card(Modifier.fillMaxWidth().padding(4.dp)) {
                    Row(Modifier.padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column { Text(it.name); Text("Stk: ${it.quantity}") }
                        Row { Button(onClick = { vm.adjustItem(it.id, -1) }, enabled = it.quantity>0){ Text("-") }; Spacer(Modifier.width(4.dp)); Button(onClick = { vm.adjustItem(it.id, +1) }){ Text("+") } }
                    }
                }
            }
        }
    }
}