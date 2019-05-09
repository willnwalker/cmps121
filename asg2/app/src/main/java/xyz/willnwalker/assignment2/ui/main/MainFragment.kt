package xyz.willnwalker.assignment2.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.main_fragment.*
import xyz.willnwalker.assignment2.R

class MainFragment : Fragment(), View.OnClickListener {

    val viewModel by viewModels<MainViewModel>()
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        downloadButton.setOnClickListener(this)
        deleteButton.setOnClickListener(this)
        viewButton.setOnClickListener(this)
        rangeButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.downloadButton -> {
                navController.navigate(R.id.action_downloadFragment)
            }
            R.id.deleteButton -> {

            }
            R.id.viewButton -> {

            }
            R.id.rangeButton -> {

            }
        }
    }

}
