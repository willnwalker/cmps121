package xyz.willnwalker.assignment2.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_delete.*
import xyz.willnwalker.assignment2.R

/**
 * A simple [Fragment] subclass.
 */
class DeleteFragment : Fragment(), View.OnClickListener {

    private val viewModel by viewModels<MainViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        viewModel.delImage(imgId.text.toString().toLong(), imgTitle.text.toString())
    }

}
