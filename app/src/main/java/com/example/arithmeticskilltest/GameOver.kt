package com.example.arithmeticskilltest

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameOver : AppCompatActivity() {
    private lateinit var correctScore : TextView
    private lateinit var wrongScore: TextView
    private lateinit var correctR : String
    private lateinit var wrongR: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        correctScore = findViewById(R.id.correctScore)
        wrongScore = findViewById(R.id.wrongScore)

        correctR = intent.getStringExtra("correct").toString()
        wrongR = intent.getStringExtra("wrong").toString()

        correctScore.text = correctR
        wrongScore.text = wrongR
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("CORRECT_R", correctR)
        outState.putString("WRONG_R", wrongR)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        correctR = savedInstanceState.getString("CORRECT_R", "")
        correctScore.text = correctR
        wrongR = savedInstanceState.getString("WRONG_R", "")
        wrongScore.text = wrongR
    }


}
