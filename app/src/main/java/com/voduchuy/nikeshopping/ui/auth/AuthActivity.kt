package com.voduchuy.nikeshopping.ui.auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.voduchuy.nikeshopping.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer,LoginFragment())
        }.commit()
    }
}
