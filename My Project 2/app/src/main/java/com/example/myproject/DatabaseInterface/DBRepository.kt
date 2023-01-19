package com.example.myproject

import androidx.lifecycle.LiveData
import com.example.myproject.Database.AppDatabase
import com.example.myproject.Database.Entity.ProblemsEntity
import com.example.myproject.DatabaseInterface.Transformer.convertArticleModelToArticleEntity
import com.example.myproject.Model.Problems
import javax.inject.Inject

class DBRepository @Inject constructor(val appDatabase: AppDatabase) {

    suspend fun insertArticle(article: Problems): Long {
        return appDatabase.articleDao()
            .insert(convertArticleModelToArticleEntity(article))
    }

    suspend fun delete(article: Problems) {
        appDatabase.articleDao().delete(convertArticleModelToArticleEntity(article))
    }

    // NOTE - Since we are already using LIVE-DATA no need to use suspend function
    fun getAllArticles(): LiveData<List<ProblemsEntity>> {
        return appDatabase.articleDao().getAllOfflineProblems()
    }


}