package com.tsndongo.appspaneltest.component

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tsndongo.appspaneltest.R
import com.tsndongo.appspaneltest.model.Event
import com.tsndongo.appspaneltest.model.formatDate
import java.util.*

class EventsItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_event, parent, false)
) {

    private var title: TextView = itemView.findViewById(R.id.title)
    private var description: TextView = itemView.findViewById(R.id.description)
    private var date: TextView = itemView.findViewById(R.id.date)
    private var image: ImageView = itemView.findViewById(R.id.image)

    fun bind(event: Event) {
        title.text = event.title
        description.text = event.description
        date.text = event.date.formatDate()
        image.setImageResource(R.mipmap.ic_placeholder)

        event.image?.let{
            if (event.image.isNotEmpty() && event.image.isNotBlank()) {
                Glide.with(itemView.context).load(event.image)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_placeholder)
                    .into(image)
            }
        }

    }


}