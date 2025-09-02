package com.example.lagerlite.ui
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lagerlite.InventoryViewModel
@Composable
fun LagerApp() {
    val navController = rememberNavController()
    val vm: InventoryViewModel = viewModel(factory = InventoryViewModel.Factory(androidx.compose.ui.platform.LocalContext.current.applicationContext as android.app.Application))
    Scaffold(bottomBar = { BottomNav(navController) }) { padding ->
        androidx.navigation.compose.NavHost(navController = navController, startDestination = "stock", modifier = Modifier.fillMaxSize()) {
            androidx.navigation.compose.composable("stock") { StockScreen(vm) }
            androidx.navigation.compose.composable("orders") { OrdersScreen(vm) }
        }
    }
}
@Composable
fun BottomNav(navController: androidx.navigation.NavHostController) {
    val items = listOf("stock" to "Lager", "orders" to "Aufträge")
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val current = navBackStackEntry?.destination?.route
        items.forEach { (route, label) ->
            NavigationBarItem(selected = current == route, onClick = { navController.navigate(route) }) {
                if (route=="stock") androidx.compose.material3.Icon(androidx.compose.material.icons.Icons.Default.Inventory, contentDescription = label) else androidx.compose.material3.Icon(androidx.compose.material.icons.Icons.Default.List, contentDescription = label)
                androidx.compose.material3.Text(label)
            }
        }
    }
}