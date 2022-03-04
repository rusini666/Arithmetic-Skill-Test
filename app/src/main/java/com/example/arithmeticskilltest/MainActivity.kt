package com.example.arithmeticskilltest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newGameBtn = findViewById<Button>(R.id.newGameBtn)
        val aboutBtn = findViewById<Button>(R.id.aboutBtn)


        newGameBtn.setOnClickListener {
            val gamePage = Intent(this, GamePage::class.java)
            startActivity(gamePage)
        }

        aboutBtn.setOnClickListener {
            AlertDialog.Builder(this) //set icon
                .setIcon(android.R.drawable.ic_dialog_alert) //set title
                .setTitle("Student Details") //set message
                .setMessage("Student Name: Rusini Thara Gunarathne\n\n" +
                        "Student ID: 20200205\n\n" +
                        "I confirm that I understand what plagiarism is and have read and understood the section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely my own. Any work from other authors is duly referenced and acknowledged.") //set positive button
                .setPositiveButton("OK") { dialogInterface, i -> //set what would happen when positive button is clicked
                    dialogInterface.cancel()
                }
                .show()

        }

    }
}