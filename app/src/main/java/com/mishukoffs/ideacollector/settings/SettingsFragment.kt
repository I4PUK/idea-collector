package com.mishukoffs.ideacollector.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.mishukoffs.ideacollector.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}