package com.tsndongo.appspaneltest.services

import com.tsndongo.appspaneltest.model.Event
import com.tsndongo.appspaneltest.model.PostResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @Headers("X-AP-Key: uD4Muli8nO6nzkSlsNM3d1Pm",
        "X-AP-DeviceUID: trial",
        "Accept: application/json")
    @GET("/events")
    fun getEvents() : Call<List<Event>>


    @Headers("X-AP-Key: uD4Muli8nO6nzkSlsNM3d1Pm",
        "X-AP-DeviceUID: trial",
        "Accept: text/html")
    @GET("/html")
    fun getHtml(): Call<String>


    @Headers("X-AP-Key: uD4Muli8nO6nzkSlsNM3d1Pm",
        "X-AP-DeviceUID: trial",
        "Accept: application/json")
    @POST("/authentication/register")
    fun postSubscribe(@Query("name") name: String,
                  @Query("email") email: String,
                  @Query("phone") phone: String) : Call<PostResponse>
}