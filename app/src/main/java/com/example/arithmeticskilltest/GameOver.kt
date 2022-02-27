package com.example.arithmeticskilltest

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameOver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val correctScore = findViewById<TextView>(R.id.correctScore)
        val wrongScore = findViewById<TextView>(R.id.wrongScore)

        val correctR = intent.getStringExtra("correct").toString()
        val wrongR = intent.getStringExtra("wrong").toString()

        correctScore.text = correctR
        wrongScore.text = wrongR
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
