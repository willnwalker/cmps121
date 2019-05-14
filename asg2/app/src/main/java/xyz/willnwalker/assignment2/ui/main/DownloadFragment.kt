package xyz.willnwalker.assignment2.ui.main


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_download.*
import xyz.willnwalker.assignment2.R

/**
 * A simple [Fragment] subclass.
 */
class DownloadFragment : Fragment(), View.OnClickListener {

    private val viewModel by viewModels<MainViewModel>()

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
        val prefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        var id = prefs.getLong("id",1)
        viewModel.getImage(id, v!!, imgUrl.text.toString(), imgTitle.text.toString())
        id++
        prefs.edit(commit = true){
            putLong("id",id)
        }

    }

}
