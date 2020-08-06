package com.tsndongo.appspaneltest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.tsndongo.appspaneltest.R
import com.tsndongo.appspaneltest.model.PostResponse
import com.tsndongo.appspaneltest.services.Api
import com.tsndongo.appspaneltest.services.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_subscription.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubscriptionFragment : Fragment() {


    companion object {
        fun newInstance(): SubscriptionFragment = SubscriptionFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subscription, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = nameText.editText?.text.toString()
        val email = emailText.editText?.text.toString()
        val phone = phoneText.editText?.text.toString()

        subscribeButton.isEnabled = name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()

        subscribeButton.setOnClickListener{
            val request = ServiceBuilder.buildService(Api::class.java)
            val call = request.postSubscribe(name, email, phone)

            call.enqueue(object : Callback<PostResponse> {
                 override fun onResponse(
                     call: Call<PostResponse>,
                     response: Response<PostResponse>
                 ) {
                     if (response.isSuccessful) {
                         Toast.makeText(activity, resources.getString(R.string.success), Toast.LENGTH_SHORT).show()
                     }
                 }

                 override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
                 }
            })
        }

    }

}
