package com.example.colorpick

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity(),
    SeekBar.OnSeekBarChangeListener{

    private lateinit var mainView: View
    private lateinit var mainSeekBarRed:SeekBar
    private lateinit var mainSeekBarGreen:SeekBar
    private lateinit var mainSeekBarBlue:SeekBar
    private lateinit var redValues:EditText
    private lateinit var greenValues:EditText
    private lateinit var blueValues:EditText
    private lateinit var hexColor:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainView = findViewById(R.id.MainView)
        mainSeekBarRed = findViewById(R.id.MainSeekBarRed)
        mainSeekBarGreen = findViewById(R.id.MainSeekBarGreen)
        mainSeekBarBlue = findViewById(R.id.MainSeekBarBlue)
        redValues = findViewById(R.id.RedValues)
        greenValues = findViewById(R.id.GreenValues)
        blueValues = findViewById(R.id.BlueValues)
        hexColor = findViewById(R.id.HexColor)


        //mainSeekBar.visibility = View.INVISIBLE

        mainSeekBarRed.setOnSeekBarChangeListener(this)
        mainSeekBarGreen.setOnSeekBarChangeListener(this)
        mainSeekBarBlue.setOnSeekBarChangeListener(this)

        redValues.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()){
                    mainSeekBarRed.progress = 0
                    redValues.setHint("0")
                }else{
                    mainSeekBarRed.progress = p0.toString().toInt()
                    redValues.setHint("$p0.toString().toInt()")
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        greenValues.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()){
                    mainSeekBarGreen.progress = 0
                    greenValues.setHint("0")
                }else{
                    mainSeekBarGreen.progress = p0.toString().toInt()
                    greenValues.setHint("$p0.toString().toInt()")
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        blueValues.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()){
                    mainSeekBarBlue.progress = 0
                    blueValues.setHint("0")
                }else{
                    mainSeekBarBlue.progress = p0.toString().toInt()
                    blueValues.setHint("$p0.toString().toInt()")
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        // mainView.setBackgroundColor(Color.rgb(progress, progress, progress))
        val seekBarId: Int? = seekBar?.id

        when (seekBarId) {
            R.id.MainSeekBarRed -> {
                mainView.setBackgroundColor(Color.rgb(progress, mainSeekBarGreen.progress, mainSeekBarBlue.progress))
                redValues.setHint("$progress")
                hexColor.setTextColor(Color.rgb(255 - mainSeekBarRed.progress, 255 - mainSeekBarGreen.progress, 255 - mainSeekBarBlue.progress))
            }
            R.id.MainSeekBarGreen -> {
                mainView.setBackgroundColor(Color.rgb(mainSeekBarRed.progress, progress, mainSeekBarBlue.progress))
                greenValues.setHint("$progress")
                hexColor.setTextColor(Color.rgb(255 - mainSeekBarRed.progress, 255 - mainSeekBarGreen.progress, 255 - mainSeekBarBlue.progress))
            }
            R.id.MainSeekBarBlue -> {
                mainView.setBackgroundColor(Color.rgb(mainSeekBarRed.progress, mainSeekBarGreen.progress, progress))
                blueValues.setHint("$progress")
                hexColor.setTextColor(Color.rgb(255 - mainSeekBarRed.progress, 255 - mainSeekBarGreen.progress, 255 - mainSeekBarBlue.progress))
            }
        }

        hexColor.setText(
            "#" +
            String.format("%02X", mainSeekBarRed.progress) +
            String.format("%02X", mainSeekBarGreen.progress) +
            String.format("%02X", mainSeekBarBlue.progress)
        )
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
}
