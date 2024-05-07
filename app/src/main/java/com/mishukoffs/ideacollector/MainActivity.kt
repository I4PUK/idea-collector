package com.mishukoffs.ideacollector

import android.os.Bundle
import androidx.fragment.app.FragmentActivity


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val frameLayout = R.id.frame_layout

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, IdeaListFragment()).commit()
    }
}