package ru.s4nchez.financecrackerretrospective.data.dao

import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {

    @Insert
    fun insert(vararg item: T)

    @Update
    fun update(vararg item: T)
}