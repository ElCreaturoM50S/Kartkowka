package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wynik : Int = 0
        var osobyWynik : Int = 0
        var kartkowaWyniki : Int = 0
        var kacperWyniki : Float = 1f
        var dzienWynik : Int = 0

        val wynikView = findViewById<TextView>(R.id.wyniki)

        fun updateWynik() {
            wynik = (((((kacperWyniki-1) * 10)+ osobyWynik + kartkowaWyniki) * kacperWyniki) * dzienWynik).toInt()
            wynikView.text = "Wynik: ${wynik}%"
        }


        val lpInput = findViewById<CheckBox>(R.id.LPInput)
        lpInput.isChecked = true
        lpInput.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                lpInput.isChecked = true
            }
        }


        val osobyIlosc = findViewById<EditText>(R.id.osobyInput)
        osobyIlosc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text changes.
                if (s.isNullOrBlank()) {
                    osobyWynik = 0
                } else {
                    val enteredText = s.toString()
                    Log.d("Jarvis", "Entered Text: $enteredText")
                    osobyWynik = s.toString().toInt()
                    Log.d("Jarvis", "numer $osobyWynik")
                }
                updateWynik()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        val kartkowkaIlosc = findViewById<EditText>(R.id.kartkowkaInput)
        kartkowkaIlosc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text changes.
                if (s.isNullOrBlank()) {
                    kartkowaWyniki = 0
                } else {
                    val enteredText = s.toString()
                    Log.d("Jarvis", "Entered Text: $enteredText")
                    kartkowaWyniki = s.toString().toInt()
                    Log.d("Jarvis", "numer $kartkowaWyniki")
                    updateWynik()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        val czyJestKacper = findViewById<CheckBox>(R.id.kacperInput)
        czyJestKacper.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                kacperWyniki = 1f
            } else {
                kacperWyniki = 2.5f
            }

            updateWynik()
        }

        //dzien tygodnia
        val dniTygodnia = arrayOf("Niedziela","Poniedziałek","Wtorek","Środa","Czwartek","Piątek","Sobota")
        val dniTygodniaView = findViewById<TextView>(R.id.pracadomowa)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, dniTygodnia)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(applicationContext, dniTygodnia[p2],Toast.LENGTH_SHORT).show()
                dniTygodniaView.text = "Który jest dzień tygodnia?                             Wybrano: ${dniTygodnia[p2]}"
                if (p2 in (1..5)) {
                    dzienWynik = 1
                    if (p2 == 0) {
                        dzienWynik = 2
                    }
                } else {
                    dzienWynik = 0
                }
                updateWynik()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}