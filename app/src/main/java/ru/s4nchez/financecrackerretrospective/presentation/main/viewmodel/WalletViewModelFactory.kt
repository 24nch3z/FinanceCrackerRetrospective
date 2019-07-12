package ru.s4nchez.financecrackerretrospective.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor

class WalletViewModelFactory(private val financeInteractor: FinanceInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WalletViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WalletViewModel(financeInteractor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}