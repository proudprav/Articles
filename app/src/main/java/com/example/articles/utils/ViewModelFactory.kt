package com.example.articles.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.articles.application.ArticleApplication
import com.example.articles.di.APIComponent
import com.example.articles.repository.RetrofitRepository
import com.example.articles.ui.viewmodel.ArticleListViewModel

import javax.inject.Inject

class ViewModelFactory : ViewModelProvider.Factory {
    @Inject
    lateinit var retrofitRepository: RetrofitRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val apiComponent: APIComponent = ArticleApplication.apiComponent
        apiComponent.inject(this)
        if (modelClass.isAssignableFrom(ArticleListViewModel::class.java)) {
            return ArticleListViewModel(retrofitRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
