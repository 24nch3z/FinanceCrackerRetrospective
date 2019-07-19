package ru.s4nchez.financecrackerretrospective.di.app

import dagger.Component
import ru.s4nchez.financecrackerretrospective.di.finance.FinanceComponent
import ru.s4nchez.financecrackerretrospective.di.finance.FinanceModule
import ru.s4nchez.financecrackerretrospective.presentation.common.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(view: MainActivity)
    fun plusFinance(module: FinanceModule): FinanceComponent
}