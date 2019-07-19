package ru.s4nchez.financecrackerretrospective.domain

import androidx.lifecycle.LiveData
import ru.s4nchez.financecrackerretrospective.data.model.Wallet

interface FinanceInteractor {
    fun getWallets(): LiveData<List<Wallet>>
    fun getWallet(id: Long?, mode: Int): LiveData<Wallet>
    fun saveWallet(wallet: Wallet, mode: Int)
}