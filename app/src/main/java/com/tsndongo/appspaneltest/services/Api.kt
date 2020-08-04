package com.tsndongo.appspaneltest.services

import com.tsndongo.appspaneltest.model.Event
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("/events")
    fun getEvents() : Call<List<Event>>
}