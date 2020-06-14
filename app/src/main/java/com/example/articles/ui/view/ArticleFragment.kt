package com.example.articles.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.articles.R
import com.example.articles.model.ArticleResponse
import com.example.articles.ui.viewmodel.ArticleListViewModel
import com.example.articles.utils.ViewModelFactory
import kotlinx.android.synthetic.main.article_fragment.*

class ArticleFragment : Fragment() {

    companion object {
        fun newInstance() = ArticleFragment()
    }

    private lateinit var viewModel: ArticleListViewModel
    private  var adapter = ArticleListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.article_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleListViewModel::class.java)
        rv_article_list.adapter = adapter

        viewModel.getListOfArticle().observe(this, Observer {
            adapter.updateList(it as ArrayList<ArticleResponse>)
        })


    }

}