package com.mishukoffs.ideacollector

import androidx.recyclerview.widget.DiffUtil
import com.mishukoffs.ideacollector.model.IdeaModel

class ItemListDiffIdeaCallback(private val oldList: List<IdeaModel>, private val newList: List<IdeaModel>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldIdea = oldList[oldItemPosition]
        val newIdea = newList[newItemPosition]
        return oldIdea.createdDate == newIdea.createdDate
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldIdea = oldList[oldItemPosition]
        val newIdea = newList[newItemPosition]
        return (oldIdea.title == newIdea.title && oldIdea.priority == newIdea.priority)
    }

}