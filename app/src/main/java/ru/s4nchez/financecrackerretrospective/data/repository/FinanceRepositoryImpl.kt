package ru.s4nchez.financecrackerretrospective.data.repository

import androidx.lifecycle.LiveData
import ru.s4nchez.financecrackerretrospective.data.AppDatabase
import ru.s4nchez.financecrackerretrospective.data.model.Wallet

class FinanceRepositoryImpl(private val database: AppDatabase) : FinanceRepository {

    override fun getWallets(): LiveData<List<Wallet>> {
        return database.walletDao().getAll()
    }

    override fun getWallet(id: Long): LiveData<Wallet> {
        return database.walletDao().getById(id)
    }

    override fun saveWallet(wallet: Wallet) {
        database.walletDao().insert(wallet)
    }
}