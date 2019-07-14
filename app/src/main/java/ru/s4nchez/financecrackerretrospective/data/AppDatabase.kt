package ru.s4nchez.financecrackerretrospective.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.s4nchez.financecrackerretrospective.data.dao.WalletDao
import ru.s4nchez.financecrackerretrospective.data.model.Wallet

@Database(entities = [Wallet::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao
}