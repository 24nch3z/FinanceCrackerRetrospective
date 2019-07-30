package ru.s4nchez.financecrackerretrospective.di.app

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import ru.s4nchez.financecrackerretrospective.data.AppDatabase
import ru.s4nchez.financecrackerretrospective.data.model.Category
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
    fun provideDatabase(executor: Executor): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val appDb = provideDatabase(executor)
                        executor.run {
                            appDb.categoryDao().insert(Category(name = "Еда", isDefault = true))
                            appDb.categoryDao().insert(Category(name = "Женщины", isDefault = true))
                            appDb.categoryDao().insert(Category(name = "Другое", isDefault = true))
                        }
                    }
                })
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