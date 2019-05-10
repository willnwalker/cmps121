package xyz.willnwalker.assignment2

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ImageObject: RealmObject() {

    @PrimaryKey
    var id: Long = 0
    var name = ""
    var imageBytes = ByteArray(0)

}