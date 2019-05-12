package xyz.willnwalker.assignment2

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import androidx.activity.viewModels
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import com.androidnetworking.AndroidNetworking
import io.realm.Realm
import xyz.willnwalker.assignment2.ui.main.MainFragment
import xyz.willnwalker.assignment2.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getPreferences(Context.MODE_PRIVATE)
        setContentView(R.layout.main_activity)
        AndroidNetworking.initialize(applicationContext)
        Realm.init(this)
        viewModel.counter = prefs.getLong("counter",1)
    }

    override fun onDestroy() {
        super.onDestroy()
        prefs.edit(commit = true) { putLong("counter",viewModel.counter) }
    }

}
