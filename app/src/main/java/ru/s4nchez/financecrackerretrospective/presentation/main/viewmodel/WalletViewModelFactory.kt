package ru.s4nchez.financecrackerretrospective.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor
import ru.terrakok.cicerone.Router

class WalletViewModelFactory(
        private val financeInteractor: FinanceInteractor,
        private val router: Router
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WalletViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WalletViewModel(financeInteractor, router) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}