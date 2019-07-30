package ru.s4nchez.financecrackerretrospective.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.s4nchez.financecrackerretrospective.data.model.Category

@Dao
interface CategoryDao : BaseDao<Category> {

    @Query("SELECT * FROM Category")
    fun getAll(): LiveData<List<Category>>

    @Query("SELECT * FROM Category WHERE id = :id")
    fun getById(id: Long): Category
}