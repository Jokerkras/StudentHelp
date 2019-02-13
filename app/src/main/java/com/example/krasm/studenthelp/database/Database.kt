package com.example.krasm.studenthelp.database

import android.util.Log
import android.widget.Toast
import com.example.krasm.studenthelp.other.Contact
import com.example.krasm.studenthelp.other.Student
import com.google.firebase.database.*
import java.util.*

class Database() {
    private val database = FirebaseDatabase.getInstance().reference
    private val contacts = LinkedList<Contact>()

    init {
        addListenerForContacts()
    }

    fun addStudent() {
        val student = Student("krasmax97@mail.ru","Максим Красиков")
        database.child("Course")
                .child("Group")
                .child("9")
                .child("Students")
                .push()
                .setValue(student)
    }

    fun getContacts(): List<Contact> {
        return contacts
    }

    fun addContact(contact: Contact){
        database.child("Course")
                .child("Group")
                .child("9")
                .child("Contacts")
                .push()
                .setValue(contact)
    }

    fun addListenerForContacts() {
        val listener = object: ChildEventListener {
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                Log.d("shit", "add")
                val item = p0.getValue(Contact::class.java)
                if(item != null)
                    contacts.add(item)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                Log.d("shit", "remove")
                val item = p0.getValue(Contact::class.java)
                if(item != null)
                    contacts.remove(item)
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d("shit", "dont load")
            }
        }
        database.child("Course")
                .child("Group")
                .child("9")
                .child("Contacts")
                .addChildEventListener(listener)
    }
}