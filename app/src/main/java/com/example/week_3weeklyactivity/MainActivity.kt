package com.example.week_3weeklyactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    lateinit var  stopwatch: Chronometer
    var running = false
    var offset: Long = 0
    val offset_key = "offset"
    val running_key = "running"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopwatch = findViewById<Chronometer>(R.id.chronometer)
        if(savedInstanceState!= null) {
            offset = savedInstanceState.getLong(offset_key)
            running = savedInstanceState.getBoolean(running_key)

            setTimer(0)
            if (running) {
                stopwatch.start()
            }
        }

        val one_sec = findViewById<Button>(R.id.one_sec_button)
         one_sec.setOnClickListener{
            Log.v("button click", "Add 1 sec")
             setTimer(1000)
         }
        val five_sec = findViewById<Button>(R.id.five_sec_button)
        five_sec.setOnClickListener{
            Log.v("button click", "Add 5 sec")
            setTimer(5000)
        }
        val ten_sec = findViewById<Button>(R.id.ten_sec_button)
        ten_sec.setOnClickListener{
            Log.v("button click", "Add 10 sec")
            setTimer(10000)
        }
        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            if(!running){
                setBaseTime()
                stopwatch.start()
                running = true
            }
        }

        val stopButton = findViewById<Button>(R.id.stop_button)
        stopButton.setOnClickListener {
            if (running) {
                saveOffset()
                stopwatch.stop()
                running = false
            }
        }

        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            offset =0
            setBaseTime()
        }
    }
   fun setTimer(value: Long){
        offset +=value
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    fun saveOffset(){
        offset = SystemClock.elapsedRealtime() - stopwatch.base

    }
    fun setBaseTime(){
        stopwatch.base= SystemClock.elapsedRealtime() - offset
    }

}