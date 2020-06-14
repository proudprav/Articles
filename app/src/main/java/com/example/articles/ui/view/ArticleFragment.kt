package com.example.articles.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.articles.R
import com.example.articles.model.ArticleResponse
import com.example.articles.ui.viewmodel.ArticleListViewModel
import com.example.articles.utils.PaginationScrollListener
import com.example.articles.utils.ViewModelFactory
import kotlinx.android.synthetic.main.article_fragment.*

class ArticleFragment : Fragment() {

    companion object {
        fun newInstance() = ArticleFragment()
    }

    private lateinit var viewModel: ArticleListViewModel
    private  var adapter = ArticleListAdapter()
    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.article_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProvider(this,viewModelFactory).get(ArticleListViewModel::class.java)
        rv_article_list.adapter = adapter

        viewModel.getListOfArticle().observe(viewLifecycleOwner, Observer {
            adapter.updateList(it as ArrayList<ArticleResponse>)
        })

        val linearLayoutManager = LinearLayoutManager(this.context)
        rv_article_list.layoutManager = linearLayoutManager
        rv_article_list?.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                //you have to call loadmore items to get more data
                viewModel.getMoreItems()
            }
        })
    }

}