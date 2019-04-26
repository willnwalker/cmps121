package xyz.willnwalker.asg1


import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var navController: NavController
    private lateinit var hobbyButtons: ArrayList<Button>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        saveExitButton.setOnClickListener {
            requireActivity().finish()
        }
        hobbyButtons = ArrayList()
        hobbyButtons.add(hobbybuttton1)
        hobbyButtons.add(hobbybuttton2)
        hobbyButtons.add(hobbybuttton3)
        hobbyButtons.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onResume() {
        super.onResume()
        val activity = requireActivity()
        for(x in 0..2){
            val deleted = activity.getPreferences(Context.MODE_PRIVATE).getBoolean("hobby$x",false)
            if(deleted){
                hobbyButtons.get(x).visibility = View.GONE
            }
        }
    }

    override fun onClick(v: View?) {
        val action = MainFragmentDirections.hobbyAction(v!!.id)
        navController.navigate(action)
    }

}
