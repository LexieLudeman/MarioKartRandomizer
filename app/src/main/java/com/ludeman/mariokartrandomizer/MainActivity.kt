package com.ludeman.mariokartrandomizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun loadRacers(view: View) {
        var racerDBHandler = RacerDBHandler(this, null, null, 1)

    }
}