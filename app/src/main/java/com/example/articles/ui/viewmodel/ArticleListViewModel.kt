package com.example.articles.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.articles.repository.RetrofitRepository

class ArticleListViewModel(private val retrofitRepository: RetrofitRepository) : ViewModel() {
    // TODO: Implement the ViewModel

    init {
        retrofitRepository.fetchListOfArticle()
    }
}