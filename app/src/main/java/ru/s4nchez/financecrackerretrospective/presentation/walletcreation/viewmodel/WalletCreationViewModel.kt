package ru.s4nchez.financecrackerretrospective.presentation.walletcreation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.s4nchez.financecrackerretrospective.data.model.Wallet
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor
import ru.s4nchez.financecrackerretrospective.utils.SingleLiveEvent
import ru.terrakok.cicerone.Router

class WalletCreationViewModel(
        private val financeInteractor: FinanceInteractor,
        private val router: Router
) : ViewModel() {

    val walletLiveData = MutableLiveData<Wallet>()
    val validationErrorLiveData = SingleLiveEvent<Boolean>()

    fun getWallet(id: Long?, mode: Int) {
        walletLiveData.value = financeInteractor.getWallet(id, mode).value
    }

    fun saveWallet(id: Long?, name: String, currency: String?, mode: Int) {
        if (name.trim().isEmpty()) {
            validationErrorLiveData.value = true
            return
        }

        if (currency == null) {
            validationErrorLiveData.value = true
            return
        }

        val wallet = financeInteractor.getWallet(id, mode).value!!
        financeInteractor.saveWallet(wallet.copy(name = name, currency = currency), mode)

        router.exit() // TODO: Открывать экран кошелька
    }

//    override fun onCleared() { // TODO: Протестить
//        Log.d("sssss", "")
//        super.onCleared()
//    }
}