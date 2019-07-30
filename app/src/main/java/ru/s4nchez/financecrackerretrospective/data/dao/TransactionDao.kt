package ru.s4nchez.financecrackerretrospective.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.s4nchez.financecrackerretrospective.data.model.Transaction

@Dao
interface TransactionDao : BaseDao<Transaction> {

    @Query("SELECT * FROM `Transaction` WHERE walletId = :walletId")
    fun getAllByWallet(walletId: Long): LiveData<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE id = :id")
    fun getById(id: Long): Transaction
}