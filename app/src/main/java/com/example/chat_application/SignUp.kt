package com.example.chat_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private val TAG = "MyActivity"
    private lateinit var edtName : EditText
    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private  lateinit var edtConfirmPassword : EditText
    private lateinit var btSignUp : Button

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()
        edtName = findViewById(R.id.name)
        edtEmail = findViewById(R.id.email)
        edtPassword = findViewById(R.id.signUpPassword)
        edtConfirmPassword = findViewById(R.id.confirmPassword)
        btSignUp = findViewById(R.id.signupButton)

        supportActionBar?.hide()

        btSignUp.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val confirmPassword = edtConfirmPassword.text.toString()
            if(password == confirmPassword) {
                signUp(name,email, password)
            }else{
                Toast.makeText(this@SignUp,"Passwords does not match!",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signUp(name : String,email: String, password: String){
        //Logic for creating new user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                        addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                        Log.d(TAG,"User creation : SUCCESS")
                        val intent = Intent(this@SignUp, MainActivity::class.java)

                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d(TAG,"User creation : FAILURE")
                    Toast.makeText(this@SignUp,"Some error occurred! Please try after sometime.",Toast.LENGTH_SHORT).show()
                }
            }

    }
    private fun addUserToDatabase(name: String,email: String,uid : String){

    }
}