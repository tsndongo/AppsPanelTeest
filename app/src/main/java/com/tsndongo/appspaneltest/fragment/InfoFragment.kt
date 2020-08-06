package com.tsndongo.appspaneltest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.tsndongo.appspaneltest.R
import com.tsndongo.appspaneltest.services.Api
import com.tsndongo.appspaneltest.services.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoFragment : Fragment() {


    companion object {
        fun newInstance(): InfoFragment = InfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val request = ServiceBuilder.buildService(Api::class.java)
        val call = request.getHtml()

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){
                if (response.isSuccessful) {
                    webView.loadData(response.body()!!, "text/html; charset=utf-8", "utf-8")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
