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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var serviceIntent: Intent
    private lateinit var movementServiceConnection: MovementServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clearButton.setOnClickListener(this)
        exitButton.setOnClickListener(this)
        serviceIntent = Intent(this, MovementService::class.java)
        movementServiceConnection = MovementServiceConnection(this)
    }

    override fun onResume() {
        super.onResume()
        startService(serviceIntent)
        bindService(serviceIntent, movementServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onPause() {
        super.onPause()
        unbindService(movementServiceConnection)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.clearButton -> {

            }
            R.id.exitButton -> {
                // TODO: Stop service here
                stopService(serviceIntent)
                Log.d(localClassName, "Service successfully stopped.")
                finish()
            }
        }
    }
}

class MovementServiceConnection(context: Context): ServiceConnection {

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
