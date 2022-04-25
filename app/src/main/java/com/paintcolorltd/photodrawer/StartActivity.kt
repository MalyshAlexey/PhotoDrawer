package com.paintcolorltd.photodrawer

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val startButton: Button = findViewById(R.id.start)
        val rulesButton: Button = findViewById(R.id.rules)


        startButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        rulesButton.setOnClickListener {
            startActivity(Intent(this, RulesActivity::class.java))
        }
    }
}