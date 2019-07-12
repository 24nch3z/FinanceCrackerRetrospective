package ru.s4nchez.financecrackerretrospective.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Wallet(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "currency")
        val currency: String
)