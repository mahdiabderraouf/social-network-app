package fr.isen.mahdi.socialnetwork.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.isen.mahdi.socialnetwork.databinding.ActivityPostsBinding
import fr.isen.mahdi.socialnetwork.network.Post

class PostsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostsBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database("https://social-network-cb966-default-rtdb.europe-west1.firebasedatabase.app/").reference

        getPosts(database.child("posts"))
    }

    private fun getPosts(postsReference: DatabaseReference) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val posts = dataSnapshot.getValue<List<Post>>()
                if (posts != null) {
                    updateUi(posts)
                    Log.d("firebase", posts.count().toString())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("firebase", "loadPost:onCancelled", databaseError.toException())
            }
        }
        postsReference.addValueEventListener(postListener)
    }

    private fun updateUi(posts: List<Post>) {
        val adapter = PostAdapter(posts)
        binding.posts.layoutManager = LinearLayoutManager(this)
        binding.posts.adapter = adapter
    }
}