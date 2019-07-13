package ru.s4nchez.financecrackerretrospective.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Wallet(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long = NEW_WALLET_ID,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "currency")
        val currency: String
) {

    companion object {
        const val NEW_WALLET_ID = -1L
        fun empty() = Wallet(NEW_WALLET_ID, "", "")
    }
}