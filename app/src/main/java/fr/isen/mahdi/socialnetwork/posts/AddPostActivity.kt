package fr.isen.mahdi.socialnetwork.posts

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import fr.isen.mahdi.socialnetwork.databinding.ActivityAddPostBinding
import fr.isen.mahdi.socialnetwork.network.Post
import java.util.*


class AddPostActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAddPostBinding
    private lateinit var database: DatabaseReference
    lateinit var filepath : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Firebase.database("https://social-network-cb966-default-rtdb.europe-west1.firebasedatabase.app/").reference
        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setOnClickListener {

                //check runtime permission
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {

                        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                        {
                        //permission denied
                        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                        //show popup to request runtime permission
                        requestPermissions(permissions, PERMISSION_CODE)
                        }
                        else
                        {
                            chooseImage()
                        }
                }
                 else
                {
                    chooseImage()
                }
        }

        binding.imgPickBtn.setOnClickListener {
            uploadImage()
            sendData()
        }
    }

    private fun sendData(){
        var title=binding.titleArticle.text.toString().trim()
        var body=binding.descriptionArticle.text.toString().trim()

        if(title.isNotEmpty() && body.isNotEmpty())
        {
            val id = database.push().key
            val post= Post(id = id, userId = 4, title =title, body =body)
            Toast.makeText(applicationContext,"Bien Ajouté",Toast.LENGTH_LONG).show()

            if (id != null)
            {
                database.child("posts").child(id).setValue(post)
            }
        }

        else
        {
            Toast.makeText(applicationContext,"all filed required",Toast.LENGTH_LONG).show()
        }

    }

    private fun chooseImage() {
            //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type ="image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private fun uploadImage(){
            if(filepath!= null)
            {
                var imageName= UUID.randomUUID().toString()
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("image Uploading")
                progressDialog.show()

                var imageRef: StorageReference = FirebaseStorage.getInstance().reference.child("images/"+imageName )
                imageRef.putFile(filepath)
                    .addOnSuccessListener { p0 ->
                        progressDialog.dismiss()
                        Toast.makeText(applicationContext,"Image Uploaded",Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {  p0 ->
                        progressDialog.dismiss()
                        Toast.makeText(applicationContext,p0.message,Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener { p0 ->
                        var progress : Double = (100.0 * p0.bytesTransferred)/ p0.totalByteCount
                        progressDialog.setMessage("Uploaded ${progress.toInt()}%")
                    }
            }
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
                    chooseImage()
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
       if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE)
       {
            filepath = data?.data!!
            binding.imageView.setImageURI(filepath)
       }
    }

}