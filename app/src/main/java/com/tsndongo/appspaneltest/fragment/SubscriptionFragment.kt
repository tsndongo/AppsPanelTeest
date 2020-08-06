package com.tsndongo.appspaneltest.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tsndongo.appspaneltest.R
import com.tsndongo.appspaneltest.extension.isValidEmail
import com.tsndongo.appspaneltest.model.PostBody
import com.tsndongo.appspaneltest.model.PostResponse
import com.tsndongo.appspaneltest.services.Api
import com.tsndongo.appspaneltest.services.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_subscription.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SubscriptionFragment : Fragment() {

    private var name: String? = null
    private var email: String? = null
    private var phone: String? = null

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


        subscribeButton.setOnClickListener{
            val name = nameText.editText?.text.toString()
            val email = emailText.editText?.text.toString()
            val phone = phoneText.editText?.text.toString()


            nameText.error = null
            emailText.error = null
            phoneText.error = null

            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()) {
                if (email.isValidEmail()) {
                    val request = ServiceBuilder.buildService(Api::class.java)
                    val call = request.postSubscribe(PostBody(name, email, phone))

                    call.enqueue(object : Callback<PostResponse> {
                        override fun onResponse(
                            call: Call<PostResponse>,
                            response: Response<PostResponse>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    activity,
                                    resources.getString(R.string.success),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                            Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })
                } else {
                    emailText.error = getString(R.string.error_email_format)
                }
            } else {
                if (name.isEmpty()) nameText.error = getString(R.string.error_empty)
                if (email.isEmpty()) emailText.error = getString(R.string.error_empty)
                if (phone.isEmpty()) phoneText.error = getString(R.string.error_empty)
            }
        }
    }
}
