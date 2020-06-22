package com.example.kotlindemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer,
                ListUserFragment()
            )
            .commit()

    }

    fun addDetailsFragmentWithTransition(detailsFragment: DetailsFragment, transitioningView: Any) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, detailsFragment)
            .addToBackStack("")

        transaction.commit()
    }

}
