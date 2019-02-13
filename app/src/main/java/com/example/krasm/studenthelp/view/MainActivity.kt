package com.example.krasm.studenthelp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.example.krasm.studenthelp.R
import com.example.krasm.studenthelp.presenter.MainActivityPresenter
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainActivityPresenter
    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener

    private val RC_SIGN_IN: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarCust)

        mFirebaseAuth = FirebaseAuth.getInstance()
        presenter = MainActivityPresenter(this)
        mAuthListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
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
        toolbarCust.title = "Home"
        toolbarCust.inflateMenu(R.menu.toolbar_home_items)
        openFragment(FragmentHome.newInstance())

        toolbarCust.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_logout -> {
                    AuthUI.getInstance().signOut(this)
                    return@setOnMenuItemClickListener true
                }
                else -> {
                    return@setOnMenuItemClickListener true
                }
            }
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_home -> {
                toolbarCust.menu.clear()
                toolbarCust.title = "Home"
                val fragmentHome = FragmentHome.newInstance()
                toolbarCust.inflateMenu(R.menu.toolbar_home_items)
                openFragment(fragmentHome)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_timetable -> {
                toolbarCust.menu.clear()
                toolbarCust.title = "Timetable"
                val fragmentTimetable = FragmentTimetable.newInstance()
                openFragment(fragmentTimetable)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_teachers -> {
                toolbarCust.menu.clear()
                toolbarCust.title = "Teachers"
                toolbarCust.inflateMenu(R.menu.toolbar_contacts_items)
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
