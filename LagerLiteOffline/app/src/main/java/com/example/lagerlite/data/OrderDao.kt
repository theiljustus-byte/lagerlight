package com.example.lagerlite.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface OrderDao {
    @Query("SELECT * FROM orders ORDER BY date DESC") fun all(): Flow<List<Order>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(order: Order): Long
}