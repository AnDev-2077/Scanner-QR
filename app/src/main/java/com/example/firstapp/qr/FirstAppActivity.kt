package com.example.firstapp.qr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityFirstAppBinding
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class FirstAppActivity : AppCompatActivity() {


    private lateinit var binding:ActivityFirstAppBinding
    private lateinit var scannerLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScanner.setOnClickListener{initSacanner() }

        val retrofitEstado = RetofitClient.ConsumirAPI.getEstado()

        retrofitEstado.enqueue(object : Callback, retrofit2.Callback<objeto> {
            override fun onResponse(call: Call<objeto>, response: Response<objeto>) {
                Toast.makeText(this@FirstAppActivity, "Respuesta exitosa", Toast.LENGTH_SHORT).show()
                //binding.tvMostrar.text = response.body().toString()
            }

            override fun onFailure(call: Call<objeto>, t: Throwable) {
                Toast.makeText(this@FirstAppActivity, "Error en la respuesta", Toast.LENGTH_SHORT).show()
            }
        })

    }
    private fun initSacanner(){
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Enfoca el codigo QR dentro del recuadro")
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        val tvResult: TextView = findViewById(R.id.tvResult)
        if (result != null) {
            if (result.contents != null) {
                tvResult.text = result.contents
                Toast.makeText(this, "El valor escaneado: " + result.contents, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Cancelado!", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}