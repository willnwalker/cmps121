package xyz.willnwalker.assignment2.ui.main

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.willnwalker.assignment2.ImageObject
import java.io.ByteArrayOutputStream

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var counter: Long = 1

    fun delImage(id: Long){

    }

    fun getImage(v: View?, url: String, name: String): Boolean{
        var success = false
        viewModelScope.launch(Dispatchers.IO){
            val imageObject = ImageObject()
            imageObject.id = counter
            counter++
            imageObject.name = name
            val futureTarget: FutureTarget<Bitmap> = Glide.with(v!!).asBitmap().load(url).submit()
            val stream = ByteArrayOutputStream()
            futureTarget.get().compress(Bitmap.CompressFormat.PNG, 100, stream)
            imageObject.imageBytes = stream.toByteArray()
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.insertOrUpdate(imageObject)
            realm.commitTransaction()
            success = true
        }
        return success
    }

    fun loadImages(img: ImageObject){

    }

}
