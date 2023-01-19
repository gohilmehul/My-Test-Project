package com.example.myproject.Model


import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproject.DBRepository
import com.example.myproject.DatabaseInterface.Transformer
import com.example.myproject.Network.DataHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfflineViewModel @Inject constructor(private val dbRepository: DBRepository) : ViewModel() {


    var article = Transformations.map(dbRepository.getAllArticles()) { list ->

        val temp = list.map {
            Transformer.convertArticleEntityToArticleModel(it)
        }
        if (temp.isNullOrEmpty()) {
            DataHandler.ERROR(null, "LIST IS EMPTY OR NULL")
        } else {
            DataHandler.SUCCESS(temp)
        }
    }


    fun insertArticle(article: Problems) {
        viewModelScope.launch {

            dbRepository.insertArticle(article)
        }
    }

    fun deleteArticle(article: Problems) {
        viewModelScope.launch {

            dbRepository.delete(article)
        }
    }

    fun getAllArticles() = dbRepository.getAllArticles()


}