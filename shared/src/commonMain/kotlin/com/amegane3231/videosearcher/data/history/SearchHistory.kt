package com.amegane3231.videosearcher.data.history

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class SearchHistory : RealmObject {
    @PrimaryKey var id: Long = 0
    var words: String = ""
}
