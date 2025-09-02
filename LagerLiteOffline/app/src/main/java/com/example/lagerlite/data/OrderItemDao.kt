package com.example.lagerlite.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface OrderItemDao {
    @Query("SELECT * FROM order_items WHERE orderId = :orderId") fun forOrder(orderId: Long): Flow<List<OrderItem>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(oi: OrderItem): Long
}