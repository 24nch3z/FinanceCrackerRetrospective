package ru.s4nchez.financecrackerretrospective.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long?,

        @ColumnInfo(name = "value")
        val value: Double,

        @ColumnInfo(name = "description")
        val description: String,

        @ColumnInfo(name = "categoryId")
        val categoryId: Long,

        @ColumnInfo(name = "walletId")
        val walletId: Long,

        @ColumnInfo(name = "date")
        val date: Long
)