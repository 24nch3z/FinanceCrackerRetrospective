package ru.s4nchez.financecrackerretrospective.presentation.transaction.viewmodel

import androidx.lifecycle.ViewModel
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor
import ru.terrakok.cicerone.Router

class TransactionCreationViewModel(
        private val financeInteractor: FinanceInteractor,
        private val router: Router
) : ViewModel() {

    val categoriesLiveData = financeInteractor.getCategories()

    fun saveTransaction(id: Long?, value: String, description: String, categoryId: Long, walletId: Long, date: Long) {
        financeInteractor.saveTransaction(id, value.toDouble(), description, categoryId, walletId, date)
        router.exit()
    }
}