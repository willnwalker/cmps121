package xyz.willnwalker.assignment2

import android.content.Context
import android.graphics.BitmapFactory
import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import io.realm.Realm
import io.realm.RealmBasedRecyclerViewAdapter
import io.realm.RealmResults
import io.realm.RealmViewHolder
import android.view.View
import android.widget.ImageView

class ImageListAdapter(
    context: Context,
    private val data: RealmResults<ImageObject>,
    automaticUpdate: Boolean,
    animateIdType: Boolean,
    animateExtraColumnName: String) : RealmBasedRecyclerViewAdapter<ImageObject, ImageListAdapter.ViewHolder>(
    context,
    data,
    automaticUpdate,
    animateIdType,
    animateExtraColumnName){

    // Provide a suitable constructor (depends on the kind of dataset)
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    // each data item is just a string in this case
    inner class ViewHolder(container: FrameLayout) : RealmViewHolder(container) {
        var mTextView: TextView = container.findViewById<View>(R.id.textView) as TextView
        var mImageView: ImageView = container.findViewById(R.id.imageView) as ImageView

        init {
            mTextView = container.findViewById<View>(R.id.textView) as TextView
            mImageView = container.findViewById<View>(R.id.imageView) as ImageView
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateRealmViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v as FrameLayout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindRealmViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.text = data[position]!!.name
        val image = BitmapFactory.decodeByteArray(data[position]!!.imageBytes, 0, data[position]!!.imageBytes.size)
        holder.mImageView.setImageBitmap(image)
        //set list item onclicklistener here
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return data.size
    }

}