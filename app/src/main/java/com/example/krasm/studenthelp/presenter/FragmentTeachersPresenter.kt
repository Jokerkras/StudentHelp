package com.example.krasm.studenthelp.presenter

import android.support.v4.app.Fragment
import com.example.krasm.studenthelp.database.Database
import com.example.krasm.studenthelp.other.Contact

class FragmentTeachersPresenter(fragment: Fragment) {
    private val teachersFragment =  fragment
    private val database = Database()

    fun getContacts(): List<Contact> {
        return database.getContacts()
    }
}