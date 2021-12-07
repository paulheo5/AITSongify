package com.ait.songifyait

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Math.abs

class Compare : AppCompatActivity() {

    var Points = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare)

        energy
        danceAbility1
        instramentalness1
        key
        loudless
        timesignature1
        valence1
        tempo1
        fun comparision() {
            if (abs(danceAbility1 - danceAbility2) < .25) {
                Points = +1
            }
            if (abs(energy1 - energy2) < .25) {
                Points = +1
            }

            if (abs(instramentalness1 - instramentalness2) < .2) {
                Points = +1
            }
            if (abs(key1 - key2) < 3) {
                Points = +1
            }
            if (abs(loudness1 - loudless2) < 20) {
                Points = +1
            }
            if (abs(timesignature1 - timesignature2) < 3) {
                Points = +1
            }
            if (abs(valence1 - valence2) < .25) {
                Points = +1
            }
            if (abs(tempo1 - tempo2) < 34) {
                Points = +1
            }
        }
    }






}