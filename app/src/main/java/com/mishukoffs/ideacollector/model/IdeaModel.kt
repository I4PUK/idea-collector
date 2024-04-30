package com.mishukoffs.ideacollector.model

import java.time.LocalDate

enum class IdeaStatus{
    red,
    yellow,
    green,
}

data class IdeaModel(val title: String, var createdDate: LocalDate, val status: IdeaStatus) {}