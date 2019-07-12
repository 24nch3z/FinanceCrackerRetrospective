package ru.s4nchez.financecrackerretrospective.di.finance

import dagger.Subcomponent
import ru.s4nchez.financecrackerretrospective.di.FeatureScope
import ru.s4nchez.financecrackerretrospective.presentation.main.MainFragment

@FeatureScope
@Subcomponent(modules = [FinanceModule::class])
interface FinanceComponent {
    fun inject(view: MainFragment)
}