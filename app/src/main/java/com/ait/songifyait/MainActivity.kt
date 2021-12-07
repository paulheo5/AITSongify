package com.ait.songifyait

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ait.songifyait.data.URL
import com.ait.songifyait.databinding.ActivityMainBinding
import com.ait.songifyait.dialog.Dialog

class MainActivity : AppCompatActivity(), Dialog.URLHandler{
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Demo = "Did this push work?"

        val Pull = "Does this pull work after I pushed it?"


        fun getURI(url: String): String {
            var trackURI = ""
            for (i in 31..52) {
                trackURI += url[i]
            }
            return trackURI
        }

        binding.btnSearch.setOnClickListener {
            Dialog().show(supportFragmentManager, "DIALOG")
        }
    }

    override fun urlCreated(url: URL) {
        TODO("Not yet implemented")
    }
}