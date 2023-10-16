package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.News
import java.util.UUID

class NewsLetterDetailScreenViewModel(newsId: UUID): ViewModel() {

    private val repository = Repository.get()
    val news: LiveData<DataResult<News>> = repository.getNews(newsId)

}

class NewsLetterDetailScreenViewModelFactory(private val newsId: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsLetterDetailScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsLetterDetailScreenViewModel(UUID.fromString(newsId)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}