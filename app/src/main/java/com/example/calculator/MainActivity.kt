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
    private var firstOperand = 0

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

        binding.sil.setOnClickListener { silme() }
        binding.ekleme.setOnClickListener { ekle() }
        binding.hepsiniSil.setOnClickListener { hepsiniSil() }
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
        firstOperand = 0
        binding.islemText.text = ""
        binding.operatorSign.text = ""
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
        // Parse `girdi` to an integer or default to 0 if parsing fails
        val currentInput = girdi.toIntOrNull() ?: 0
        binding.operatorSign.text = "+"
        // Add the current input to the running total
        firstOperand += currentInput

        // Display the updated total
        binding.islemText.text = firstOperand.toString()

        // Reset `girdi` for the next input
        girdi = ""
    }
}
