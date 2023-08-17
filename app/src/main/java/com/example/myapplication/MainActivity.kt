package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton: Button = findViewById(R.id.idBtn)
        val text1 : TextView = findViewById(R.id.txt1)

        val img: ImageView = findViewById(R.id.imgPerritos)
        val editxt: EditText = findViewById(R.id.editxt)

        boton.setOnClickListener {
            var id: Editable? = editxt.text
            consultarApi(id,img,this, text1)

        }

    }

}
fun consultarApi (id: Editable?, img:ImageView, context: Context, text1:TextView) {
    GlobalScope.launch(Dispatchers.Main) {
        try {
            val response = ApiClient.apiService.obtenerImagenPersoanje(id)
            if (response.isSuccessful) {
                val nombre =response.body()?.name
                val imageUrl = response.body()?.image
                text1.text = nombre
                Glide.with(context).load(imageUrl).into(img)
                // Manejar la lista de razas aquí
            } else {
                // Manejar errores de respuesta aquí
            }
        } catch (e: IOException) {
            // Manejar errores de red aquí
        }
    }
}

