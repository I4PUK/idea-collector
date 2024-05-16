package com.mishukoffs.ideacollector.model

import com.mishukoffs.ideacollector.R

enum class IdeaPriority {
    HIGH,
    MEDIUM,
    LOW;

    fun getDrawableFromPriority(): Int {
        val iconResource = when (this) {
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