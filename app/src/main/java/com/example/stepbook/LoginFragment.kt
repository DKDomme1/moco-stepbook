package com.example.stepbook

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stepbook.common.User
import com.example.stepbook.databinding.LoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private val TAG = "LoginFragment"

    private var _binding: LoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val auth = Firebase.auth
        val currUser = auth.currentUser
        if (currUser != null) {
            val action = LoginFragmentDirections
                .actionLoginFragmentToTrainingMenuFragment()
            view.findNavController().navigate(action)
        }

        binding.login.setOnClickListener {
            val email = binding.email.text
            val password = binding.password.text
            val bothNotNullOrBlank =
                !(email.isNullOrBlank() || password.isNullOrBlank())

            if (bothNotNullOrBlank) {
                auth.signInWithEmailAndPassword(email.toString(), password.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this.context,
                                "Login successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            val action = LoginFragmentDirections
                                .actionLoginFragmentToTrainingMenuFragment()
                            view.findNavController().navigate(action)
                        } else {
                            Log.d(
                                TAG,
                                "signInWithEmailAndPassword:failure " + task.exception.toString()
                            )

                            Toast.makeText(
                                this.context, task.exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
        binding.register.setOnClickListener {
            //TODO check for validity of email/password
            val email = binding.email.text
            val password = binding.password.text
            val bothNotNullOrBlank =
                !(email.isNullOrBlank() || password.isNullOrBlank())

            if (bothNotNullOrBlank) {
                auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")
                            Toast.makeText(
                                this.context,
                                "Account Created",
                                Toast.LENGTH_SHORT
                            ).show()
                            val newUser = User(
                                auth.currentUser?.uid,
                                auth.currentUser?.displayName,
                                auth.currentUser?.email
                            )
                            Firebase.firestore.collection("users")
                                .document(newUser.uId!!).set(newUser)

                            val action = LoginFragmentDirections
                                .actionLoginFragmentToTrainingMenuFragment()
                            view.findNavController().navigate(action)
                        } else {
                            Log.d(TAG, "createUserWithEmail:failure " + task.exception.toString())

                            Toast.makeText(
                                this.context, task.exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}
