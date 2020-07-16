package com.example.carkeeper

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.carkeeper.R.*


class MainActivity : AppCompatActivity() {
  private lateinit var auth: FirebaseAuth

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    auth = Firebase.auth

    Log.i("Auth", "User email: " + auth.currentUser?.email.toString())
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      id.profileMenuItem -> {
        Log.i("Tmp", "Profile menu item clicked")
        var intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        true
      }
      else -> super.onContextItemSelected(item)
    }
  }
}