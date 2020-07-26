package com.example.carkeeper

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.carkeeper.R.*


class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
  }

  override fun onStart() {
    super.onStart()
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
      } id.logOutMenuItem -> {
        Firebase.auth.signOut()
        finish()
        return true
      }
      else -> super.onContextItemSelected(item)
    }
  }
}