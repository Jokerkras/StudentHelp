package com.example.krasm.studenthelp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.krasm.studenthelp.R
import com.example.krasm.studenthelp.other.Contact
import kotlinx.android.synthetic.main.element_contacts_recycler_view.view.*

class AdapterContactsList(var items: List<Contact>?,
                          val context: Context,
                          val onClickListener: View.OnClickListener): RecyclerView.Adapter<AdapterContactsList.ViewHolderContacts>() {

    override fun onBindViewHolder(holder: ViewHolderContacts, position: Int) {
        holder.name.text = items?.get(position)?.name
        holder.subject.text = items?.get(position)?.subject
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderContacts {
        val view = LayoutInflater.from(context).inflate(R.layout.element_contacts_recycler_view, parent, false)
        view.setOnClickListener(onClickListener)
        return ViewHolderContacts(view)
    }

    class ViewHolderContacts(view: View): RecyclerView.ViewHolder(view){
        val name = view.textView_name
        val subject = view.textView_subject
    }
}