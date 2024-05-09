package com.mishukoffs.ideacollector

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mishukoffs.ideacollector.model.IdeaModel


class IdeaListAdapter(private val dataSet: MutableList<IdeaModel>) :
    RecyclerView.Adapter<IdeaListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ideaTitle: TextView
        val ideaDate: TextView
        val ideaIcon: ImageView

        init {
            ideaTitle = view.findViewById(R.id.idea_title)
            ideaDate = view.findViewById(R.id.idea_create_date)
            ideaIcon = view.findViewById(R.id.idea_icon)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.ideaDate.text = dataSet[position].createdDate.toString()
        viewHolder.ideaTitle.text = dataSet[position].title
        viewHolder.ideaIcon.setImageResource(dataSet[position].getDrawableFromStatus())
    }

    override fun getItemCount() = dataSet.size
}
