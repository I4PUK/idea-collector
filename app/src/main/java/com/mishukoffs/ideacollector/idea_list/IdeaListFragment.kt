package com.mishukoffs.ideacollector.idea_list

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.mishukoffs.ideacollector.databinding.FragmentIdeaListBinding
import com.mishukoffs.ideacollector.idea_list.view_model.IdeaListViewModel
import com.mishukoffs.ideacollector.model.IdeaPriority
import com.mishukoffs.ideacollector.settings.SettingsFragment


class IdeaListFragment : Fragment() {
    private var _binding: FragmentIdeaListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: IdeaListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[IdeaListViewModel::class.java]

        val oldDataset = viewModel.list.toList()
        val ideaListAdapter = IdeaListAdapter(viewModel.list)
        val spinnerAdapter = ArrayAdapter(
            binding.root.context,
            R.layout.simple_spinner_dropdown_item,
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
                viewModel.insert(ideaText)
                binding.ideaTextField.clearFocus()
                binding.ideaTextField.text.clear()
//
//                val newDataset = viewModel.list
//                val ideaListDiff = IdeaComparator()
//                val diffResult = DiffUtil.calculateDiff(ideaListDiff, true)
//
//
//                diffResult.dispatchUpdatesTo(ideaListAdapter)
            }
        }

        binding.settingsButton.setOnClickListener {
            val fragment = SettingsFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(com.mishukoffs.ideacollector.R.id.frame_layout, fragment)
            transaction.addToBackStack("main_to_settings")
            transaction.commit()
        }

        viewModel.allIdeas.observe(viewLifecycleOwner) { ideas ->
            ideas.let {
                val ideaListDiff = IdeaComparator(oldList = oldDataset, newList = it)
                val diffResult = DiffUtil.calculateDiff(ideaListDiff, true)

                diffResult.dispatchUpdatesTo(ideaListAdapter)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIdeaListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}