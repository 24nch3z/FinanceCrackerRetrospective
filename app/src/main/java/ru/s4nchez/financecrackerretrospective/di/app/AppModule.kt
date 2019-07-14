package ru.s4nchez.financecrackerretrospective.di.app

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.s4nchez.financecrackerretrospective.data.AppDatabase
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .allowMainThreadQueries() // TODO: Убрать
                .build()
    }
}