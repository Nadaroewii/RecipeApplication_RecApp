package com.codingwithme.recipeapp


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.codingwithme.recipeapp.entities.MealResponse
import com.codingwithme.recipeapp.interfaces.GetDataService
import com.codingwithme.recipeapp.retofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : BaseActivity() {

    var youtubeLink = ""
    var sourceLink = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var id = intent.getStringExtra("id")

        getSpecificItem(id!!)

        imgToolbarBtnBack.setOnClickListener {
            finish()
        }

        btnReference.setOnClickListener {
            val uri = Uri.parse(sourceLink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        imInstructions.setOnClickListener{
            val uri = Uri.parse(youtubeLink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }


    }

    fun getSpecificItem(id:String) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getSpecificItem(id)
        call.enqueue(object : Callback<MealResponse> {
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {

                Toast.makeText(this@DetailActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(
                call: Call<MealResponse>,
                response: Response<MealResponse>
            ) {

                Glide.with(this@DetailActivity).load(response.body()!!.mealsEntity[0].strmealthumb).into(imgItem)

                tvCategory.text = response.body()!!.mealsEntity[0].strmeal

                var ingredient = " ${response.body()!!.mealsEntity[0].stringredient1}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient2}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient3}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient4}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient5}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient6}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient7}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient8}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient9}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient10}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient11}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient12}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient13}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient14}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient15}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient16}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient17}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient18}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient19}\n" +
                        " ${response.body()!!.mealsEntity[0].stringredient20}\n"

                var ingredient2 = "${response.body()!!.mealsEntity[0].strmeasure1}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure2}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure3}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure4}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure5}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure6}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure7}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure8}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure9}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure10}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure11}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure12}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure13}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure14}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure15}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure16}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure17}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure18}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure19}  \t\n" +
                        "${response.body()!!.mealsEntity[0].strmeasure20}  \t\n"

                tvIngredients.text = ingredient
                tvIngredients2.text = ingredient2
                tvInstructions.text = response.body()!!.mealsEntity[0].strinstructions

                if (response.body()!!.mealsEntity[0].strsource != null){
                    sourceLink = response.body()!!.mealsEntity[0].strsource
                }else{
                    btnReference.visibility = View.GONE
                }

                if (response.body()!!.mealsEntity[0].stryoutube != null){
                    youtubeLink = response.body()!!.mealsEntity[0].stryoutube
                }else{
                    imInstructions.visibility = View.GONE
                }
            }

        })
    }


}