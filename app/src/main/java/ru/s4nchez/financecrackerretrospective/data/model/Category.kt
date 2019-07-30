package ru.s4nchez.financecrackerretrospective.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long? = null,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "isDefault")
        val isDefault: Boolean
)