package com.example.krasm.studenthelp.view

import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.krasm.studenthelp.R
import com.example.krasm.studenthelp.other.Contact
import com.example.krasm.studenthelp.presenter.FragmentAddContactPresenter
import com.example.krasm.studenthelp.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.fragment_add_contact.*

class FragmentAddContact: Fragment() {
    private lateinit var presenter: FragmentAddContactPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_contact, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = FragmentAddContactPresenter(this)
        button_add.setOnClickListener { addContact() }
    }

    fun addContact() {
        if (edit_text_name.text.isEmpty() or edit_text_email.text.isEmpty() and edit_text_phone.text.isEmpty())
            Toast.makeText(this.context, "Fill name and contact information!",Toast.LENGTH_SHORT).show()
        else
            presenter.addContact(Contact(edit_text_name.text.toString(),
                                         edit_text_subject.text.toString(),
                                         edit_text_email.text.toString(),
                                         edit_text_phone.text.toString()))
    }

    companion object {
        fun newInstance(): FragmentAddContact{
            return FragmentAddContact()
        }
    }
}