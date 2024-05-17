package com.mishukoffs.ideacollector

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.mishukoffs.ideacollector.idea_list.IdeaListFragment
import com.mishukoffs.ideacollector.idea_list.view_model.IdeaListViewModel
import com.mishukoffs.ideacollector.idea_list.view_model.IdeaListViewModelFactory


class MainActivity : FragmentActivity() {
    private val ideaListViewModel: IdeaListViewModel by viewModels{
        IdeaListViewModelFactory((application as IdeaListApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val frameLayout = R.id.frame_layout

        println(ideaListViewModel.toString())

        supportFragmentManager.beginTransaction()
            .replace(frameLayout, IdeaListFragment()).commit()
    }
}