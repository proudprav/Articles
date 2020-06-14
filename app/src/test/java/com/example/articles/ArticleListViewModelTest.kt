package com.example.articles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.articles.model.ArticleResponse
import com.example.articles.repository.RetrofitRepository
import com.example.articles.ui.viewmodel.ArticleListViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import kotlin.collections.ArrayList

@RunWith(MockitoJUnitRunner::class)
class ArticleListViewModelTest {

    @Mock
    lateinit var retrofitRepository : RetrofitRepository
    @Mock
    lateinit var viewModel: ArticleListViewModel
    private val mockLiveData = MutableLiveData<List<ArticleResponse>>()
    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Before
    fun setup()
    {
        MockitoAnnotations.initMocks(this)
        viewModel = ArticleListViewModel(
            retrofitRepository
        )
        mockData()
    }
    @Test
    fun getListOfArticleNullTest() {
        Mockito.`when`(retrofitRepository.fetchListOfArticle(1,10)).thenReturn(null)
        Assert.assertNull(viewModel.getListOfArticle())
        viewModel.getMoreArticles()
        Assert.assertNull(viewModel.getListOfArticle())

    }

    @Test
    fun getListOfArticleNotNullTest() {
        Mockito.`when`(retrofitRepository.fetchListOfArticle(1,10)).thenReturn(mockLiveData)
        Assert.assertNotNull(viewModel.getListOfArticle())
        Assert.assertNotNull(viewModel.getListOfArticle().hasActiveObservers())
    }

    private fun mockData(){
        val arrayList = ArrayList<ArticleResponse>()
        arrayList.add(ArticleResponse(9, Date(),"Lorem Ipsem",89,2, ArrayList(),ArrayList()))
        mockLiveData.postValue(arrayList)

    }



}