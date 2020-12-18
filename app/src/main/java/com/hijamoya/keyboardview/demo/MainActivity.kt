package com.hijamoya.keyboardview.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hijamoya.keyboardview.demo.R
import com.hijamoya.keyboardview.demo.keyboard.CustomKeyboard

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val keyboard = CustomKeyboard(this, R.xml.keyboard)
        keyboard.registerKeyboardView(findViewById(R.id.keyboardview))
        keyboard.registerEditText(findViewById(R.id.edit_query), true)
    }
}