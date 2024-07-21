package com.example.contactos

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Images.Media.insertImage
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.drawToBitmap
import com.example.contactos.databinding.ActivityAddContactBinding
import java.io.ByteArrayOutputStream

class AddContact : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    private lateinit var db: ContactsDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = ContactsDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val name = binding.txtName.text.toString()
            val phone = binding.txtPhone.text.toString()
            val lon = binding.txtLon.text.toString()
            val lat = binding.txtLat.text.toString()
            val sign = getImageUri(this, binding.sign.signatureBitmap)
            val contact = Contact(0, name, phone, lon, lat, sign)
            db.insertContact(contact)
            finish()
            Toast.makeText(this, "Contacto Guardado!", Toast.LENGTH_SHORT).show()
        }

    }

    // convert image to uri
    fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

}