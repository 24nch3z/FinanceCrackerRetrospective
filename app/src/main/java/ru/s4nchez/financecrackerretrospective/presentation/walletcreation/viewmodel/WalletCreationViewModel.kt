package ru.s4nchez.financecrackerretrospective.presentation.walletcreation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.s4nchez.financecrackerretrospective.data.model.Wallet
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.WalletCreationView

class WalletCreationViewModel(private val financeInteractor: FinanceInteractor) : ViewModel() {

    private var view: WalletCreationView? = null

    val walletLiveData = MutableLiveData<Wallet>()
    val validationErrorLiveData = MutableLiveData<Boolean>()

    fun bindView(view: WalletCreationView) {
        this.view = view
    }

    fun getWallet(id: Long) {
        walletLiveData.value = financeInteractor.getWallet(id).value
    }

    fun saveWallet(id: Long, name: String, currency: String?) {
        if (name.trim().isEmpty()) {
            validationErrorLiveData.value = true
            return
        }

        if (currency == null) {
            validationErrorLiveData.value = true
            return
        }

        val wallet = financeInteractor.getWallet(id).value
        financeInteractor.saveWallet(
                wallet?.copy(name = name, currency = currency)
                        ?: Wallet(name = name, currency = currency))
        view?.closeScreen()
    }

    override fun onCleared() {
        view = null
        super.onCleared()
    }
}