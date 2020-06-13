package com.example.articles.application

import android.app.Application
import android.content.Context
import com.example.articles.di.APIComponent
import com.example.articles.di.APIModule
import com.example.articles.di.DaggerAPIComponent
import com.example.articles.repository.ApiURL


class ArticleApplication : Application() {

    companion object {
        var ctx: Context? = null
        lateinit var apiComponent: APIComponent

    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        apiComponent = initDaggerComponent()
    }

    fun initDaggerComponent(): APIComponent {
        apiComponent = DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(ApiURL.BASE_URL))
            .build()
        return apiComponent

    }
}