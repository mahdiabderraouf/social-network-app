package fr.isen.mahdi.socialnetwork.authentication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.mahdi.socialnetwork.databinding.ActivitySigninBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {
            if(verifyInformations()) {
                launchRequest()
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun launchRequest() {

    }

    private fun verifyInformations(): Boolean {
        return (binding.email.text?.isNotEmpty() == true &&
                binding.password.text?.count() ?: 0 >= 6)
    }
}