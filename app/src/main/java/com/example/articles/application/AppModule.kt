package com.example.articles.application

import dagger.Module
import dagger.Provides

@Module
class AppModule constructor(private var myApplication: ArticleApplication) {

    @Provides
    fun provideMyRetroApplication(): ArticleApplication {
        return myApplication
    }
}