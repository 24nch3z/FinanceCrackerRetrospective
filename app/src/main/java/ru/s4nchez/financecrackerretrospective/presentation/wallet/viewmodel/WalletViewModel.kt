package ru.s4nchez.financecrackerretrospective.presentation.wallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.s4nchez.financecrackerretrospective.data.model.Wallet
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor
import ru.terrakok.cicerone.Router

class WalletViewModel(
        private val financeInteractor: FinanceInteractor,
        private val router: Router
) : ViewModel() {

    private val triggerLiveData = MutableLiveData<Long>()
    val walletLiveData: LiveData<Wallet> = Transformations.switchMap(triggerLiveData) { id ->
        financeInteractor.getWallet(id)
    }

    fun getWallet(id: Long) {
        triggerLiveData.postValue(id)
    }
}