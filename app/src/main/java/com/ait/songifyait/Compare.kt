package com.ait.songifyait

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ait.songifyait.api.Authorization
import com.ait.songifyait.api.TrackFeatures
import com.ait.songifyait.data.AudioFeatures
import com.ait.songifyait.data.Token
import com.ait.songifyait.databinding.ActivityCompareBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Compare : AppCompatActivity() {

    lateinit var compareBinding: ActivityCompareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compareBinding = ActivityCompareBinding.inflate(layoutInflater)
        setContentView(compareBinding.root)
        getAuthorization()

    }

    fun getAuthorization() {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://accounts.spotify.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var authorizationAPI = retrofit.create(Authorization::class.java)
        val call = authorizationAPI.getToken()

        call.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                var authorizationResult = response.body()
                val TOKEN = authorizationResult?.access_token.toString()

                compareBinding.tvSong1.text = authorizationResult?.access_token
                getTrackFeatures(TOKEN)

            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                TODO("Not yet implemented")

            }
        })

    }

    fun getTrackFeatures(Token : String){
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.spotify.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var spotifyAPI = retrofit.create(TrackFeatures::class.java)
        val call = spotifyAPI.getTrackFeatures("Bearer "+Token)

        call.enqueue(object : Callback<AudioFeatures>{

            override fun onResponse(call: Call<AudioFeatures>, response: Response<AudioFeatures>) {
                var spotifyResult = response.body()

                compareBinding.tvSong2.text = "Acousticness: ${spotifyResult?.acousticness}"
            }

            override fun onFailure(call: Call<AudioFeatures>, t: Throwable) {
                TODO("Not yet implemented")


            }

        })


    }
}