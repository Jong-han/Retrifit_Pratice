package halla.icsw.retrifit_pratice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = Retrofit_Interface.create()
        textView.text = "유가정보"

        button.setOnClickListener {
            api.getOilStationInfo("F792200616", 367061.00000, 545435.00000, 100, 2, "B027", "json")
                .enqueue(object : Callback<Result> {
                    override fun onResponse(call: Call<Result>, response: Response<Result>) {
                        var success = response.isSuccessful
                        if (success) {
                            for (i in response.body()?.result?.oil!!){
                                textView.text = i.oilPrice.toString()
                                break;
                            }
                        } else {
                            textView.text = "실패"
                        }
                    }

                    override fun onFailure(call: Call<Result>, t: Throwable) {
                        textView.text = "실패" + t.message
                    }

                })
        }
    }
}