package com.example.chat_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    private val TAG = "MyActivity"
    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var btLogin : Button
    private lateinit var btSignUp : Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.loginEmail)
        edtPassword = findViewById(R.id.loginPassword)
        btLogin = findViewById(R.id.loginButton)
        btSignUp = findViewById(R.id.signupButton)


        btSignUp.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        btLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email,password)
        }
    }

    private fun login(email : String,password : String){
        //Logic for logging a user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "LoginWithEmail:success")
                    val intent = Intent(this@LogIn,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "LoginWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "User does not exist or the password you've entered is incorrect.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}