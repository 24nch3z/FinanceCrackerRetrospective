package ru.s4nchez.financecrackerretrospective.data.repository

import androidx.lifecycle.LiveData
import ru.s4nchez.financecrackerretrospective.data.model.Wallet

interface FinanceRepository {
    fun getWallets(): LiveData<List<Wallet>>
    fun getWallet(id: Long): LiveData<Wallet>
    fun saveWallet(wallet: Wallet): Long
    fun updateWallet(wallet: Wallet): Long
}