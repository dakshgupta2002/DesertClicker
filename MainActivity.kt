package com.example.tipcalculator

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        val msg= Toast.makeText(this,"Success!", Toast.LENGTH_SHORT)
        binding.button.setOnClickListener {msg.show()
            solution() }
    }
    private fun solution(){

        val coaster=binding.costOfServiceEditQuery.text.toString().toDoubleOrNull()
        if (coaster==null) {
            binding.textView2.text= this.getString(R.string.tip_amount_string, "0.00")
            return
        }

        val percent: Double = when(binding.Options.checkedRadioButtonId){
            R.id.radioButton->0.20
            R.id.radioButton2->0.15
            else -> 0.10
        }

        var tipcalc: Double=coaster*percent


        if (binding.switch1.isChecked){
            tipcalc= ceil(tipcalc)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tipcalc)
        binding.textView2.text= getString(R.string.tip_amount_string, formattedTip)
    }
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}