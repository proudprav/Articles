package com.example.articles.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.articles.repository.RetrofitRepository
import com.example.articles.utils.Utility.Companion.PAGE_LIMIT

class ArticleListViewModel(private val retrofitRepository: RetrofitRepository) : ViewModel() {
    private var pageNumber : Int = 1



    fun getListOfArticle() = retrofitRepository.fetchListOfArticle(pageNumber, PAGE_LIMIT)


    fun getMoreArticles(){
        pageNumber++
        getListOfArticle()
    }
}