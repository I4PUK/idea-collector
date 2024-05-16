package com.mishukoffs.ideacollector.idea_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mishukoffs.ideacollector.R
import com.mishukoffs.ideacollector.database.IdeaEntity


//class IdeaListAdapter(private val dataSet: MutableList<IdeaModel>) :
//    RecyclerView.Adapter<IdeaListAdapter.ViewHolder>() {
//
//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val ideaTitle: TextView
//        val ideaDate: TextView
//        val ideaIcon: ImageView
//
//        init {
//            ideaTitle = view.findViewById(R.id.idea_title)
//            ideaDate = view.findViewById(R.id.idea_create_date)
//            ideaIcon = view.findViewById(R.id.idea_icon)
//        }
//    }
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.list_item, viewGroup, false)
//
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//        viewHolder.ideaDate.text = dataSet[position].createdDate.toString()
//        viewHolder.ideaTitle.text = dataSet[position].title
//        viewHolder.ideaIcon.setImageResource(dataSet[position].getDrawableFromPriority())
//    }
//
//    override fun getItemCount() = dataSet.size
//}

class IdeaListAdapter(private val dataset: MutableList<IdeaEntity>) :
    RecyclerView.Adapter<IdeaListAdapter.IdeaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdeaViewHolder {
        return IdeaViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: IdeaViewHolder, position: Int) {
        val current = dataset.elementAt(position)
        holder.bind(
            text = current.text,
            imageId = current.priority.getDrawableFromPriority(),
            createdDate = current.createdDate.toString()
        )
    }

    class IdeaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ideaTitle = itemView.findViewById<TextView>(R.id.idea_title)
        private val ideaDate = itemView.findViewById<TextView>(R.id.idea_create_date)
        private val ideaIcon = itemView.findViewById<ImageView>(R.id.idea_icon)

        fun bind(text: String?, createdDate: String, imageId: Int) {
            ideaTitle.text = text
            ideaDate.text = createdDate
            ideaIcon.setImageResource(imageId)
        }

        companion object {
            fun create(parent: ViewGroup): IdeaViewHolder {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
                return IdeaViewHolder(view)
            }
        }
    }

//    class IdeaComparator : DiffUtil.ItemCallback<IdeaEntity>() {
//        override fun areItemsTheSame(oldItem: IdeaEntity, newItem: IdeaEntity): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: IdeaEntity, newItem: IdeaEntity): Boolean {
//            return oldItem.text == newItem.text
//        }
//    }
}