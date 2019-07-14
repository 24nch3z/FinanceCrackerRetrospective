package ru.s4nchez.financecrackerretrospective.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.s4nchez.financecrackerretrospective.data.model.Wallet

@Dao
interface WalletDao : BaseDao<Wallet> {

    @Query("SELECT * FROM Wallet")
    fun getAll(): LiveData<List<Wallet>>

    @Query("SELECT * FROM Wallet WHERE id=:id")
    fun getById(id: Long): LiveData<Wallet>
}