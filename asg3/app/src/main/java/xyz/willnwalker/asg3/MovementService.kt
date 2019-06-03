package xyz.willnwalker.asg3

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.PowerManager
import android.widget.Toast

class MovementService: Service() {
    private val tag = "asg3:MovementService"
    private lateinit var workerThread: Thread
    private lateinit var workerTask: MovementServiceTask
    private val movementServiceBinder = MovementServiceBinder()
    private lateinit var wakeLock: PowerManager.WakeLock

    inner class MovementServiceBinder : Binder() {
        internal// Returns the underlying service.
        val service: MovementService
            get() = this@MovementService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return movementServiceBinder
    }

    override fun onCreate() {
        workerTask = MovementServiceTask(applicationContext)
        workerThread = Thread(workerTask)
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,tag)
        wakeLock.acquire()
        workerThread.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service starting", Toast.LENGTH_SHORT).show()
//        if(!workerThread.isAlive){
//            Toast.makeText(this, "Worked thread was asleep", Toast.LENGTH_SHORT).show()
//            workerThread.start()
//        }
        return START_STICKY
    }

    override fun onDestroy() {
        workerTask.stopProcessing()
        wakeLock.release()
    }

    fun updateResultCallback(resultCallback: MovementServiceTask.ResultCallback){
        workerTask.updateResultCallback(resultCallback)
    }

    fun didItMove(){
        workerTask.didItMove()
    }

    fun clear(){
        workerTask.clear()
    }

}