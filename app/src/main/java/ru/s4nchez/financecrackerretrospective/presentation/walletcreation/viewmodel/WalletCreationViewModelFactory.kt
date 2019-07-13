package ru.s4nchez.financecrackerretrospective.presentation.walletcreation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor

class WalletCreationViewModelFactory(
        private val financeInteractor: FinanceInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WalletCreationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WalletCreationViewModel(financeInteractor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}