package fr.isen.mahdi.socialnetwork

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.mahdi.socialnetwork.databinding.ActivityAddPostBinding


class AddPostActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setOnClickListener {
                //check runtime permission
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        //permission denied
                        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                        //show popup to request runtime permission
                        requestPermissions(permissions, PERMISSION_CODE)
                        }
                        else{
                        pickImageFromGallery()

                        }
                }
                 else{
                    pickImageFromGallery()

                }
        }
    }

    private fun pickImageFromGallery() {
            //Intent to pick image
            val intent = Intent(Intent.ACTION_PICK)
        intent.type ="image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }
    companion object{
        private val IMAGE_PICK_CODE=10
        private val PERMISSION_CODE=11

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if(grantResults.size > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            binding.imageView.setImageURI(data?.data)
        }
    }

}