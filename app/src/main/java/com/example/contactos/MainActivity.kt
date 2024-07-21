package com.example.contactos

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactos.databinding.ActivityMainBinding
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: ContactsDatabaseHelper
    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = ContactsDatabaseHelper(this)
        contactsAdapter = ContactsAdapter(db.selectAllContacts(), this)

        binding.contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.contactsRecyclerView.adapter = contactsAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddContact::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        contactsAdapter.refershData(db.selectAllContacts())
    }

}