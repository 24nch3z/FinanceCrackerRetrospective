package ru.s4nchez.financecrackerretrospective.data.repository

import androidx.lifecycle.LiveData
import ru.s4nchez.financecrackerretrospective.data.model.Category
import ru.s4nchez.financecrackerretrospective.data.model.Transaction
import ru.s4nchez.financecrackerretrospective.data.model.Wallet

interface FinanceRepository {
    fun getWallets(): LiveData<List<Wallet>>
    fun getWallet(id: Long): LiveData<Wallet>
    fun saveWallet(wallet: Wallet): Long
    fun updateWallet(wallet: Wallet): Long
    fun deleteWallet(id: Long)

    fun getCategories(): LiveData<List<Category>>
    fun getCategory(id: Long): Category

    fun getTransactions(walletId: Long): LiveData<List<Transaction>>
    fun saveTransaction(transaction: Transaction): Long
}