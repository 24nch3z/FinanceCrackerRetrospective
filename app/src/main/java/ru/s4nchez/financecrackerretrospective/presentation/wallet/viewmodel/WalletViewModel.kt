package ru.s4nchez.financecrackerretrospective.presentation.wallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.s4nchez.financecrackerretrospective.data.model.Transaction
import ru.s4nchez.financecrackerretrospective.data.model.Wallet
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor
import ru.s4nchez.financecrackerretrospective.presentation.common.TransactionCreationScreen
import ru.s4nchez.financecrackerretrospective.presentation.common.adapter.ListItem
import ru.s4nchez.financecrackerretrospective.presentation.wallet.adapter.TransactionDelegate
import ru.terrakok.cicerone.Router

class WalletViewModel(
        private val financeInteractor: FinanceInteractor,
        private val router: Router
) : ViewModel() {

    private val triggerWalletLiveData = MutableLiveData<Long>()
    val walletLiveData: LiveData<Wallet> = Transformations.switchMap(triggerWalletLiveData) { id ->
        financeInteractor.getWallet(id)
    }

    private val triggerTransactionListLiveData = MutableLiveData<Long>()
    private val pureTransactionListLiveData: LiveData<List<Transaction>> = Transformations.switchMap(triggerTransactionListLiveData) { id ->
        financeInteractor.getTransactions(id)
    }
    val transactionListLiveData: LiveData<List<ListItem>> = Transformations.map(pureTransactionListLiveData) { transaction ->
        transaction.map { TransactionDelegate.Model(it.id.toString(), it) }.toMutableList()
    }

    fun getWallet(id: Long) {
        triggerWalletLiveData.postValue(id)
        triggerTransactionListLiveData.postValue(id)
    }

    fun deleteWallet(id: Long) {
        financeInteractor.deleteWallet(id)
        router.exit()
    }

    fun openTransactionCreationScreen(walletId: Long) {
        router.navigateTo(TransactionCreationScreen(walletId))
    }
}