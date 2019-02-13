package com.example.krasm.studenthelp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.krasm.studenthelp.R


class FragmentHome(): Fragment() {
    lateinit var toolbar: ActionBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.actionBar?.setHomeButtonEnabled(true)
    }

    companion object {
        fun newInstance(): FragmentHome{
            return FragmentHome()
        }
    }
}