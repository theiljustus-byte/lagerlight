package com.example.lagerlite
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lagerlite.data.AppDatabase
import com.example.lagerlite.data.Repository
import com.example.lagerlite.data.Item
import com.example.lagerlite.data.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
class InventoryViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = Repository(AppDatabase.get(app))
    val uiState: StateFlow<com.example.lagerlite.UiState>
    init {
        uiState = combine(repo.items(), repo.orders()) { items, orders -> com.example.lagerlite.UiState(items = items, orders = orders) }
            .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000), com.example.lagerlite.UiState())
    }
    fun upsertItem(i: Item) = viewModelScope.launch { repo.upsertItem(i) }
    fun adjustItem(id: Long, delta: Int) = viewModelScope.launch { repo.adjustItem(id, delta) }
    fun findByBarcode(barcode: String, cb: (Item?)->Unit) = viewModelScope.launch { cb(repo.findByBarcode(barcode)) }
    fun insertOrder(o: Order, cb: (Long)->Unit) = viewModelScope.launch { val id = repo.insertOrder(o); cb(id) }
}