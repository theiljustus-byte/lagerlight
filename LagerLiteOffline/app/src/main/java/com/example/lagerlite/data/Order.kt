package com.example.lagerlite.data
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "orders")
data class Order(@PrimaryKey(autoGenerate = true) val id: Long = 0L, val orderNumber: String = "", val customer: String = "", val date: Long = System.currentTimeMillis(), val photoPath: String = "")