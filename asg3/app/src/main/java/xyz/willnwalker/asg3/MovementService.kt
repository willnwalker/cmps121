package xyz.willnwalker.asg3

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MovementService: Service() {

    class MovementServiceBinder: Binder(){
        fun getService(): MovementService{
            return MovementService.this
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}