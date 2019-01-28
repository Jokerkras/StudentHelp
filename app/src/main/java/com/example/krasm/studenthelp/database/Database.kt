package com.example.krasm.studenthelp.database

import com.example.krasm.studenthelp.other.Student
import com.google.firebase.database.FirebaseDatabase

class Database {
    private val database = FirebaseDatabase.getInstance().reference

    fun addStudent() {
        var student = Student("krasmax97@mail.ru","Максим Красиков")
        database.child("Course").child("Group").child("9").child("Students").push().setValue(student)
    }
}