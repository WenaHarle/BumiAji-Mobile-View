package com.example.bumiajimobileview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.EditText
import android.widget.Button

class Fragment_table : Fragment() {

    private val BASE_URL: String = "http://rarief.com/"
    private val TAG: String = "CHECK_RESPONSE"
    private lateinit var recyclerView: RecyclerView
    private lateinit var tableAdapter: TableAdapter
    private lateinit var editTextLimit: EditText
    private lateinit var fetchDataButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_table, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        tableAdapter = TableAdapter(emptyList())
        recyclerView.adapter = tableAdapter
        editTextLimit = view.findViewById(R.id.edit_text_limit)
        fetchDataButton = view.findViewById(R.id.button_fetch_data)
        fetchDataButton.setOnClickListener {
            getAllComments(editTextLimit.text.toString())
        }
        return view
    }

    private fun getAllComments(limit: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(MyApi::class.java)

        api.getComments(limit).enqueue(object : Callback<List<Comments>> {
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                if (response.isSuccessful) {
                    response.body()?.let { comments ->
                        tableAdapter = TableAdapter(comments)
                        recyclerView.adapter = tableAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }
        })
    }
}

