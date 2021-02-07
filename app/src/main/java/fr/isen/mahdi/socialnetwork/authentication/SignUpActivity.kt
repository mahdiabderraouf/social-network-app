package fr.isen.mahdi.socialnetwork.authentication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.mahdi.socialnetwork.databinding.ActivitySignupBinding
import fr.isen.mahdi.socialnetwork.network.User

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {
            if (verifyInformations()) {
                launchRequest()
            } else {
                Toast.makeText(this, "Remplir tous les champs", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    private fun launchRequest() {
        // todo
    }

    private fun verifyInformations(): Boolean {
        return (binding.email.text?.isNotEmpty() == true &&
                binding.username.text?.isNotEmpty() == true &&
                binding.password.text?.count() ?: 0 >= 6)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            setResult(Activity.RESULT_FIRST_USER)
            finish()
        }
    }

    companion object {
        const val REQUEST_CODE = 111
        const val ID_USER = "ID_USER"
        const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
    }
}