package com.mishukoffs.ideacollector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mishukoffs.ideacollector.model.IdeaModel
import com.mishukoffs.ideacollector.model.IdeaStatus
import java.util.Calendar
import java.util.Date

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IdeaListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IdeaListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_idea_list, container, false)

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
        val ideaStatusValues = IdeaStatus.entries.toTypedArray()
        val customAdapter = CustomAdapter(list)
        val spinnerAdapter = ArrayAdapter(
            view.context, android.R.layout.simple_spinner_dropdown_item,
            ideaStatusValues
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val ideaTextView: TextView = view.findViewById(R.id.idea_text_field)
        val addIdeaButton: ImageButton = view.findViewById(R.id.add_idea_button)
        val settingsButton: Button = view.findViewById(R.id.settings_button)
        val statusesList: Spinner = view.findViewById(R.id.status_dropdown)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.adapter = customAdapter

        statusesList.adapter = spinnerAdapter


        settingsButton.setOnClickListener {
            val fragment = SettingsFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.addToBackStack("main_to_settings")
            transaction.commit()
        }

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
        return view
    }


    private fun getNowDate(): Date {
        val instance = Calendar.getInstance()
        return instance.time
    }

    private fun getDate(year: Int, month: Int, date: Int): Date {
        val instance = Calendar.getInstance()
        instance.set(year, month, date)
        return instance.time
    }
}