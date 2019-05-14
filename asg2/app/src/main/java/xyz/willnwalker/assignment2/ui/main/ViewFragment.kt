package xyz.willnwalker.assignment2.ui.main


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_view.*
import xyz.willnwalker.assignment2.ImageListAdapter
import xyz.willnwalker.assignment2.ImageObject
import xyz.willnwalker.assignment2.R

/**
 * A simple [Fragment] subclass.
 */
class ViewFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val range = prefs.getBoolean("range",false)
        var results = viewModel.loadImages()
        if(range){
            results = viewModel.loadImages(prefs.getLong("begin",0),prefs.getLong("end",0))
        }
        realm_recycler_view.setAdapter(ImageListAdapter(requireContext(), results, true, false, ""))
    }

}
