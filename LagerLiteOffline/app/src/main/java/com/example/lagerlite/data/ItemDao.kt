package com.example.lagerlite.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface ItemDao {
    @Query("SELECT * FROM items ORDER BY name") fun all(): Flow<List<Item>>
    @Query("SELECT * FROM items WHERE barcode = :barcode LIMIT 1") suspend fun getByBarcode(barcode: String): Item?
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun upsert(item: Item): Long
    @Query("UPDATE items SET quantity = quantity + :delta WHERE id = :id") suspend fun adjustQuantity(id: Long, delta: Int)
}