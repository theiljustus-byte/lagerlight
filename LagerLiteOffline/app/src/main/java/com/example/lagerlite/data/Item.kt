package com.example.lagerlite.data
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "items")
data class Item(@PrimaryKey(autoGenerate = true) val id: Long = 0L, val name: String, val sku: String = "", val barcode: String = "", val quantity: Int = 0, val location: String = "", val notes: String = "", val updatedAt: Long = System.currentTimeMillis())