package com.example.myproject.DatabaseInterface


import com.example.myproject.Database.Entity.ProblemsEntity
import com.example.myproject.Model.Problems



object Transformer {

    fun convertArticleModelToArticleEntity(article: Problems): ProblemsEntity {
        return ProblemsEntity(
            name = article.name,
            dose = article.dose,
            strength = article.strength,


        )
    }

    fun convertArticleEntityToArticleModel(articleEntity: ProblemsEntity): Problems {
        return Problems(
            name = articleEntity.name,
            dose = articleEntity.dose,
            strength = articleEntity.strength


        )
    }



}