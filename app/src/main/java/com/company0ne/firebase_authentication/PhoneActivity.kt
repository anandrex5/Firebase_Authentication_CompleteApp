package com.company0ne.firebase_authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.company0ne.firebase_authentication.databinding.ActivityPhoneBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneActivity : AppCompatActivity() {

    lateinit var phoneBinding: ActivityPhoneBinding

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    lateinit var mCallbacks :PhoneAuthProvider.OnVerificationStateChangedCallbacks

    var verificationCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        phoneBinding = ActivityPhoneBinding.inflate(layoutInflater)
        val view = phoneBinding.root
        setContentView(view)

         //for international type
        phoneBinding.buttonSendSmsCode.setOnClickListener {

            val userPhoneNumber = phoneBinding.editTextPhoneNumber.text.toString()
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(userPhoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this@PhoneActivity)
                .setCallbacks(mCallbacks)
                .build()

            PhoneAuthProvider.verifyPhoneNumber(options)
        }
//        //for indian standard
//        phoneBinding.buttonSendSmsCode.setOnClickListener {
//            val userPhoneNumber = "+91" + phoneBinding.editTextPhoneNumber.text.toString()
//            val options = PhoneAuthOptions.newBuilder(auth)
//                .setPhoneNumber(userPhoneNumber)
//                .setTimeout(60L, TimeUnit.SECONDS)
//                .setActivity(this@PhoneActivity)
//                .setCallbacks(mCallbacks)
//                .build()
//
//            PhoneAuthProvider.verifyPhoneNumber(options)
//        }

        phoneBinding.buttonVerify.setOnClickListener {

            signinWithSMSCode()


        }

//
//        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
//                signinWithPhoneAuthCredential(p0)
//            }
//
//            override fun onVerificationFailed(p0: FirebaseException) {
//                Toast.makeText(applicationContext, p0.message, Toast.LENGTH_LONG).show()
//            }
//
//            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
//                super.onCodeSent(p0, p1)
//                verificationCode = p0
//            }
//        }
//    }
//
//    fun signinWithSMSCode() {
//        val userEnterCode = phoneBinding.editTextVerificationCode.text.toString()
//        val credential = PhoneAuthProvider.getCredential(verificationCode, userEnterCode)
//        signinWithPhoneAuthCredential(credential)
//    }
//
//    fun signinWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//        auth.signInWithCredential(credential).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val intent = Intent(this@PhoneActivity, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            } else {
//                Toast.makeText(applicationContext, "The code you entered is incorrect", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}
        mCallbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                signinWithPhoneAuthCredential(p0)


            }

            override fun onVerificationFailed(p0: FirebaseException) {

            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationCode = p0
            }

        }
    }
    fun signinWithSMSCode(){
        val userEnterCode =phoneBinding.editTextVerificationCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(verificationCode,userEnterCode)

        signinWithPhoneAuthCredential(credential)

    }
    //make an another function that makes the entry process according to these criteria
    fun signinWithPhoneAuthCredential(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if(task.isSuccessful){

                val intent = Intent(this@PhoneActivity,MainActivity::class.java)
                startActivity(intent)
                finish()

            }else {

                Toast.makeText(applicationContext,"The code you entered is InCorrect",Toast.LENGTH_SHORT).show()

            }
        }

    }
}

