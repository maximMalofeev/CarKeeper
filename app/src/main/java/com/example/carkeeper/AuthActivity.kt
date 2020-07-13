package com.example.carkeeper

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carkeeper.R.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity() {
  private lateinit var auth: FirebaseAuth

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_auth)

    auth = Firebase.auth
  }

  override fun onStart() {
    super.onStart()
    val currentUser = auth.currentUser
    if (currentUser != null) {
      Log.i("Auth", "User is not null")
      startMainActivity(currentUser)
    } else {
      Log.i("Auth", "User is null")
    }
  }

  fun onGo(view: View) {
    Log.i("Auth", "Go button pressed")

    var email = findViewById<EditText>(R.id.emailEditText).text.toString()
    var password = findViewById<EditText>(R.id.passwordEditText).text.toString()

    Log.i("Auth", "$email + $password")

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this) { task ->
          if (task.isSuccessful) {
            Log.d("Auth", "signInWithEmail:success")
            val user = auth.currentUser!!
            startMainActivity(user)
          } else {
            Log.d("Auth", "signInWithEmail:failure", task.exception)
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                  if (task.isSuccessful) {
                    Log.d("Auth", "createUserWithEmail:success")
                    val user = auth.currentUser!!
                    startMainActivity(user)
                  } else {
                    Log.w("Auth", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed, try again",
                        Toast.LENGTH_SHORT).show()
                  }
                }
          }
        }
  }

  fun startMainActivity(user: FirebaseUser){
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
  }
}