package xyz.willnwalker.assignment2.ui.main


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_range.*
import xyz.willnwalker.assignment2.R

/**
 * A simple [Fragment] subclass.
 */
class RangeFragment : Fragment(), View.OnClickListener {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_range, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
//        viewModel.range = true
//        viewModel.begin = rangeBegin.text.toString().toLong()
//        viewModel.end = rangeEnd.text.toString().toLong()
        requireActivity().getPreferences(Context.MODE_PRIVATE).edit(commit = true){
            putBoolean("range",true)
            putLong("begin",rangeBegin.text.toString().toLong())
            putLong("end",rangeEnd.text.toString().toLong())
        }
        findNavController().navigate(R.id.action_viewFragment)
    }

}
