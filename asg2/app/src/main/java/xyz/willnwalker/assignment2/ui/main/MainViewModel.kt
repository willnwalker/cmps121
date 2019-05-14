package xyz.willnwalker.assignment2.ui.main

import android.util.Log
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
import io.realm.RealmResults
import io.realm.kotlin.where
import java.io.File


class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var counter: Long = 1
    var range: Boolean = false
    var begin: Long = -1
    var end: Long = -1
    private val realm = Realm.getDefaultInstance()

    fun delImage(v: View, id: Long, name: String){
        val results = realm.where(ImageObject::class.java).equalTo("id",id).or().equalTo("name",name).findAll()
        if(results.isEmpty()){ Toast.makeText(v.context, "No matching images", Toast.LENGTH_LONG).show() }
        else{
            realm.beginTransaction()
            results.deleteAllFromRealm()
            realm.commitTransaction()
            Toast.makeText(v.context, "Matching images deleted", Toast.LENGTH_LONG).show()
            v.findNavController().navigateUp()
        }
    }

    fun getImage(id: Long, v: View, url: String, name: String){
//        viewModelScope.launch(Dispatchers.IO){
            AndroidNetworking.download(url, v.context.cacheDir.toString(), name)
                .setTag("downloadTest")
                .setPriority(Priority.HIGH)
                .build()
                .startDownload(object : DownloadListener {
                    override fun onDownloadComplete() {
                        val imageFile = File(v.context.cacheDir.toString(), name)
                        val imageObject = ImageObject()
                        imageObject.id = id
                        Log.d("xyz.willnwalker.model","$counter")
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
//        }
        counter++
    }

    fun loadImages(): RealmResults<ImageObject>{
        return realm.where(ImageObject::class.java).findAll()
    }

    fun loadImages(begin: Long, end: Long): RealmResults<ImageObject>{
        return realm.where(ImageObject::class.java).greaterThanOrEqualTo("id",begin).lessThanOrEqualTo("id",end).findAll()
    }

}
