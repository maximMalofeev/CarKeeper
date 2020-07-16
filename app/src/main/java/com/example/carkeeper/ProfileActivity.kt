package com.example.carkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class ProfileActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_profile)
  }

  fun onSubmit(view: View){
    Log.i("Tmp", "submit clicked")
  }
}