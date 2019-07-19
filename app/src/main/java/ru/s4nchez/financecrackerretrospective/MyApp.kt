package ru.s4nchez.financecrackerretrospective

import android.app.Application
import ru.s4nchez.financecrackerretrospective.di.ComponentManager
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class MyApp : Application() {

    lateinit var componentManager: ComponentManager
    private lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        cicerone = Cicerone.create()
        componentManager = ComponentManager(this, cicerone.router)
    }

    fun getNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }
}