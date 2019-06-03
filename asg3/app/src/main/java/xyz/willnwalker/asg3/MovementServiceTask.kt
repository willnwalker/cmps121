package xyz.willnwalker.asg3

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.hardware.TriggerEvent
import android.hardware.TriggerEventListener
import android.util.Log
import java.util.*

class MovementServiceTask(private val context: Context): Runnable, TriggerEventListener() {

    private val tag = "MovementServiceTask"
    private lateinit var resultCallback: ResultCallback
    private var running = false
    private var moved = false
    private var t0: Date? = null
    private var firstAccelTime: Date? = null

    override fun run() {
        t0 = Date()
        running = true
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
//        val eventListener = object : TriggerEventListener(){
//            override fun onTrigger(event: TriggerEvent?) {
//                Log.d(tag,"Motion sensor triggered")
//                firstAccelTime = Date()
//                val diff = (firstAccelTime!!.time - t0!!.time)/1000
//                if(diff > 30){
//                    moved = true
//                    Log.d(tag,"Phone was moved")
//                }
//            }
//        }
        sensorManager.requestTriggerSensor(this, accelSensor)
//        Thread.sleep(30000)
        while(running){
            if(moved){
                Log.d(tag,"Phone moved!")
                sensorManager.requestTriggerSensor(this, accelSensor)
            }
            else{
                Thread.sleep(100)
            }
            Log.d(tag, "Running: $running")
        }
    }

    fun stopProcessing(){
        running = false
    }

    fun didItMove() {
        val result = MovementServiceResult(moved)
        resultCallback.onResultReady(result)
    }

    fun updateResultCallback(callback: ResultCallback) {
        resultCallback = callback
    }

    fun clear(){
        t0 = Date()
        firstAccelTime = null
        moved = false
    }

    override fun onTrigger(event: TriggerEvent?) {
        Log.d(tag,"Motion sensor triggered")
        firstAccelTime = Date()
        val diff = (firstAccelTime!!.time - t0!!.time)/1000
        if(diff > 30){
            moved = true
            Log.d(tag,"Phone was moved")
        }
    }

    interface ResultCallback {
        fun onResultReady(result: MovementServiceResult)
    }

}