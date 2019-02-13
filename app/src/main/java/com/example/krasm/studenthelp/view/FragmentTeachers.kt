package com.example.krasm.studenthelp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.krasm.studenthelp.R
import com.example.krasm.studenthelp.adapter.AdapterContactsList
import com.example.krasm.studenthelp.other.Contact
import com.example.krasm.studenthelp.presenter.FragmentTeachersPresenter
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_teachers.*
import java.util.*

class FragmentTeachers: Fragment(), View.OnClickListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var contacts: List<Contact>
    private val presenterTeachers = FragmentTeachersPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teachers, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        linearLayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        recyclerView_contacts.layoutManager = linearLayoutManager
        contacts = presenterTeachers.getContacts()
        val contactAdapter = AdapterContactsList(contacts, this.context!! ,this)
        recyclerView_contacts.adapter = contactAdapter

        val menu = activity?.toolbarCust?.menu
        menu?.findItem(R.id.item_refresh)?.setOnMenuItemClickListener {
            contacts = presenterTeachers.getContacts()
            recyclerView_contacts.adapter.notifyDataSetChanged()
            return@setOnMenuItemClickListener true
        }
        button_add_contact.setOnClickListener{addContact()}
    }

    private fun addContact(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.frame_layout, FragmentAddContact())
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    override fun onClick(v: View?) {

    }

    companion object {
        fun newInstance(): FragmentTeachers{
            return FragmentTeachers()
        }
    }
}