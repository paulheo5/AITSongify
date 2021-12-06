package com.ait.songifyait

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ait.songifyait.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Demo = "Did this push work?"

        binding.tvURI.text = getURI(
            "https://open.spotify.com/track/4laGLspUjGjh1O0AZ2tEQk?si=a22a7892d6344620")
    }


    fun getURI(url : String): String {
        var trackURI = ""
        for (i in 31..52) {
                trackURI += url[i]
        }
        return trackURI
    }
}