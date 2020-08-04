package com.tsndongo.appspaneltest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.tsndongo.appspaneltest.R
import com.tsndongo.appspaneltest.adapter.RecyclerViewAdapter
import com.tsndongo.appspaneltest.model.Event
import com.tsndongo.appspaneltest.model.sortByDate
import com.tsndongo.appspaneltest.services.ServiceBuilder
import com.tsndongo.appspaneltest.services.Api
import kotlinx.android.synthetic.main.fragment_actuality.*
import kotlin.collections.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActualityFragment : Fragment() {

    private lateinit var eventList: ArrayList<Event>

    companion object {
        fun newInstance(): ActualityFragment = ActualityFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actuality, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchActualities()
    }

    private fun fetchActualities(){
        eventList = ArrayList()

        val request = ServiceBuilder.buildService(Api::class.java)
        val call = request.getEvents()

        call.enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>){
                if (response.isSuccessful) {
                    progressBar.visibility = View.GONE
                    eventList = ArrayList(response.body()!!)
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = RecyclerViewAdapter(eventList.sortByDate())
                    }
                }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
