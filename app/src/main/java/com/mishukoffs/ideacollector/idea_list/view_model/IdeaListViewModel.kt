package com.mishukoffs.ideacollector.idea_list.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mishukoffs.ideacollector.database.IdeaEntity
import com.mishukoffs.ideacollector.model.IdeaPriority
import com.mishukoffs.ideacollector.repository.IdeaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class IdeaListViewModel(val repository: IdeaRepository) : ViewModel() {
    private val initialListValue = mutableListOf(
        IdeaEntity(
            id = 1,
            text = "Idea 1",
            createdDate = getDate(2022, 11, 2).toString(),
            priority = IdeaPriority.HIGH
        )
    )

    val allIdeas: LiveData<List<IdeaEntity>> = repository.allIdeas.asLiveData()

    fun insert(text: String) = viewModelScope.launch {
        val newIdea = IdeaEntity(
            id = 0,
            text = text,
            priority = _selectedPriority.value,
            createdDate = getNowDate().toString()
        )
        repository.insert(newIdea)
    }

    private val _selectedPriority = MutableStateFlow(IdeaPriority.MEDIUM)
    private val _list = MutableStateFlow(initialListValue)
    var list
        get() = _list.value
        set(value) {
            _list.value = value
        }

    fun updatePriority(newPriority: IdeaPriority) {
        _selectedPriority.value = newPriority
    }

    private fun getNowDate(): Date {
        val instance = Calendar.getInstance()
        return instance.time
    }

    private fun getDate(year: Int, month: Int, date: Int): Date {
        val instance = Calendar.getInstance()
        instance.set(year, month, date)
        return instance.time
    }
}

