package com.mishukoffs.ideacollector.idea_list

import androidx.lifecycle.ViewModel
import com.mishukoffs.ideacollector.model.IdeaModel
import com.mishukoffs.ideacollector.model.IdeaPriority
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Calendar
import java.util.Date

class IdeaListViewModel : ViewModel() {
    private val initialListValue = mutableListOf(
        IdeaModel(
            title = "Idea 1",
            createdDate = getDate(2022, 11, 2),
            priority = IdeaPriority.HIGH
        ),
        IdeaModel(
            title = "Idea 2",
            createdDate = getDate(2023, 4, 9),
            priority = IdeaPriority.MEDIUM
        ),
        IdeaModel(
            title = "Idea 3",
            createdDate = getDate(2024, 2, 29),
            priority = IdeaPriority.LOW
        )
    )

    private val _selectedPriority = MutableStateFlow(IdeaPriority.MEDIUM)
    private val _list = MutableStateFlow(initialListValue)
    var list
        get() = _list.value
        set(value) {
            _list.value = value
        }

    val selectedPriority get() = _selectedPriority

    fun updatePriority(newPriority: IdeaPriority) {
        _selectedPriority.value = newPriority
    }

    fun addIdea(text: String) {
        _list.value.add(
            IdeaModel(
                title = text,
                priority = _selectedPriority.value,
                createdDate = getNowDate()
            )
        )
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