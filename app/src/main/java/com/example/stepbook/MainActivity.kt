package com.example.stepbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val USE_FIREBASE_EMULATOR = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firestore = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()

        if (USE_FIREBASE_EMULATOR){
            firestore.useEmulator("10.0.2.2", 8080)
            auth.useEmulator("10.0.2.2", 9099)
        }
    }
}