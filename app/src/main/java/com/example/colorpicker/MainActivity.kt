package com.example.colorpicker

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_BUTTON_PRESS
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {


    private lateinit var resetBtn : Button
    private lateinit var colorView: View
    private lateinit var redSwitch: Switch
    private lateinit var redSeekBar: SeekBar
    private lateinit var redTextView: TextView
    private lateinit var greenSwitch: Switch
    private lateinit var greenSeekBar: SeekBar
    private lateinit var greenTextView: TextView
    private lateinit var blueSwitch: Switch
    private lateinit var blueSeekBar: SeekBar
    private lateinit var blueTextView: TextView
    private var redColorValue = 0
    private var greenColorValue = 0
    private var blueColorValue = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        resetBtn = findViewById(R.id.resetButton)
        colorView = findViewById(R.id.colorView)
        redSwitch = findViewById(R.id.redSwitch)
        redSeekBar = findViewById(R.id.redSeekBar)
        redTextView = findViewById(R.id.redTxt)
        greenSwitch = findViewById(R.id.greenSwitch)
        greenSeekBar = findViewById(R.id.greenSeekBar)
        greenTextView = findViewById(R.id.greenTxt)
        blueSwitch = findViewById(R.id.blueSwitch)
        blueSeekBar = findViewById(R.id.blueSeekBar)
        blueTextView = findViewById(R.id.blueTxt)

        resetBtn.setOnClickListener {
            redSwitch.isChecked = false
            greenSwitch.isChecked = false
            blueSwitch.isChecked = false
            redSeekBar.isEnabled = true
            redSeekBar.progress = 0
            redColorValue = 0
            redTextView.text = String.format("%.2f", (redColorValue / 255f))

            greenSeekBar.isEnabled = true
            greenColorValue = 0;
            greenSeekBar.isEnabled = true
            greenSeekBar.progress = 0
            greenTextView.text = String.format("%.2f", (greenColorValue / 255f))

            blueSeekBar.isEnabled = true
            blueColorValue = 0;
            blueSeekBar.isEnabled = true
            blueSeekBar.progress = 0
            blueTextView.text = String.format("%.2f", (blueColorValue / 255f))

            setRGBColor()
        }
        redSwitch.setOnClickListener{
            if(redSwitch.isChecked)
                redSeekBar.isEnabled = false
            else
                redSeekBar.isEnabled = true

            redColorValue = 0;
            redSeekBar.progress = 0
            redTextView.text = String.format("%.2f", (redColorValue / 255f))
            setRGBColor()
        }

        greenSwitch.setOnClickListener{
            if(greenSwitch.isChecked)
                greenSeekBar.isEnabled = false
            else
                greenSeekBar.isEnabled = true

            greenColorValue = 0;
            greenSeekBar.progress = 0
            greenTextView.text = String.format("%.2f", (greenColorValue / 255f))
            setRGBColor()
        }

        blueSwitch.setOnClickListener{
            if(blueSwitch.isChecked)
                blueSeekBar.isEnabled = false
            else
                blueSeekBar.isEnabled = true

            blueColorValue = 0;
            blueSeekBar.progress = 0
            blueTextView.text = String.format("%.2f", (blueColorValue / 255f))
            setRGBColor()
        }

        redSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                redColorValue = seekBar.progress.toInt()
                redTextView.text = String.format("%.2f", (redColorValue / 255f))

                setRGBColor()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

        greenSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                greenColorValue = seekBar.progress.toInt()
                greenTextView.text = String.format("%.2f", (greenColorValue / 255f))

                setRGBColor()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

        blueSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                blueColorValue = seekBar.progress.toInt()
                blueTextView.text = String.format("%.2f", (blueColorValue / 255f))

                setRGBColor()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

    }

    private fun setRGBColor():String {
        val hex = String.format(
            "#%02x%02x%02x",
            redColorValue,
            greenColorValue,
            blueColorValue
        )
        colorView.setBackgroundColor(Color.parseColor(hex))
        return hex
    }
}
