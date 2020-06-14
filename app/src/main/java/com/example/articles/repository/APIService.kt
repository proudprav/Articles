package com.example.articles.repository

import com.example.articles.model.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {

    @GET("blogs")
    fun getListOfNews(@Query("page") pageNumber : Int, @Query("limit") pageLimit : Int): Call<List<ArticleResponse>>

}