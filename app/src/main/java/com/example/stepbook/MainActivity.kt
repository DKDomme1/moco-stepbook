package com.example.stepbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stepbook.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val USE_FIREBASE_EMULATOR = false
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            if (USE_FIREBASE_EMULATOR) {
                FirebaseFirestore.getInstance().useEmulator("10.0.2.2", 8080)
                FirebaseAuth.getInstance().useEmulator("10.0.2.2", 9099)
            }
        }
    }

}
