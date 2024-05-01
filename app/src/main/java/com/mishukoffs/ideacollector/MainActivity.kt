package com.mishukoffs.ideacollector

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mishukoffs.ideacollector.model.IdeaModel
import com.mishukoffs.ideacollector.model.IdeaStatus
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val list: List<IdeaModel> =
            listOf(
                IdeaModel(title = "Idea 1", createdDate = getDate(2022, 11, 2), status = IdeaStatus.red),
                IdeaModel(title = "Idea 2", createdDate = getDate(2023, 4, 9), status = IdeaStatus.yellow),
                IdeaModel(title = "Idea 3", createdDate = getDate(2024, 2, 29), status = IdeaStatus.green)
            )
        val customAdapter = CustomAdapter(list)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = customAdapter
    }

    fun getDate(year: Int, month: Int, date: Int): Date {
        val instance = Calendar.getInstance()
        instance.set(year, month, date)
        return instance.time
    }
}