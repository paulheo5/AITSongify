package com.ait.songifyait

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ait.songifyait.api.Authorization
import com.ait.songifyait.api.TrackFeatures
import com.ait.songifyait.data.AudioFeatures
import com.ait.songifyait.data.Token
import com.ait.songifyait.databinding.ActivityCompareBinding
import com.ait.songifyait.dialog.Dialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Math.abs
import retrofit2.converter.gson.GsonConverterFactory

class Compare : AppCompatActivity() {

    lateinit var compareBinding: ActivityCompareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compareBinding = ActivityCompareBinding.inflate(layoutInflater)
        setContentView(compareBinding.root)


        var firstURI = intent.getStringExtra(Dialog.FIRST_URL).toString()
        var secondURI = intent.getStringExtra(Dialog.SECOND_URL).toString()
        getAuthorization(firstURI, secondURI)
        compareBinding.tvSong2.text = firstURI
        compareBinding.tvSong1.text = secondURI


        var Points = 0

//        energy
//        danceAbility1
//        instramentalness1
//        key
//        loudless
//        timesignature1
//        valence1
//        tempo1
//        fun comparision() {
//            if (abs(danceAbility1 - danceAbility2) < .25) {
//                Points = +1
//            }
//            if (abs(energy1 - energy2) < .25) {
//                Points = +1
//            }
//
//            if (abs(instramentalness1 - instramentalness2) < .2) {
//                Points = +1
//            }
//            if (abs(key1 - key2) < 3) {
//                Points = +1
//            }
//            if (abs(loudness1 - loudless2) < 20) {
//                Points = +1
//            }
//            if (abs(timesignature1 - timesignature2) < 3) {
//                Points = +1
//            }
//            if (abs(valence1 - valence2) < .25) {
//                Points = +1
//            }
//            if (abs(tempo1 - tempo2) < 34) {
//                Points = +1
//            }
//        }

    }

    fun getAuthorization(firstURI : String, secondURI : String) {
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

                getTrackFeatures(TOKEN, firstURI, secondURI)


            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                TODO("Not yet implemented")

            }
        })

    }

    fun getTrackFeatures(Token : String, firstURI: String, secondURI: String){
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.spotify.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var spotifyAPI = retrofit.create(TrackFeatures::class.java)
        val call = spotifyAPI.getTrackFeatures("Bearer "+Token, firstURI)
        val secondCall = spotifyAPI.getTrackFeatures("Bearer "+Token, secondURI)

        call.enqueue(object : Callback<AudioFeatures>{

            override fun onResponse(call: Call<AudioFeatures>, response: Response<AudioFeatures>) {
                var spotifyResult = response.body()

                compareBinding.tvSong1.text = "Acousticness: ${spotifyResult?.acousticness}"
            }

            override fun onFailure(call: Call<AudioFeatures>, t: Throwable) {
                TODO("Not yet implemented")


            }

        })
        secondCall.enqueue(object : Callback<AudioFeatures>{

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