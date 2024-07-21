package com.example.contactos

import android.graphics.Bitmap
import android.net.Uri

data class Contact(val id:Int,
                   val name:String,
                   val phone:String,
                   val lon:String,
                   val lat:String,
                   val sign: Uri)
