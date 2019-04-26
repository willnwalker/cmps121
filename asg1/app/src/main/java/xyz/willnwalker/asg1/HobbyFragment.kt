package xyz.willnwalker.asg1


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_hobby.*

/**
 * A simple [Fragment] subclass.
 */
class HobbyFragment : Fragment() {

    private val args by navArgs<HobbyFragmentArgs>()
    private lateinit var mTitleTextView: TextView
    private lateinit var mImageView: ImageView
    private lateinit var mDescriptionTextView: TextView
    private var x: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hobby, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        okButton.setOnClickListener { navController.navigateUp() }
        deleteButton.setOnClickListener {
            requireActivity().getPreferences(Context.MODE_PRIVATE).edit().putBoolean("hobby$x",true).apply()
            navController.navigateUp()
        }
        mTitleTextView = titleTextView
        mImageView = imageView
        mDescriptionTextView = descriptionTextView
        when(args.hobby){
            R.id.hobbybuttton1 -> {
                x = 0
                mTitleTextView.text = "Sailing"
                mImageView.setImageResource(R.drawable.hobby1)
                mDescriptionTextView.text = "Sailing on the 35-foot boat Gandalf with the Santa Cruz Yacht Club"
                view.setBackgroundColor(resources.getColor(R.color.hobby1))
            }
            R.id.hobbybuttton2 -> {
                x = 1
                mTitleTextView.text = "App Development"
                mImageView.setImageResource(R.drawable.hobby2)
                mDescriptionTextView.text = "Writing an Android app at the CodeDay hackathon"
                view.setBackgroundColor(resources.getColor(R.color.hobby2))
            }
            R.id.hobbybuttton3 -> {
                x = 2
                mTitleTextView.text = "Hiking"
                mImageView.setImageResource(R.drawable.hobby3)
                mDescriptionTextView.text = "Hiking on the Hawaiian island of Oahu"
                view.setBackgroundColor(resources.getColor(R.color.hobby3))
            }
        }
    }

}
