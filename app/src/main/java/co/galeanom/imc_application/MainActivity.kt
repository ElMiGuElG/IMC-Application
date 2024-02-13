package co.galeanom.imc_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat
import kotlin.contracts.Returns

class MainActivity : AppCompatActivity() {

    //Indicates whether male or female sex was selected.
    private var maleSelect: Boolean = true
    private var femaleSelect: Boolean = false

    //Represent views of the user interface
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:RangeSlider
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
            val Result = DecimalFormat.format(value)
            tvHeight.text = ("$Result cm")
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

    //Sets the initial color
    private fun initSetGenderColor() {
        setGenderColor()
    }

    /* Clase #3
    *
    *
    *
    *
    *
    *
    *
    * */


}