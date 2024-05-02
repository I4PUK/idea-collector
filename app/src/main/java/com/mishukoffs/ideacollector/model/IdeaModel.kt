package com.mishukoffs.ideacollector.model

import com.mishukoffs.ideacollector.R
import java.util.Date

data class IdeaModel(val title: String, var createdDate: Date, val status: IdeaStatus) {
    fun getDrawableFromStatus(): Int {
        val iconResource = when (status) {
            IdeaStatus.HIGH -> {
                R.drawable.circle_two_tone_red
            }

            IdeaStatus.MEDIUM -> {
                R.drawable.circle_two_tone_orange
            }

            IdeaStatus.LOW -> {
                R.drawable.circle_two_tone_blue
            }
        }

        return iconResource
    }
}