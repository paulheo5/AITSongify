package com.ait.songifyait

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.ait.songifyait.api.Authorization
import com.ait.songifyait.data.Token
import com.ait.songifyait.data.URL
import com.ait.songifyait.databinding.ActivityMainBinding
import com.ait.songifyait.dialog.Dialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Dialog.URLHandler{
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Demo = "Did this push work?"

        val Pull = "Does this pull work after I pushed it?"
        val imageView = findViewById<ImageView>(R.id.image_view)
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(imageView, PropertyValuesHolder.ofFloat("scaleX", 1.1f),
        PropertyValuesHolder.ofFloat("scaleY", 1.1f))
        objectAnimator.setDuration(310)
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE)
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE)
        objectAnimator.setInterpolator(FastOutSlowInInterpolator())
        objectAnimator.start()

        val animationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.apply{
            setEnterFadeDuration(1000)
            setExitFadeDuration(2000)
            start()
        }

        binding.btnSearch.setOnClickListener {
            Dialog().show(supportFragmentManager, "DIALOG")
        }
    }

    override fun urlCreated(url: URL) {
        TODO("Not yet implemented")
    }



}