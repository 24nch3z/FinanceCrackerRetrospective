package ru.s4nchez.financecrackerretrospective.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.s4nchez.financecrackerretrospective.data.dao.CategoryDao
import ru.s4nchez.financecrackerretrospective.data.dao.TransactionDao
import ru.s4nchez.financecrackerretrospective.data.dao.WalletDao
import ru.s4nchez.financecrackerretrospective.data.model.Category
import ru.s4nchez.financecrackerretrospective.data.model.Transaction
import ru.s4nchez.financecrackerretrospective.data.model.Wallet

@Database(entities = [Wallet::class, Category::class, Transaction::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
}