package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        loadPreferences()

        binding.buttonSaveNumber.setOnClickListener {
            val number = binding.editTextNumber.text.toString().toIntOrNull()
            if (number != null){
                saveNumberPreference(number)
            }else {
                Toast.makeText(this, "Ingrese un número Valido", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonSaveText.setOnClickListener {
            val text = binding.editTextText.text.toString().trim()
            if (text.isNotEmpty()){
                saveTextPreference(text)
            }else{
                Toast.makeText(this, "Ingrese un texto Valido", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonSaveRadio.setOnClickListener {
            val isChecked = binding.radioGroup.checkedRadioButtonId == binding.radioButtonTrue.id
            saveRadioPreference(isChecked)
        }

        binding.buttonSaveDecimal.setOnClickListener {


            val decimal = binding.editTextDecimal.text.toString()
            if (!decimal.contains(".")){
                Toast.makeText(this, "Debe usar . para ingresar un decimal", Toast.LENGTH_SHORT).show()
            }else {
                val dec = decimal.toFloatOrNull()

                if (dec != null) {
                    saveDecimalPreference(dec)
                } else {
                    Toast.makeText(this, "Decimal ,Ingresado no es Valido", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.exitButton.setOnClickListener { finish() }



    }

    private fun saveNumberPreference(number: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("number", number)
        editor.apply()
        binding.textViewNumber.text = "Número guardado: $number"
    }

    private fun saveTextPreference(text: String) {
        val editor = sharedPreferences.edit()
        editor.putString("text", text)
        editor.apply()
        binding.textViewText.text = "Texto guardado: $text"
    }

    private fun saveRadioPreference(isChecked: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isChecked", isChecked)
        editor.apply()
        val value = if (isChecked) "Verdadero" else "Falso"
        binding.textViewRadio.text = "Valor guardado: $value"
    }

    private fun saveDecimalPreference(decimal: Float) {
        val editor = sharedPreferences.edit()
        editor.putFloat("decimal", decimal)
        editor.apply()
        binding.textViewDecimal.text = "Número decimal guardado: $decimal"
    }


    private fun loadPreferences() {
        val number = sharedPreferences.getInt("number", 0)
        binding.textViewNumber.text = "Número guardado: $number"

        val text = sharedPreferences.getString("text", "")
        binding.textViewText.text = "Texto guardado: $text"

        val isChecked = sharedPreferences.getBoolean("isChecked", false)
        val value = if (isChecked) "Verdadero" else "Falso"
        binding.textViewRadio.text = "Valor guardado: $value"

        val decimal = sharedPreferences.getFloat("decimal", 0f)
        binding.textViewDecimal.text = "Número decimal guardado: $decimal"
    }






}

