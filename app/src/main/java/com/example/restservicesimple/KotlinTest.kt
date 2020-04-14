package com.example.restservicesimple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_kotlin_test.*

class KotlinTest : AppCompatActivity() {

    private val KotlinString = "Kotlin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_test)

        val message:String = intent.getStringExtra("Kotlin")

        kotlinTextView.text = message

        kotlinBackButton.setOnClickListener{
            finish()
        }


    }




}
