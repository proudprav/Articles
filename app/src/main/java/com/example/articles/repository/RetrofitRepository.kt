package com.example.articles.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.articles.application.ArticleApplication
import com.example.articles.di.APIComponent
import com.example.articles.model.ArticleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitRepository {
    private var apiComponent: APIComponent = ArticleApplication.apiComponent
    private var listOfArticle = MutableLiveData<List<ArticleResponse>>()

    @Inject
    lateinit var retrofit: Retrofit

    init {
        apiComponent.inject(this)
    }

    fun fetchListOfArticle(pageNumber: Int, pageLimit: Int): LiveData<List<ArticleResponse>> {
        val apiService: APIService = retrofit.create(APIService::class.java)
        val listOfNews = apiService.getListOfNews(pageNumber, pageLimit)
        listOfNews.enqueue(object : Callback<List<ArticleResponse>> {
            override fun onResponse(
                call: Call<List<ArticleResponse>>,
                response: Response<List<ArticleResponse>>
            ) {
                listOfArticle.value = response.body()
            }

            override fun onFailure(call: Call<List<ArticleResponse>>, t: Throwable) {
                Log.d("RetroRepository", "Failed:::" + t.message)
            }
        })

        return listOfArticle

    }

}

