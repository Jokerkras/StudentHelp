package com.example.krasm.studenthelp.presenter

import android.util.Log
import com.example.krasm.studenthelp.database.Database
import com.example.krasm.studenthelp.view.MainActivity

class MainActivityPresenter(activity: MainActivity) {
    private val mainActivityPresenter =  activity
    private val database = Database()

    fun addFStudent(){
        database.addStudent()
        Log.d("smth", "send motherfucker")
    }
}