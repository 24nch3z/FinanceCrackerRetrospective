package ru.s4nchez.financecrackerretrospective

import android.app.Application
import ru.s4nchez.financecrackerretrospective.di.ComponentManager

class MyApp : Application() {

    lateinit var componentManager: ComponentManager

    override fun onCreate() {
        super.onCreate()
        componentManager = ComponentManager(this)
    }
}