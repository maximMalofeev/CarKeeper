package com.example.carkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
  private var nameEditText: EditText? = null
  private var plateEditText: EditText? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_profile)

    nameEditText = findViewById(R.id.nameEditText)
    plateEditText = findViewById(R.id.plateEditText)

    val db = Firebase.firestore
    val docRef = db.collection("users").document(Firebase.auth.currentUser?.uid.toString())
    docRef.get().addOnSuccessListener { document ->
      if (document != null) {
        Log.d("Tmp","Get user data successful ${document.data}")
        nameEditText?.setText(document.data?.get("name").toString())
        plateEditText?.setText(document.data?.get("plate").toString())
      } else {
        Log.d("Tmp","No such data")
      }
    }.addOnFailureListener { exception ->
      Log.d("Profile", "Get profile finished with ", exception)
    }
  }

  fun onSubmit(view: View) {
    Log.i("Tmp", "submit clicked")
    val db = Firebase.firestore
    val docRef = db.collection("users").document(Firebase.auth.currentUser?.uid.toString()).set(hashMapOf(
        "name" to nameEditText?.text.toString(), "plate" to plateEditText?.text.toString()
    ))
  }
}