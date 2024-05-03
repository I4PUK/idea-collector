package com.mishukoffs.ideacollector

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mishukoffs.ideacollector.model.IdeaModel
import com.mishukoffs.ideacollector.model.IdeaStatus
import java.util.Calendar
import java.util.Date


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val list: MutableList<IdeaModel> =
            mutableListOf(
                IdeaModel(
                    title = "Idea 1",
                    createdDate = getDate(2022, 11, 2),
                    status = IdeaStatus.HIGH
                ),
                IdeaModel(
                    title = "Idea 2",
                    createdDate = getDate(2023, 4, 9),
                    status = IdeaStatus.MEDIUM
                ),
                IdeaModel(
                    title = "Idea 3",
                    createdDate = getDate(2024, 2, 29),
                    status = IdeaStatus.LOW
                )
            )

        val customAdapter = CustomAdapter(list)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val ideaTextView: TextView = findViewById(R.id.idea_text_field)
        val addIdeaButton: ImageButton = findViewById(R.id.add_idea_button)
        val statusesList: Spinner = findViewById(R.id.status_dropdown)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = customAdapter

        statusesList.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            IdeaStatus.entries
        )

        addIdeaButton.setOnClickListener {
            val ideaTextField: String = ideaTextView.text.toString()
            if (ideaTextField.trim().isNotEmpty()) {
                list.add(
                    IdeaModel(
                        // statusesList.getSelectedItem()
                        title = ideaTextField,
                        status = IdeaStatus.MEDIUM,
                        createdDate = getNowDate()
                    )
                )

                ideaTextView.text = ""
                ideaTextView.clearFocus()

                customAdapter.notifyDataSetChanged()
            }
        }


    }

    fun getNowDate(): Date {
        val instance = Calendar.getInstance()
        return instance.time
    }

    fun getDate(year: Int, month: Int, date: Int): Date {
        val instance = Calendar.getInstance()
        instance.set(year, month, date)
        return instance.time
    }
}