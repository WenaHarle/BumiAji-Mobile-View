package com.example.bumiajimobileview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Fragment_latest : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private val BASE_URL: String = "http://rarief.com/"
    private val TAG: String = "CHECK_RESPONSE"

    private lateinit var photoAtasImageView: ImageView
    private lateinit var photoSampingImageView: ImageView
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val updateInterval = 5000L // 5 seconds

    private lateinit var idTextView: TextView
    private lateinit var gradeTextView: TextView
    private lateinit var beratTextView: TextView
    private lateinit var panjangTextView: TextView
    private lateinit var lebarTextView: TextView
    private lateinit var tinggiTextView: TextView
    private lateinit var timestampTextView: TextView
    private lateinit var machineIdTextView: TextView
    private lateinit var timeUpdateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_latest, container, false)
        photoAtasImageView = view.findViewById(R.id.photoAtas)
        photoSampingImageView = view.findViewById(R.id.photoSamping)

        idTextView = view.findViewById(R.id.idText)
        gradeTextView = view.findViewById(R.id.gradeText)
        beratTextView = view.findViewById(R.id.beratText)
        panjangTextView = view.findViewById(R.id.panjangText)
        lebarTextView = view.findViewById(R.id.lebarText)
        tinggiTextView = view.findViewById(R.id.tinggiText)
        timestampTextView = view.findViewById(R.id.timestampText)
        machineIdTextView = view.findViewById(R.id.machineIdText)
        timeUpdateTextView = view.findViewById(R.id.timeUpdateText)

        handler = Handler(Looper.getMainLooper())
        startAutoUpdate()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopAutoUpdate()
    }

    private fun startAutoUpdate() {
        runnable = object : Runnable {
            override fun run() {
                getAllComments()
                handler.postDelayed(this, updateInterval)
            }
        }
        handler.post(runnable)
    }

    private fun stopAutoUpdate() {
        handler.removeCallbacks(runnable)
    }

    private fun getAllComments() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(MyApi::class.java)

        api.getComments(limit = "1").enqueue(object : Callback<List<Comments>> {
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                if (response.isSuccessful) {
                    response.body()?.let { comments ->
                        if (comments.isNotEmpty()) {
                            updateViews(comments[0]) // Assuming you want to display the first comment
                        }
                    }
                } else {
                    Log.e(TAG, "onResponse: Response was not successful")
                }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun updateViews(comment: Comments) {
        activity?.runOnUiThread {
            idTextView.text = comment.id.toString()
            gradeTextView.text = comment.grade
            beratTextView.text = comment.berat.toString()
            panjangTextView.text = comment.panjang.toString()
            lebarTextView.text = comment.lebar.toString()
            tinggiTextView.text = comment.tinggi.toString()
            timestampTextView.text = comment.timestamp
            machineIdTextView.text = comment.machine_id
            timeUpdateTextView.text = comment.timeupdate
            val newDomain = "rarief.com"
            val newUrl1 = changeUrl(comment.photo_atas_url, newDomain)
            val newUrl2 = changeUrl(comment.photo_samping_url, newDomain)
            // Using Picasso to load images
            Picasso.get().load(newUrl1).into(photoAtasImageView, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    // Image loaded successfully
                }

                override fun onError(e: Exception?) {
                    // Image failed to load, handle error here
                    Log.e(TAG, "Error loading photo atas image: ${e?.message}")
                    // You can set a placeholder image or display an error message here
                }
            })

            Picasso.get().load(newUrl2).into(photoSampingImageView, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    // Image loaded successfully
                }

                override fun onError(e: Exception?) {
                    // Image failed to load, handle error here
                    Log.e(TAG, "Error loading photo samping image: ${e?.message}")
                    // You can set a placeholder image or display an error message here
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_latest().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun changeUrl(oldUrl: String, newDomain: String): String {
        val regex = Regex("http://[^/]+/")
        return oldUrl.replace(regex, "http://$newDomain/")
    }
}
