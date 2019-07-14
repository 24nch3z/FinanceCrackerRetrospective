package ru.s4nchez.financecrackerretrospective.di.finance

import dagger.Module
import dagger.Provides
import ru.s4nchez.financecrackerretrospective.data.AppDatabase
import ru.s4nchez.financecrackerretrospective.data.repository.FinanceRepository
import ru.s4nchez.financecrackerretrospective.data.repository.FinanceRepositoryImpl
import ru.s4nchez.financecrackerretrospective.di.FeatureScope
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractorImpl
import ru.s4nchez.financecrackerretrospective.presentation.main.viewmodel.WalletViewModelFactory
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.viewmodel.WalletCreationViewModelFactory

@Module
class FinanceModule {

    @FeatureScope
    @Provides
    fun provideFinanceRepository(appDatabase: AppDatabase): FinanceRepository {
        return FinanceRepositoryImpl(appDatabase)
    }

    @FeatureScope
    @Provides
    fun provideFinanceInteractor(financeRepository: FinanceRepository): FinanceInteractor {
        return FinanceInteractorImpl(financeRepository)
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