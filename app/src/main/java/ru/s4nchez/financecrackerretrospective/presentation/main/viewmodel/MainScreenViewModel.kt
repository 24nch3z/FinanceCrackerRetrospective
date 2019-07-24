package ru.s4nchez.financecrackerretrospective.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor
import ru.s4nchez.financecrackerretrospective.presentation.common.WalletCreationScreen
import ru.s4nchez.financecrackerretrospective.presentation.common.WalletFragmentScreen
import ru.s4nchez.financecrackerretrospective.presentation.common.adapter.ListItem
import ru.s4nchez.financecrackerretrospective.presentation.main.adapter.delegate.AddWalletDelegate
import ru.s4nchez.financecrackerretrospective.presentation.main.adapter.delegate.WalletDelegate
import ru.terrakok.cicerone.Router

class MainScreenViewModel(
        financeInteractor: FinanceInteractor,
        private val router: Router
) : ViewModel() {

    val items: LiveData<List<ListItem>> = Transformations.map(financeInteractor.getWallets()) {
        val list: MutableList<ListItem> = it.map { wallet ->
            WalletDelegate.Model(wallet.id.toString(), wallet)
        }.toMutableList()
        list.add(AddWalletDelegate.Model())
        list
    }

    fun openWalletScreen(walletId: Long) {
        router.navigateTo(WalletFragmentScreen(walletId))
    }

    fun openWalletCreationScreen() {
        router.navigateTo(WalletCreationScreen())
    }
}