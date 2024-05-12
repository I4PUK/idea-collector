package com.mishukoffs.ideacollector.model

import com.mishukoffs.ideacollector.R
import java.util.Date

data class IdeaModel(
    val title: String,
    var createdDate: Date,
    val priority: IdeaPriority
) {
    fun getDrawableFromPriority(): Int {
        val iconResource = when (priority) {
            IdeaPriority.HIGH -> {
                R.drawable.circle_two_tone_red
            }

            IdeaPriority.MEDIUM -> {
                R.drawable.circle_two_tone_orange
            }

            IdeaPriority.LOW -> {
                R.drawable.circle_two_tone_blue
            }
        }

        return iconResource
    }
}