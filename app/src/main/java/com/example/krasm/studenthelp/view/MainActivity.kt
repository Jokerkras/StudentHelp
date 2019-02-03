package com.example.krasm.studenthelp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import com.example.krasm.studenthelp.R
import com.example.krasm.studenthelp.presenter.MainActivityPresenter
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainActivityPresenter
    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    private lateinit var toolbar: ActionBar

    private val RC_SIGN_IN: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = supportActionBar!!

        mFirebaseAuth = FirebaseAuth.getInstance()
        presenter = MainActivityPresenter(this)
        mAuthListener = FirebaseAuth.AuthStateListener {
            var user = it.currentUser
            if(user != null) {

            } else {
                val providers = arrayListOf( AuthUI.IdpConfig.EmailBuilder().setAllowNewAccounts(false).build() )
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),
                        RC_SIGN_IN)
            }
        }

        bottom_navigation_bar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        openFragment(FragmentHome())
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_home -> {
                toolbar.title = "Home"
                val fragmentHome = FragmentHome.newInstance()
                openFragment(fragmentHome)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_timetable -> {
                toolbar.title = "Timetable"
                val fragmentTimetable = FragmentTimetable.newInstance()
                openFragment(fragmentTimetable)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_teachers -> {
                toolbar.title = "Teachers"
                val fragmentTeachers = FragmentTeachers.newInstance()
                openFragment(fragmentTeachers)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onPause() {
        super.onPause()
        mFirebaseAuth.removeAuthStateListener(mAuthListener)
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth.addAuthStateListener(mAuthListener)
    }
}
