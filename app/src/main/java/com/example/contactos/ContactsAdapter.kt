package com.example.contactos

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(private var contacts: List<Contact>, context: Context): RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.lblName)
        val phoneView: TextView = itemView.findViewById(R.id.lblPhone)
        val lonView: TextView = itemView.findViewById(R.id.lblLon)
        val latView: TextView = itemView.findViewById(R.id.lblLat)
        val signView: ImageView = itemView.findViewById(R.id.signature)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameView.text = contact.name
        holder.phoneView.text = contact.phone
        holder.lonView.text = contact.lon
        holder.latView.text = contact.lat
        holder.signView.setImageURI(contact.sign)
    }

    fun refershData(newContacts: List<Contact>) {
        contacts = newContacts
        notifyDataSetChanged()
    }

}