package ru.s4nchez.financecrackerretrospective.di.finance

import dagger.Module
import dagger.Provides
import ru.s4nchez.financecrackerretrospective.data.AppDatabase
import ru.s4nchez.financecrackerretrospective.data.repository.FinanceRepository
import ru.s4nchez.financecrackerretrospective.data.repository.FinanceRepositoryImpl
import ru.s4nchez.financecrackerretrospective.di.FeatureScope
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractor
import ru.s4nchez.financecrackerretrospective.domain.FinanceInteractorImpl
import ru.s4nchez.financecrackerretrospective.executor.Executor
import ru.s4nchez.financecrackerretrospective.presentation.main.viewmodel.MainScreenViewModelFactory
import ru.s4nchez.financecrackerretrospective.presentation.wallet.viewmodel.WalletViewModelFactory
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.viewmodel.WalletCreationViewModelFactory
import ru.terrakok.cicerone.Router

@Module
class FinanceModule {

    @FeatureScope
    @Provides
    fun provideFinanceRepository(appDatabase: AppDatabase): FinanceRepository {
        return FinanceRepositoryImpl(appDatabase)
    }

    @FeatureScope
    @Provides
    fun provideFinanceInteractor(financeRepository: FinanceRepository, executor: Executor): FinanceInteractor {
        return FinanceInteractorImpl(financeRepository, executor)
    }

    @Provides
    fun provideMainScreenViewModelFactory(
            financeInteractor: FinanceInteractor,
            router: Router): MainScreenViewModelFactory {
        return MainScreenViewModelFactory(financeInteractor, router)
    }

    @Provides
    fun provideWalletCreationViewModelFactory(
            financeInteractor: FinanceInteractor,
            router: Router): WalletCreationViewModelFactory {
        return WalletCreationViewModelFactory(financeInteractor, router)
    }

    @Provides
    fun provideWalletViewModelFactory(
            financeInteractor: FinanceInteractor,
            router: Router): WalletViewModelFactory {
        return WalletViewModelFactory(financeInteractor, router)
    }
}