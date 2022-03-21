package com.example.arithmeticskilltest

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private var popupWindow: PopupWindow? = null
    private var constraintLayout: ConstraintLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newGameBtn = findViewById<Button>(R.id.newGameBtn)
        val aboutBtn = findViewById<Button>(R.id.aboutBtn)

        constraintLayout = findViewById(R.id.constraint)

        fun alertDialog(){
            AlertDialog.Builder(this)
                .setTitle("Student Details") //set title
                .setMessage("Student Name: Rusini Thara Gunarathne\n\n" + //set message
                        "Student ID: 20200205\n\n" +
                        "I confirm that I understand what plagiarism is and have read and understood the section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely my own. Any work from other authors is duly referenced and acknowledged.") //set positive button
                .setPositiveButton("OK") { dialogInterface, _ -> //set what would happen when positive button is clicked
                    dialogInterface.cancel()
                }
                .show()
        }

        newGameBtn.setOnClickListener {
            val gamePage = Intent(this, GamePage::class.java)
            startActivity(gamePage)
        }

        aboutBtn.setOnClickListener {
            val layoutInflater =
                baseContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupview: View = layoutInflater.inflate(R.layout.about_window, null)
            popupWindow = PopupWindow(
                popupview,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            popupWindow!!.showAtLocation(constraintLayout, Gravity.CENTER, 0, 0)
            val btnclose = popupview.findViewById<Button>(R.id.btnclose)
            btnclose.setOnClickListener { popupWindow!!.dismiss() }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

}