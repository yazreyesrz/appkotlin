package com.ramirinter.lologin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.ramirinter.lologin.activities.LoginActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}