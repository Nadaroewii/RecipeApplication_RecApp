package com.codingwithme.recipeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.codingwithme.recipeapp.database.RecipeDatabase
import com.codingwithme.recipeapp.entities.Category
import com.codingwithme.recipeapp.entities.CategoryItems
import com.codingwithme.recipeapp.entities.Meal
import com.codingwithme.recipeapp.entities.MealsItems
import com.codingwithme.recipeapp.interfaces.GetDataService
import com.codingwithme.recipeapp.retofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.jar.Manifest
import android.R.id.home
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth


class SplashActivity : BaseActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        auth = FirebaseAuth.getInstance()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { //setelah loading maka akan langsung berpindah ke home activity
            if (auth.currentUser != null) {
                intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                val home = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(home)
            }
        }, 4000)
    }

}