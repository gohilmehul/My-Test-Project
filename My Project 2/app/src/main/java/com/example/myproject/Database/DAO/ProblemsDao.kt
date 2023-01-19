package com.example.myproject.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myproject.Database.Entity.ProblemsEntity


@Dao
interface ProblemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(problemsEntity :  ProblemsEntity):Long

    @Query("SELECT * FROM PROBLEMS")
    fun getAllOfflineProblems():LiveData<List<ProblemsEntity>>

    @Delete
    suspend fun delete(articleEntity: ProblemsEntity)
}