package fr.isen.mahdi.socialnetwork.network

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Post(
    var userId: Int? = 0,
    var id: Int? = 0,
    var title: String? = "",
    var body: String? = "",
    var imageUrl: String? = ""
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userId" to userId,
            "id" to id,
            "title" to title,
            "body" to body,
            "imageUrl" to imageUrl
        )
    }
}