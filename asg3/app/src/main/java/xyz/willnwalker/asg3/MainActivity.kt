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
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var serviceIntent: Intent
    private lateinit var movementServiceConnection: MovementServiceConnection
    private var serviceRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clearButton.setOnClickListener(this)
        exitButton.setOnClickListener(this)
        serviceSwitch.setOnClickListener(this)
        serviceIntent = Intent(this, MovementService::class.java)
        movementServiceConnection = MovementServiceConnection(this)
    }

    override fun onResume() {
        super.onResume()
        startService(serviceIntent)
        bindService(serviceIntent, movementServiceConnection, Context.BIND_AUTO_CREATE)
        serviceRunning = true
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
}

class MovementServiceConnection(context: Context): ServiceConnection {

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val movementServiceBinder = service as MovementService.MovementServiceBinder
        movementServiceBinder.service.didItMove()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
