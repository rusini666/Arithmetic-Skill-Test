package com.example.arithmeticskilltest

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameOver : AppCompatActivity() {
//    private lateinit var correctScore : TextView
//    private lateinit var wrongScore: TextView
//    private lateinit var correctR : String
//    private lateinit var wrongR: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val correctScore = findViewById<TextView>(R.id.correctScore)
        val wrongScore = findViewById<TextView>(R.id.wrongScore)

        /**
         *
         * Gets correct score and wrong score data from GamePage activity.
         *
         */
        val correctR = intent.getStringExtra("correct").toString()
        val wrongR = intent.getStringExtra("wrong").toString()

        correctScore.text = correctR
        wrongScore.text = wrongR
    }

    /**
     *
     * This inbuilt function displays the MainActivity once the user presses back button in device.
     *
     */
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}
