package com.example.articles.repository

import com.example.articles.model.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET


interface APIService {

    @GET("blogs?page=1&limit=10")
    fun getListOfNews(): Call<List<ArticleResponse>>

}