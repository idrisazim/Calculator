package com.example.calculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var girdi = ""
    private var ilkIslenen = 0f
    private var gecmis = ""
    private var currentInput = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()

        binding.gecmis.setOnClickListener {
            val intent = Intent(this, Gecmis::class.java)
            intent.putExtra("gecmis", gecmis)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.sil.setOnClickListener { silme() }
        binding.ekleme.setOnClickListener { ekle() }
        binding.cikarma.setOnClickListener { cikarma() }
        binding.carpma.setOnClickListener { carpma() }
        binding.bolme.setOnClickListener { bolme() }

        binding.yuzde.setOnClickListener { yuzde() }
        binding.esit.setOnClickListener { esit() }
        binding.hepsiniSil.setOnClickListener { hepsiniSil() }

        binding.sifirSayi.setOnClickListener { sayiButon("0") }
        binding.birSayi.setOnClickListener { sayiButon("1") }
        binding.ikiSayi.setOnClickListener { sayiButon("2") }
        binding.ucSayi.setOnClickListener { sayiButon("3") }
        binding.dortSayi.setOnClickListener { sayiButon("4") }
        binding.besSayi.setOnClickListener { sayiButon("5") }
        binding.altiSayi.setOnClickListener { sayiButon("6") }
        binding.yediSayi.setOnClickListener { sayiButon("7") }
        binding.sekizSayi.setOnClickListener { sayiButon("8") }
        binding.dokuzSayi.setOnClickListener { sayiButon("9") }
        binding.virgulSayi.setOnClickListener { sayiButon(".") }
    }

    private fun hepsiniSil() {
        girdi = ""
        ilkIslenen = 0f
        binding.islemText.text = ""
        binding.islemIsareti.text = ""
        gecmis = ""
    }

    private fun sayiButon(number: String) {
        girdi += number
        binding.islemText.text = girdi
    }

    private fun silme() {
        if (girdi.isNotEmpty()) {
            girdi = girdi.dropLast(1)
            binding.islemText.text = girdi
        }
        else {
            girdi = ""
        }
    }

    private fun ekle() {
        val currentInput = girdi.toFloatOrNull() ?: 0f
        binding.islemIsareti.text = "+"
        ilkIslenen += currentInput
        if (ilkIslenen % 1 == 0f) {
            binding.islemText.text = ilkIslenen.toInt().toString()
            gecmis+= "${ilkIslenen.toInt()} + "
        }
        else {
            binding.islemText.text = ilkIslenen.toString()
            gecmis+= "$ilkIslenen + "
        }

        girdi = ""

    }

    private fun cikarma() {

        val currentInput = girdi.toFloatOrNull() ?: 0f
        binding.islemIsareti.text = "-"
        ilkIslenen = currentInput - ilkIslenen

        if (ilkIslenen % 1 == 0f) {
            binding.islemText.text = ilkIslenen.toInt().toString()
            gecmis+= "${ilkIslenen.toInt()} - "
        }
        else {
            binding.islemText.text = ilkIslenen.toString()
            gecmis+= "$ilkIslenen - "
        }
        girdi = ""
    }

    private fun carpma() {

        val currentInput = girdi.toFloatOrNull() ?: 1f
        if (ilkIslenen == 0f) {
            ilkIslenen = currentInput
        } else {
            ilkIslenen *= currentInput
        }

        binding.islemIsareti.text = "*"

        if (ilkIslenen % 1 == 0f) {
            binding.islemText.text = ilkIslenen.toInt().toString()
            gecmis += "${currentInput.toInt()} * "

        } else {
            binding.islemText.text = ilkIslenen.toString()
            gecmis += "$currentInput * "
        }

        girdi = ""
    }

    private fun bolme() {
        val currentInput = girdi.toFloatOrNull() ?: 1f
        if (ilkIslenen == 0f) {
            ilkIslenen = currentInput
        } else {
            ilkIslenen = currentInput
        }

        binding.islemIsareti.text = "/"

        if (ilkIslenen % 1 == 0f) {
            binding.islemText.text = ilkIslenen.toInt().toString()
            gecmis += "${currentInput.toInt()} / "

        } else {
            binding.islemText.text = ilkIslenen.toString()
            gecmis += "$currentInput / "
        }
        girdi = ""
    }

    private fun yuzde() {
        var currentInput = girdi.toFloatOrNull() ?: 1f

        binding.islemIsareti.text = "%"
        ilkIslenen = currentInput

        if (ilkIslenen % 1 == 0f) {
            binding.islemText.text = "${currentInput.toInt()}%"
            gecmis+= "${currentInput.toInt()} % "
        }
        else {
            binding.islemText.text = "${currentInput}%"
            gecmis+= "$currentInput % "
        }
        girdi = ""
    }

    private fun esit() {
         currentInput = girdi.toFloat()

        when (binding.islemIsareti.text) {
            "+" -> ilkIslenen += currentInput
            "-" -> ilkIslenen -= currentInput
            "*" -> ilkIslenen *= currentInput
            "/" -> ilkIslenen /= currentInput
            "%" -> ilkIslenen *= (currentInput / 100)
        }

        binding.islemText.text = if (ilkIslenen % 1 == 0f) ilkIslenen.toInt().toString() else ilkIslenen.toString()
        binding.islemIsareti.text = ""

        if (ilkIslenen % 1 == 0f) {
            gecmis += "${currentInput.toInt()} = ${ilkIslenen.toInt()}\n"
        }
        else {
            gecmis += "$currentInput = $ilkIslenen\n"
        }

        ilkIslenen = 0f
        girdi = ""
    }
}
