package com.example.krasm.studenthelp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.krasm.studenthelp.R
import com.example.krasm.studenthelp.presenter.MainActivityPresenter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter(this)
    }
}
