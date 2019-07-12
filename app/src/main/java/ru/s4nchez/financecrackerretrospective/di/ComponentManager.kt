package ru.s4nchez.financecrackerretrospective.di

import android.content.Context
import ru.s4nchez.financecrackerretrospective.di.app.AppComponent
import ru.s4nchez.financecrackerretrospective.di.app.AppModule
import ru.s4nchez.financecrackerretrospective.di.app.DaggerAppComponent
import ru.s4nchez.financecrackerretrospective.di.finance.FinanceComponent
import ru.s4nchez.financecrackerretrospective.di.finance.FinanceModule

class ComponentManager(private val context: Context) {

    private val rootComponent: AppComponent = buildAppComponent()
    private var financeComponent: FinanceComponent? = null

    private fun buildAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()
    }

    fun buildFinanceComponent(): FinanceComponent {
        return financeComponent ?: rootComponent.plusFinance(FinanceModule())
                .also { financeComponent = it }
    }

    fun destroyFinanceComponent() {
        financeComponent = null
    }
}