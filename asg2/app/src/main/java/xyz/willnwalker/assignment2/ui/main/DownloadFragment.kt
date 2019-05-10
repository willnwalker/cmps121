package xyz.willnwalker.assignment2.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_download.*
import kotlinx.coroutines.delay
import xyz.willnwalker.assignment2.R

/**
 * A simple [Fragment] subclass.
 */
class DownloadFragment : Fragment(), View.OnClickListener {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val t: Toast = Toast.makeText(requireContext(), "", Toast.LENGTH_LONG)
        val success = viewModel.getImage(v, imgUrl.text.toString(), imgTitle.text.toString())
        if(success){
            t.setText("Download successful!")
            t.show()
            findNavController().navigateUp()
        }
        else{
            t.setText("Download unsuccessful.")
            t.show()
        }
    }

}
