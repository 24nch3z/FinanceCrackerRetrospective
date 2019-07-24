package ru.s4nchez.financecrackerretrospective.presentation.common

import androidx.fragment.app.Fragment
import ru.s4nchez.financecrackerretrospective.presentation.main.MainScreenFragment
import ru.s4nchez.financecrackerretrospective.presentation.wallet.WalletFragment
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.WalletCreationFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MainListScreen : SupportAppScreen() {
    override fun getFragment() = MainScreenFragment()
}

class WalletCreationScreen : SupportAppScreen() { // TODO: Пока только создание
    override fun getFragment(): Fragment {
        return WalletCreationFragment.createNewWallet()
    }
}

class WalletFragmentScreen(private val walletId: Long) : SupportAppScreen() {
    override fun getFragment(): Fragment {
        return WalletFragment.newInstance(walletId)
    }
}