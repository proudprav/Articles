package com.example.articles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.articles.ui.view.ArticleFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ArticleFragment.newInstance())
                .commitNow()
        }
    }
}