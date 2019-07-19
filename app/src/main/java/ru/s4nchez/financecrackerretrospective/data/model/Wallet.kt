package ru.s4nchez.financecrackerretrospective.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Wallet")
data class Wallet(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long?,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "currency")
        val currency: String,

        @ColumnInfo(name = "balance")
        val balance: Long
) {

    companion object {
        fun empty() = Wallet(null, "", "", 0L)
    }
}