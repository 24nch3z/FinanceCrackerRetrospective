package ru.s4nchez.financecrackerretrospective.di.app

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.s4nchez.financecrackerretrospective.data.AppDatabase
import ru.s4nchez.financecrackerretrospective.executor.Executor
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class AppModule(private val context: Context, private val router: Router) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .build()
    }

    @Provides
    @Singleton
    fun provideExecutor(): Executor {
        return Executor()
    }

    @Provides
    @Singleton
    fun provideRouter(): Router {
        return router
    }
}