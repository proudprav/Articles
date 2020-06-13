package com.example.articles.di

import com.example.articles.application.AppModule
import com.example.articles.repository.RetrofitRepository
import com.example.articles.utils.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, APIModule::class])
interface APIComponent {

    fun inject(retrofitRepository: RetrofitRepository)

    fun inject(retrofitRepository: ViewModelFactory)

}