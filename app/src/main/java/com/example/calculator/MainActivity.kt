package com.example.calculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding
import kotlin.time.times

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var girdi = ""
    private var ilkIslenen = 0f
    private var sonuc = 0f
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

        binding.ekleme.setOnClickListener {
            when {
                girdi.toIntOrNull() != null -> {
                    ekle()
                }
                girdi.toFloatOrNull() != null -> {
                    ekleFloat()
                }
            }
        }
        binding.yuzde.setOnClickListener { yuzde() }
        binding.esit.setOnClickListener { esit() }
        binding.hepsiniSil.setOnClickListener { hepsiniSil() }
        binding.cikarma.setOnClickListener{ cikarma() }
        binding.carpma.setOnClickListener { carpma() }
        binding.bolme.setOnClickListener { bolme() }
        binding.sifirSayi.setOnClickListener { appendNumber("0") }
        binding.birSayi.setOnClickListener { appendNumber("1") }
        binding.ikiSayi.setOnClickListener { appendNumber("2") }
        binding.ucSayi.setOnClickListener { appendNumber("3") }
        binding.dortSayi.setOnClickListener { appendNumber("4") }
        binding.besSayi.setOnClickListener { appendNumber("5") }
        binding.altiSayi.setOnClickListener { appendNumber("6") }
        binding.yediSayi.setOnClickListener { appendNumber("7") }
        binding.sekizSayi.setOnClickListener { appendNumber("8") }
        binding.dokuzSayi.setOnClickListener { appendNumber("9") }
    }


    private fun hepsiniSil() {
        girdi = ""
        ilkIslenen = 0f
        binding.islemText.text = ""
        binding.operatorSign.text = ""
        sonuc = 0f
        binding.sonuc.text = ""
        gecmis = ""

    }

    private fun appendNumber(number: String) {
        girdi += number
        binding.islemText.text = girdi
    }

    private fun silme() {
        if (girdi.isNotEmpty()) {
            girdi = girdi.dropLast(1)


            binding.islemText.text = girdi
        }
    }

    private fun ekle() {
        val currentInput = girdi.toIntOrNull() ?: 0

        binding.operatorSign.text = "+"
        ilkIslenen += currentInput

        binding.islemText.text = ilkIslenen.toString()

        girdi = ""
        gecmis+= "$ilkIslenen\n+ "
    }
    private fun ekleFloat() {
        val currentInput = girdi.toFloatOrNull() ?: 0f

        if (binding.operatorSign.text == "+") {
            // Update `ilkIslenen` and clear `girdi`
            ilkIslenen += currentInput
            girdi = ""
        } else {
            // For the first addition, initialize `ilkIslenen`
            ilkIslenen = currentInput
        }

        // Set the operator and update the displayed result
        binding.operatorSign.text = "+"
        binding.islemText.text = if (ilkIslenen % 1 == 0f) ilkIslenen.toInt().toString() else ilkIslenen.toString()

        // Update `gecmis` with the expression
        gecmis += if (gecmis.isEmpty()) "$currentInput + " else "$currentInput + "
    }
    private fun cikarma() {
        var currentInput = girdi.toFloatOrNull() ?: 0f

        binding.operatorSign.text = "-"
        ilkIslenen -= currentInput

        binding.islemText.text = ilkIslenen.toString()

        girdi = ""

        gecmis+= "\n-\n $ilkIslenen"
    }
    private fun carpma() {
        val currentInput = girdi.toFloatOrNull() ?: 1f
        if (ilkIslenen == 0f) {
            ilkIslenen = currentInput
        } else {
            ilkIslenen *= currentInput
        }

        binding.operatorSign.text = "*"

        binding.islemText.text = if (ilkIslenen % 1 == 0f) {
            ilkIslenen.toInt().toString()
        } else {
            ilkIslenen.toString()
        }

        gecmis += "$currentInput *"
        girdi = ""
    }
    private fun bolme() {
        ilkIslenen = 1f
        val currentInput = girdi.toFloatOrNull() ?: 0f

        binding.operatorSign.text = "/"
        ilkIslenen /= currentInput

        binding.islemText.text = ilkIslenen.toString()

        girdi = ""

        gecmis+= "\n / \n $ilkIslenen"
    }
    private fun yuzde() {
        var currentInput = girdi.toFloatOrNull() ?: 0f

        binding.operatorSign.text = "%"
        currentInput *= 0.01f

        binding.islemText.text = "${currentInput}%"
        gecmis+= "$girdi%= $currentInput\n"
        girdi = ""
    }
    private fun esit() {
         currentInput = girdi.toFloatOrNull() ?: 0f

        // Calculate final result based on the operator
        when (binding.operatorSign.text) {
            "+" -> ilkIslenen += currentInput
            "-" -> ilkIslenen -= currentInput
            "*" -> ilkIslenen *= currentInput
            "/" -> if (currentInput != 0f) ilkIslenen /= currentInput else binding.islemText.text = "Error"
        }

        // Display the final result
        binding.islemText.text = if (ilkIslenen % 1 == 0f) ilkIslenen.toInt().toString() else ilkIslenen.toString()
        binding.operatorSign.text = ""  // Clear operator

        // Update `gecmis` to reflect the full calculation
        gecmis += "$currentInput = $ilkIslenen\n"

        // Clear `girdi` for next calculation
        girdi = ""
    }


}
