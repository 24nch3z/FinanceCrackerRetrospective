package ru.s4nchez.financecrackerretrospective.di.finance

import dagger.Module
import dagger.Provides
import ru.s4nchez.financecrackerretrospective.di.FeatureScope
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractorImpl
import ru.s4nchez.financecrackerretrospective.presentation.main.viewmodel.WalletViewModelFactory
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.viewmodel.WalletCreationViewModelFactory

@Module
class FinanceModule {

    @FeatureScope
    @Provides
    fun provideFinanceInteractor(): FinanceInteractor {
        return FinanceInteractorImpl()
    }

    @Provides
    fun provideWalletViewModelFactory(
            financeInteractor: FinanceInteractor): WalletViewModelFactory {
        return WalletViewModelFactory(financeInteractor)
    }

    @Provides
    fun provideWalletCreationViewModelFactory(
            financeInteractor: FinanceInteractor): WalletCreationViewModelFactory {
        return WalletCreationViewModelFactory(financeInteractor)
    }
}