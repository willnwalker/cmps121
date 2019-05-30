package xyz.willnwalker.asg3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clearButton.setOnClickListener(this)
        exitButton.setOnClickListener(this)
        serviceIntent = Intent(this, MovementService::class.java)
    }

    override fun onStart() {
        super.onStart()
        startService(serviceIntent)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
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
