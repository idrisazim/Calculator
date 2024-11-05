package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var girdi = ""
    private var sonuc = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.sil.setOnClickListener{
            silme()
        }
        binding.ekleme.setOnClickListener{
            ekle()
        }

        binding.hepsiniSil.setOnClickListener {
            hepsiniSil()
        }
        binding.sifirSayi.setOnClickListener {
            sifirSayi()
        }
        binding.birSayi.setOnClickListener {
            birSayi()
        }
        binding.ikiSayi.setOnClickListener {
            ikiSayi()
        }
        binding.ucSayi.setOnClickListener {
            ucSayi()
        }
        binding.dortSayi.setOnClickListener {
            dortSayi()
        }
        binding.besSayi.setOnClickListener {
            besSayi()
        }
        binding.altiSayi.setOnClickListener {
            altiSayi()
        }
        binding.yediSayi.setOnClickListener {
            yediSayi()
        }
        binding.sekizSayi.setOnClickListener {
            sekizSayi()
        }
        binding.dokuzSayi.setOnClickListener {
            dokuzSayi()
        }
    }

    //rakam butonlarina basma islemleri:

    private fun hepsiniSil() {
        girdi = ""
        binding.islemText.text = girdi
        binding.operatorSign.text = ""
    }

    private fun birSayi() {
        if (binding.operatorSign.text == "+"){
            girdi = "0"
            binding.operatorSign.text = ""
        }
        girdi += "1"
        binding.islemText.text = girdi
    }
    private fun ikiSayi() {
        girdi += "2"
        binding.islemText.text = girdi
    }
    private fun ucSayi() {
        girdi += "3"
        binding.islemText.text = girdi
    }
    private fun dortSayi() {
        girdi += "4"
        binding.islemText.text = girdi
    }
    private fun besSayi() {
        girdi += "5"
        binding.islemText.text = girdi
    }
    private fun altiSayi() {
        girdi += "6"
        binding.islemText.text = girdi
    }
    private fun yediSayi() {
        girdi += "7"
        binding.islemText.text = girdi
    }
    private fun sekizSayi() {
        girdi += "8"
        binding.islemText.text = girdi
    }
    private fun dokuzSayi() {
        girdi += "9"
        binding.islemText.text = girdi
    }
    private fun sifirSayi() {
        girdi += "0"
        binding.islemText.text = girdi
    }

    //bir rakam silme islemi
    private fun silme() {
        if (girdi.isNotEmpty()) {
            girdi = girdi.dropLast(1)
            binding.islemText.text = girdi
        } else {
             girdi = ""
        }
    }

    private fun ekle(){
        var string1 = girdi
        var string2 = ""
        binding.operatorSign.text = "+"
        girdi = ""
        string2 = binding.islemText.text.toString()
        sonuc = string1.toInt() + string2.toInt()



    }


}