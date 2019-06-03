package xyz.willnwalker.asg3

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, MovementServiceTask.ResultCallback {

    private lateinit var serviceIntent: Intent
    private lateinit var movementServiceConnection: ServiceConnection
    private var movementService: MovementService? = null
    private var serviceRunning = false
    private var serviceBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sysWindow = window
        sysWindow.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        sysWindow.addFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)
        sysWindow.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)

        clearButton.setOnClickListener(this)
        exitButton.setOnClickListener(this)
        serviceSwitch.setOnClickListener(this)
        serviceIntent = Intent(this, MovementService::class.java)
        movementServiceConnection = object: ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                movementService = (service as MovementService.MovementServiceBinder).service
                movementService!!.updateResultCallback(this@MainActivity)
                movementService!!.didItMove()
                serviceBound = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                serviceBound = false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        startService(serviceIntent)
        serviceRunning = true
        bindService(serviceIntent, movementServiceConnection, Context.BIND_AUTO_CREATE)
        serviceBound = true
    }

    override fun onPause() {
        super.onPause()
        unbindService(movementServiceConnection)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.serviceSwitch -> {
                if(!serviceRunning){
                    startService(serviceIntent)
                    serviceRunning = true
                }
                else{
                    stopService(serviceIntent)
                    serviceRunning = false
                    Toast.makeText(this, "Stopped service", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.clearButton -> {
                if(serviceBound){
                    movementService!!.clear()
                }
            }
            R.id.exitButton -> {
                // TODO: Stop service here
                stopService(serviceIntent)
                serviceRunning = false
                Log.d(localClassName, "Service successfully stopped.")
                finish()
            }
        }
    }

    override fun onResultReady(result: MovementServiceResult) {
        Log.d(localClassName, "Callback called.")
        Log.d(localClassName, result.moved.toString())
        status_message.text = result.moved.toString()
    }
}
