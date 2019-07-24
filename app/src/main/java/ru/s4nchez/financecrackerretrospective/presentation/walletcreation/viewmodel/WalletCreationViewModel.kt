package ru.s4nchez.financecrackerretrospective.presentation.walletcreation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.s4nchez.financecrackerretrospective.data.model.Wallet
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor
import ru.s4nchez.financecrackerretrospective.presentation.common.WalletFragmentScreen
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.MODE_EDIT
import ru.s4nchez.financecrackerretrospective.utils.SingleLiveEvent
import ru.terrakok.cicerone.Router

class WalletCreationViewModel(
        private val financeInteractor: FinanceInteractor,
        private val router: Router
) : ViewModel() {

    val walletLiveData = MutableLiveData<Wallet>()
    val validationErrorLiveData = SingleLiveEvent<Boolean>()

    private val triggerSaveWalletCompleteLiveData = MutableLiveData<SaveWalletData>()
    val saveWalletCompleteLiveData: LiveData<Long> = Transformations.switchMap(triggerSaveWalletCompleteLiveData) {
        financeInteractor.saveWallet(it.wallet, it.mode)
    }

    fun getWallet(id: Long?, mode: Int) {
        walletLiveData.value = when (mode) {
            MODE_EDIT -> financeInteractor.getWallet(id!!).value
            else -> financeInteractor.getEmptyWallet().value
        }
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

        val wallet: Wallet = when (mode) {
            MODE_EDIT -> financeInteractor.getWallet(id!!).value
            else -> financeInteractor.getEmptyWallet().value
        }!!

        triggerSaveWalletCompleteLiveData.postValue(SaveWalletData(wallet.copy(name = name, currency = currency), mode))
    }

    fun openWalletScreen(walletId: Long) {
        router.replaceScreen(WalletFragmentScreen(walletId))
    }

    data class SaveWalletData(val wallet: Wallet, val mode: Int)
}