package com.tsndongo.appspaneltest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tsndongo.appspaneltest.component.ActualityItemViewHolder
import com.tsndongo.appspaneltest.model.Event

class RecyclerViewAdapter(var eventList: ArrayList<Event>):
    RecyclerView.Adapter<ActualityItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActualityItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ActualityItemViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: ActualityItemViewHolder, position: Int) {
        val event: Event = eventList[position]
        holder.bind(event)
    }

}