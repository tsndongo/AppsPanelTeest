package com.tsndongo.appspaneltest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tsndongo.appspaneltest.component.EventsItemViewHolder
import com.tsndongo.appspaneltest.model.Event

class RecyclerViewAdapter(var eventList: ArrayList<Event>):
    RecyclerView.Adapter<EventsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventsItemViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: EventsItemViewHolder, position: Int) {
        val event: Event = eventList[position]
        holder.bind(event)
    }

}