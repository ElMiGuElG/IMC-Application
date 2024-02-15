package co.galeanom.imc_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class IMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnReCalculate: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcactivity)
        val result = intent.extras?.getDouble("IMC-Result") ?: -1.0
        initComponents()
        initListeners()
        initUI(result)
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when (result) {
            in 0.00..18.50 -> { //Bajo Peso
                tvResult.text = getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.lowWeight))
                tvDescription.text = getString(R.string.description_bajo_peso)
            }

            in 18.51..24.99 -> { //Peso Normal
                tvResult.text = getString(R.string.title_peso_normal)
                tvDescription.text = getString(R.string.description_peso_normal)
            }

            in 25.00..29.99 -> { //SobrePeso
                tvResult.text = getString(R.string.title_sobrepeso)
                tvDescription.text = getString(R.string.description_sobrepeso)
            }

            in 30.00..99.00 -> { //Obesidad
                tvResult.text = getString(R.string.title_obesidad)
                tvDescription.text = getString(R.string.description_obesidad)
            }

            else -> {
                tvResult.text = getString(R.string.error)
                tvIMC.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }

        }
    }

    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }

    private fun initListeners(){
        btnReCalculate.setOnClickListener {onBackPressed()}

    }

}