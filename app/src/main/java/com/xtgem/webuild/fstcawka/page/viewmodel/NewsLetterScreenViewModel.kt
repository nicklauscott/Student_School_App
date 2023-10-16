package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.News

class NewsLetterScreenViewModel: ViewModel() {
    private val repository = Repository.get()

    val allNews: LiveData<DataResult<List<News>>> =
        repository.getAllNews()
}