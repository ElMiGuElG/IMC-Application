package co.galeanom.imc_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import kotlin.contracts.Returns

class MainActivity : AppCompatActivity() {

    private var maleSelect: Boolean = true
    private var femaleSelect: Boolean = false

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
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
    }
    private fun changeGender(){
        maleSelect = !maleSelect
        femaleSelect = !femaleSelect
    }
    private fun setGenderColor (){
        viewMale.setCardBackgroundColor(getBackgroundColor(maleSelect))
        viewFemale.setCardBackgroundColor(getBackgroundColor(femaleSelect))
    }
    private fun getBackgroundColor(isSelect: Boolean): Int {
        val actualColor = if (isSelect) {
            R.color.backgroundComponentSelect
        } else {
            R.color.backgroundComponent
        }
        return ContextCompat.getColor(this, actualColor) //Method of accessing colour
    }

    private fun initSetGenderColor (){
        setGenderColor()
    }
}