package edu.uw.ischool.yc324.tipcalc

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextAmount: EditText = findViewById(R.id.amountEditText)
        val buttonTip: Button = findViewById(R.id.tipButton)
        val spinnerTipPercentage: Spinner = findViewById(R.id.tipPercentageSpinner)

        val percentages = arrayOf("10%", "15%", "18%", "20%")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, percentages)
        spinnerTipPercentage.adapter = adapter

        buttonTip.isEnabled = false

        editTextAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                buttonTip.isEnabled = p0?.isNotEmpty() == true
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        buttonTip.setOnClickListener {
            val amount = editTextAmount.text.toString().replace("$", "").toDouble()
            val selectedPercentage = when (spinnerTipPercentage.selectedItem.toString()) {
                "10%" -> 0.10
                "15%" -> 0.15
                "18%" -> 0.18
                "20%" -> 0.20
                else -> 0.15
            }
            val tipAmount = String.format("%.2f", amount * selectedPercentage)
            Toast.makeText(this, "$$tipAmount", Toast.LENGTH_SHORT).show()
        }
    }
}
