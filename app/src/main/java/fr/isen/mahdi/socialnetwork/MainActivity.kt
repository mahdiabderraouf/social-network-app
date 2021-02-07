package fr.isen.mahdi.socialnetwork

import android.content.Intent
import fr.isen.mahdi.socialnetwork.posts.AddPostActivity


import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD


import android.os.Bundle
=======
import fr.isen.mahdi.socialnetwork.authentication.SignInActivity
import fr.isen.mahdi.socialnetwork.authentication.SignUpActivity
>>>>>>> ce219af6e2a8299848ff024e3d047ee75a04ca9b
import fr.isen.mahdi.socialnetwork.databinding.ActivityMainBinding
import fr.isen.mahdi.socialnetwork.posts.PostsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, AddPostActivity::class.java)

        startActivity(intent)
    }




}