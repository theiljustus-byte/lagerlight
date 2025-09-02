package com.example.lagerlite.data
class Repository(private val db: AppDatabase) {
    private val itemDao = db.itemDao()
    private val orderDao = db.orderDao()
    private val orderItemDao = db.orderItemDao()
    fun items() = itemDao.all()
    suspend fun upsertItem(i: Item) = itemDao.upsert(i)
    suspend fun adjustItem(id: Long, delta: Int) = itemDao.adjustQuantity(id, delta)
    suspend fun findByBarcode(barcode: String) = itemDao.getByBarcode(barcode)
    fun orders() = orderDao.all()
    suspend fun insertOrder(o: Order) = orderDao.insert(o)
    fun orderItems(orderId: Long) = orderItemDao.forOrder(orderId)
    suspend fun insertOrderItem(oi: OrderItem) = orderItemDao.insert(oi)
}