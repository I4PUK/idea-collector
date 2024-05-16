package com.mishukoffs.ideacollector.idea_list

import androidx.recyclerview.widget.DiffUtil
import com.mishukoffs.ideacollector.database.IdeaEntity

class IdeaComparator(private val oldList: List<IdeaEntity>, private val newList: List<IdeaEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return(oldItem.text == newItem.text && oldItem.createdDate == newItem.createdDate && oldItem.priority == newItem.priority)
    }
}