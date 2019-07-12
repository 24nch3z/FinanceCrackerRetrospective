package ru.s4nchez.financecrackerretrospective.domain

import androidx.lifecycle.LiveData
import ru.s4nchez.financecrackerretrospective.data.model.Wallet

interface FinanceInteractor {
    fun getWallets(): LiveData<List<Wallet>>
}