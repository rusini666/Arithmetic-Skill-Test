package com.example.arithmeticskilltest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newGameBtn = findViewById<Button>(R.id.newGameBtn);
        val aboutBtn = findViewById<Button>(R.id.aboutBtn);

        newGameBtn.setOnClickListener {
            val gamePage = Intent(this, GamePage::class.java);
            startActivity(gamePage)
        }

        aboutBtn.setOnClickListener {
            // inflate the layout of the popup window
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView: View = inflater.inflate(android.R.layout.about_window, null)

            // create the popup window
            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true // lets taps outside the popup also dismiss it
            val popupWindow = PopupWindow(popupView, width, height, focusable)

            // show the popup window
            // which view you pass in doesn't matter, it is only used for the window token
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

            // dismiss the popup window when touched
            popupView.setOnTouchListener(object : View.OnTouchListener() {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    popupWindow.dismiss()
                    return true
                }
            })
        }
    }
}