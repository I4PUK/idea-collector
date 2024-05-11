package com.mishukoffs.ideacollector.idea_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.mishukoffs.ideacollector.IdeaListAdapter
import com.mishukoffs.ideacollector.ItemListDiffIdeaCallback
import com.mishukoffs.ideacollector.R
import com.mishukoffs.ideacollector.SettingsFragment
import com.mishukoffs.ideacollector.databinding.FragmentIdeaListBinding
import com.mishukoffs.ideacollector.model.IdeaPriority


class IdeaListFragment : Fragment() {
    private var _binding: FragmentIdeaListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: IdeaListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIdeaListBinding.inflate(inflater, container, false)

        val ideaListAdapter = IdeaListAdapter(viewModel.list)

        val spinnerAdapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_dropdown_item,
            IdeaPriority.entries.toTypedArray<IdeaPriority>()
        )

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 1)
            adapter = ideaListAdapter
        }

        binding.priorityDropdown.apply {
            adapter = spinnerAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    viewModel.updatePriority(IdeaPriority.entries[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        binding.addIdeaButton.setOnClickListener {
            val ideaText: String = binding.ideaTextField.text.toString()
            if (ideaText.trim().isNotEmpty()) {
                val oldDataset = viewModel.list

                viewModel.addIdea(ideaText)
                binding.ideaTextField.clearFocus()
                binding.ideaTextField.text.clear()

                val newDataset = viewModel.list
                val ideaListDiff = ItemListDiffIdeaCallback(oldDataset, newDataset)
                val diffResult = DiffUtil.calculateDiff(ideaListDiff, true)


                diffResult.dispatchUpdatesTo(ideaListAdapter)
            }
        }

        binding.settingsButton.setOnClickListener {
            val fragment = SettingsFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.addToBackStack("main_to_settings")
            transaction.commit()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}