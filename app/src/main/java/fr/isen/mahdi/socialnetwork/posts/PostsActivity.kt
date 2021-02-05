package fr.isen.mahdi.socialnetwork.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.mahdi.socialnetwork.databinding.ActivityPostsBinding

class PostsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadPosts()
    }

    private fun loadPosts() {
        val queue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(
            Request.Method.GET,
            "https://jsonplaceholder.typicode.com/posts",
            Response.Listener {resposne ->
                Log.d("request", )
            }
        )
    }
}