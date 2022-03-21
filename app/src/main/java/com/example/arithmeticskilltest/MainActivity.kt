package com.example.arithmeticskilltest

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private lateinit var popupWindow: PopupWindow
    private lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newGameBtn = findViewById<Button>(R.id.newGameBtn)
        val aboutBtn = findViewById<Button>(R.id.aboutBtn)

        constraintLayout = findViewById(R.id.constraint)

        newGameBtn.setOnClickListener {
            val gamePage = Intent(this, GamePage::class.java)
            startActivity(gamePage)
        }

        aboutBtn.setOnClickListener {
            popUpWindow()
        }
    }

    fun popUpWindow(){
        val layoutInflater =
            baseContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupview: View = layoutInflater.inflate(R.layout.about_window, null)
        popupWindow = PopupWindow(
            popupview,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        popupWindow.showAtLocation(constraintLayout, Gravity.CENTER, 0, 0)
        val btnclose = popupview.findViewById<Button>(R.id.btnclose)
        btnclose.setOnClickListener { popupWindow.dismiss() }
    }

}