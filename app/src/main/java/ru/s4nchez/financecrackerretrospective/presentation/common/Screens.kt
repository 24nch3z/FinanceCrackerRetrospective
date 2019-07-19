package ru.s4nchez.financecrackerretrospective.presentation.common

import androidx.fragment.app.Fragment
import ru.s4nchez.financecrackerretrospective.presentation.main.MainFragment
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.WalletCreationFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MainListScreen : SupportAppScreen() {
    override fun getFragment() = MainFragment()
}

class WalletCreationScreen : SupportAppScreen() { // TODO: Пока только создание
    override fun getFragment(): Fragment {
        return WalletCreationFragment.createNewWallet()
    }
}