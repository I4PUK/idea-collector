package com.mishukoffs.ideacollector.model

import java.util.Date

enum class IdeaStatus{
    red,
    yellow,
    green,
}

data class IdeaModel(val title: String, var createdDate: Date, val status: IdeaStatus) {}