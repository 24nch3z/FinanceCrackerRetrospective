package ru.s4nchez.financecrackerretrospective.domain

import androidx.lifecycle.LiveData
import ru.s4nchez.financecrackerretrospective.data.model.Category
import ru.s4nchez.financecrackerretrospective.data.model.Transaction
import ru.s4nchez.financecrackerretrospective.data.model.Wallet

interface FinanceInteractor {
    fun getWallets(): LiveData<List<Wallet>>
    fun getWallet(id: Long): LiveData<Wallet>
    fun getEmptyWallet(): LiveData<Wallet>
    fun saveWallet(wallet: Wallet, mode: Int): LiveData<Long>
    fun deleteWallet(id: Long)

    fun getCategories(): LiveData<List<Category>>
    fun getCategory(id: Long): Category

    fun getTransactions(walletId: Long): LiveData<List<Transaction>>
    fun saveTransaction(id: Long?, value: Double, description: String, categoryId: Long, walletId: Long, date: Long): LiveData<Long>
}