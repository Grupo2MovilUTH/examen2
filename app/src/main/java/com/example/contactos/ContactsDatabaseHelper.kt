package com.example.contactos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.insertImage
import androidx.core.net.toUri
import java.io.ByteArrayOutputStream


class ContactsDatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "contacts.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "contact_list"

        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_LON = "longitude"
        private const val COLUMN_LAT = "latitude"
        private const val COLUMN_SIGN = "signature"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME "+
                "($COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_PHONE TEXT, " +
                "$COLUMN_LON TEXT, " +
                "$COLUMN_LAT TEXT, " +
                "$COLUMN_SIGN BLOB)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertContact (contact: Contact) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, contact.name)
            put(COLUMN_PHONE, contact.phone)
            put(COLUMN_LON, contact.lon)
            put(COLUMN_LAT, contact.lat)
            put(COLUMN_SIGN, contact.sign.toString())
        }
        db.insert(TABLE_NAME,null, values)
        db.close()
    }

    fun selectAllContacts (): List<Contact> {
        val contactList = mutableListOf<Contact>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE))
            val lon = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LON))
            val lat = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAT))
            val sign = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SIGN))

            val contact = Contact(id, name, phone, lon, lat, sign.toUri())
            contactList.add(contact)
        }
        cursor.close()
        db.close()
        return contactList
    }

}