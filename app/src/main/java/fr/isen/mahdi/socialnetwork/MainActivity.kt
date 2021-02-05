package fr.isen.mahdi.socialnetwork

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.mahdi.socialnetwork.authentication.SignInActivity
import fr.isen.mahdi.socialnetwork.authentication.SignUpActivity
import fr.isen.mahdi.socialnetwork.databinding.ActivityMainBinding
import fr.isen.mahdi.socialnetwork.posts.PostsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, PostsActivity::class.java)
        startActivity(intent)
    }
}