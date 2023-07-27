package com.company0ne.firebase_authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.company0ne.firebase_authentication.databinding.ActivityForgetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordActivity : AppCompatActivity() {

    //create an off Object
    lateinit var forgetBinding:ActivityForgetPasswordBinding

    val auth:FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgetBinding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        val view = forgetBinding.root
        setContentView(view)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //add functionality to the forGot Password button
        forgetBinding.buttonReset.setOnClickListener {
            val email = forgetBinding.editTextReset.text.toString()
            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "We sent a password reset mail to your address", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }
            }
        }
    }
    //open LoginActivity on backPress
    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this@ForgetPasswordActivity, LoginActivity::class.java)
        startActivity(intent)
        return true
    }

//    override fun onBackPressed() {
//        moveTaskToBack(true)
//    }
}