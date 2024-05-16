package com.mishukoffs.ideacollector.idea_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mishukoffs.ideacollector.repository.IdeaRepository

class IdeaListViewModelFactory(private val repository: IdeaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IdeaListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IdeaListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}