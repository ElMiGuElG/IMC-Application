package co.galeanom.imc_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat
import kotlin.contracts.Returns

class MainActivity : AppCompatActivity() {

    //Indicates whether male or female sex was selected.
    private var maleSelect: Boolean = true
    private var femaleSelect: Boolean = false
    private var Weight: Int = 70
    private var Height: Int = 150
    private var Age: Int = 20

    //Represent views of the user interface
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubtWeight: FloatingActionButton
    private lateinit var btnSumWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtAge: FloatingActionButton
    private lateinit var btnSumAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
        initListener()
        initSetGenderColor()
    }

    //Initialise the components
    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSubtWeight = findViewById(R.id.btnSubtWeight)
        btnSumWeight = findViewById(R.id.btnSumWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtAge = findViewById(R.id.btnSubtAge)
        btnSumAge = findViewById(R.id.btnSumAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    //Place a click listener
    private fun initListener() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val DecimalFormat = DecimalFormat("#.##") // Formatear para no tener decinmales
            Height = DecimalFormat.format(value).toInt()
            tvHeight.text = ("$Height cm")
        }
        btnSumWeight.setOnClickListener {
            Weight = Weight + 1
            setWeight()
        }
        btnSubtWeight.setOnClickListener {
            Weight = Weight - 1
            setWeight()
        }
        btnSumAge.setOnClickListener {
            Age = Age + 1
            setAge()
        }
        btnSubtAge.setOnClickListener {
            Age = Age - 1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val Result = CalculateIMC()
            Navegate (Result)
        }
    }

    //Change background colour
    private fun changeGender() {
        maleSelect = !maleSelect
        femaleSelect = !femaleSelect
    }

    //Get the corresponding color and then set the color of the views.
    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(maleSelect))
        viewFemale.setCardBackgroundColor(getBackgroundColor(femaleSelect))
    }

    //Returns the corresponding background color
    private fun getBackgroundColor(isSelect: Boolean): Int {
        val actualColor = if (isSelect) {
            R.color.backgroundComponentSelect
        } else {
            R.color.backgroundComponent
        }
        return ContextCompat.getColor(this, actualColor) //Method of accessing colour
    }

    //Plus or Sustrain the Value
    private fun setWeight() {
        tvWeight.text = Weight.toString()
    }

    private fun setAge() {
        tvAge.text = Age.toString()
    }

    private fun CalculateIMC():Double {
        val DecimalFormat = DecimalFormat("#.##")
        val imc = Weight / (Height.toDouble() / 100 * Height.toDouble() / 100)
        return DecimalFormat.format(imc).toDouble()
    }

    private fun Navegate(Result:Double){
        val intent = Intent(this, IMCActivity::class.java)
        intent.putExtra("IMC-Result", Result)
        startActivity(intent)
    }

    //Sets the initial color
    private fun initSetGenderColor() {
        setGenderColor()
        setWeight()
        setAge()
    }

}