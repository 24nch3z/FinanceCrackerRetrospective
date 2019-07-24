package ru.s4nchez.financecrackerretrospective.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T): Long

    @Update
    fun update(item: T)

    @Delete
    fun delete(item: T)
}