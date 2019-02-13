package com.example.krasm.studenthelp.presenter

import android.support.v4.app.Fragment
import com.example.krasm.studenthelp.database.Database
import com.example.krasm.studenthelp.other.Contact

class FragmentAddContactPresenter(fragment: Fragment) {
    private val addContactFragment =  fragment
    private val database = Database()

    fun addContact(contact: Contact){
        database.addContact(contact)
    }
}