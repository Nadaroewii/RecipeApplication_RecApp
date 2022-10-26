package com.codingwithme.recipeapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.btnGetStarted
import kotlinx.android.synthetic.main.item_rv_sub_category.*
import kotlin.math.sign


class RegisterActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        btnGetStarted.setOnClickListener {
            val email = edEmail.text.toString().trim()
            val password = edPassword.text.toString().trim()
            val name = edName.text.toString().trim()
            if(TextUtils.isEmpty(name) ){
                edName.setError("Mohon Isi Nama");
                edName.requestFocus()
                return@setOnClickListener

            }
            if(TextUtils.isEmpty(email) ){
                edEmail.setError("Mohon Isi Email");
                edEmail.requestFocus()
                return@setOnClickListener

            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                edEmail.setError("Email Tidak Valid");
                edEmail.requestFocus()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password)) {
                edPassword.setError("Mohon Isi Password");
                edPassword.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 8) {
                edPassword.setError("Password Harus Lebih dari 8 Karakter");
                edPassword.requestFocus()
                return@setOnClickListener
            }
               registerUser(email, password)
            }
        }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("name",  edName.text.toString().trim())
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
