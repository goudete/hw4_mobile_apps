package com.example.hw4

import androidx.room.*

@Dao
interface DreamDao {
    @Query("SELECT * FROM dream_table ORDER BY id ASC")
    suspend fun getAll(): List<Dream>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDream(dream: Dream)

    @Query("UPDATE dream_table SET title=:title, content=:content, reflection=:reflection, emotion=:emotion WHERE id=:id")
    suspend fun updateById(id: Int, title: String, content: String, reflection: String, emotion: String)

    @Query("DELETE FROM dream_table WHERE id=:id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM dream_table WHERE id = :id")
    suspend fun getById(id: Int): List<Dream>
}