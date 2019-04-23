package xyz.willnwalker.asg1


import android.content.Context
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var mainActivityListener: MainActivityListener
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        saveExitButton.setOnClickListener {
            mainActivityListener.finish()
        }
        hobbybuttton1.setOnClickListener(this)
        hobbybuttton2.setOnClickListener(this)
        hobbybuttton3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val action = MainFragmentDirections.hobbyAction(v!!.id)
        navController.navigate(action)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivityListener = context as MainActivityListener
    }

}
