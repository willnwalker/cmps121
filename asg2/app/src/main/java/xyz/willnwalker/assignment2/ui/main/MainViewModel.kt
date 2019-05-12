package xyz.willnwalker.assignment2.ui.main

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.willnwalker.assignment2.ImageObject
import com.androidnetworking.error.ANError
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.DownloadListener
import io.realm.Realm
import io.realm.kotlin.where
import java.io.File


class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var counter: Long = 1
    private val realm = Realm.getDefaultInstance()

    fun delImage(id: Long, name: String){
        realm.where(ImageObject::class.java)
    }

    fun getImage(v: View, url: String, name: String){
        viewModelScope.launch(Dispatchers.IO){
            AndroidNetworking.download(url, v.context.cacheDir.toString(), name)
                .setTag("downloadTest")
                .setPriority(Priority.HIGH)
                .build()
                .startDownload(object : DownloadListener {
                    override fun onDownloadComplete() {
                        val imageFile = File(v.context.cacheDir.toString(), name)
                        val imageObject = ImageObject()
                        imageObject.id = counter
                        counter++
                        imageObject.name = name
                        imageObject.imageBytes = imageFile.readBytes()

                        realm.beginTransaction()
                        realm.copyToRealmOrUpdate(imageObject)
                        realm.commitTransaction()

                        imageFile.delete()

                        Toast.makeText(v.context, "File downloaded successfully.", Toast.LENGTH_LONG).show()
                        v.findNavController().navigateUp()
                    }

                    override fun onError(error: ANError) {
                        Toast.makeText(v.context, "Error downloading file.", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }

    fun loadImages(img: ImageObject){

    }

}
