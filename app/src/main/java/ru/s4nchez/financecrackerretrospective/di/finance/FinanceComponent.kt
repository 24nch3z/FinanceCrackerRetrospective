package ru.s4nchez.financecrackerretrospective.di.finance

import dagger.Subcomponent
import ru.s4nchez.financecrackerretrospective.di.FeatureScope
import ru.s4nchez.financecrackerretrospective.presentation.main.MainFragment
import ru.s4nchez.financecrackerretrospective.presentation.wallet.WalletFragment
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.WalletCreationFragment

@FeatureScope
@Subcomponent(modules = [FinanceModule::class])
interface FinanceComponent {
    fun inject(view: MainFragment)
    fun inject(view: WalletCreationFragment)
    fun inject(view: WalletFragment)
}