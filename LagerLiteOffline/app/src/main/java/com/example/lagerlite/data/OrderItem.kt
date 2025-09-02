package com.example.lagerlite.data
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "order_items")
data class OrderItem(@PrimaryKey(autoGenerate = true) val id: Long = 0L, val orderId: Long, val itemId: Long, val quantity: Int, val note: String = "")