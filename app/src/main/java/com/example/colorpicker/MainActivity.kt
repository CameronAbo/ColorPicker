package com.example.colorpicker

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
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
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
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
    private var savedRValue = 0
    private var savedGValue = 0
    private var savedBValue = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.connectViews()
        this.setupResetButtonCallback()
        this.setupSwitchCallbacks()
        this.setupSeekBarCallbacks()
        this.setupTextEditCallbacks()


    }

    private fun connectViews(){
        this.resetBtn = findViewById(R.id.resetButton)
        this.colorView = findViewById(R.id.colorView)
        this.redSwitch = findViewById(R.id.redSwitch)
        this.redSeekBar = findViewById(R.id.redSeekBar)
        this.redTextView = findViewById(R.id.redTxt)
        this.greenSwitch = findViewById(R.id.greenSwitch)
        this.greenSeekBar = findViewById(R.id.greenSeekBar)
        this.greenTextView = findViewById(R.id.greenTxt)
        this.blueSwitch = findViewById(R.id.blueSwitch)
        this.blueSeekBar = findViewById(R.id.blueSeekBar)
        this.blueTextView = findViewById(R.id.blueTxt)
    }

    private fun setupResetButtonCallback(){
        this.resetBtn.setOnClickListener {
            this.redSwitch.isChecked = false
            this.greenSwitch.isChecked = false
            this.blueSwitch.isChecked = false
            this.redSeekBar.isEnabled = true
            this.redSeekBar.progress = 0
            this.redColorValue = 0
            this.savedRValue = this.redColorValue
            this.redTextView.text = String.format("%.2f", (this.savedRValue / 255f))

            this.greenSeekBar.isEnabled = true
            this.greenColorValue = 0;
            this.greenSeekBar.isEnabled = true
            this.greenSeekBar.progress = 0
            this.savedGValue = this.greenColorValue
            this.greenTextView.text = String.format("%.2f", (this.savedGValue / 255f))

            this.blueSeekBar.isEnabled = true
            this.blueColorValue = 0;
            this.blueSeekBar.isEnabled = true
            this.blueSeekBar.progress = 0
            this.savedBValue = this.blueColorValue
            this.blueTextView.text = String.format("%.2f", (this.savedBValue / 255f))

            this.setRGBColor()
        }
    }

    private fun setupSwitchCallbacks(){
        this.redSwitch.setOnClickListener{
            if(this.redSwitch.isChecked) {
                this.redSeekBar.isEnabled = false
                this.redTextView.isEnabled = false
                this.redColorValue = 0
            }
            else {
                this.redSeekBar.isEnabled = true
                this.redTextView.isEnabled = true
                this.redColorValue = savedRValue
            }
            this.setRGBColor()
        }

        this.greenSwitch.setOnClickListener{
            if(this.greenSwitch.isChecked) {
                this.greenSeekBar.isEnabled = false
                this.greenTextView.isEnabled = false
                this.greenColorValue = 0
            }
            else {
                this.greenSeekBar.isEnabled = true
                this.greenTextView.isEnabled = true
                this.greenColorValue = savedGValue
            }

            this.setRGBColor()
        }

        this.blueSwitch.setOnClickListener{
            if(this.blueSwitch.isChecked){
                this.blueSeekBar.isEnabled = false
                this.blueTextView.isEnabled = false
                this.blueColorValue = 0
            }
            else{
                this.blueSeekBar.isEnabled = true
                this.blueTextView.isEnabled = true
                this.blueColorValue = savedBValue
            }

            this.setRGBColor()
        }
    }

    private fun setupSeekBarCallbacks(){
        this.redSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                savedRValue = seekBar.progress.toInt()
                redColorValue = savedRValue
                redTextView.text = String.format("%.2f", (redColorValue / 255f))

                setRGBColor()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

        this.greenSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                savedGValue = seekBar.progress.toInt()
                greenColorValue = savedGValue
                greenTextView.text = String.format("%.2f", (greenColorValue / 255f))

                setRGBColor()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

        this.blueSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                savedBValue = seekBar.progress.toInt()
                blueColorValue = savedBValue
                blueTextView.text = String.format("%.2f", (blueColorValue / 255f))

                setRGBColor()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    private fun setupTextEditCallbacks() {
        this.redTextView.setOnKeyListener { view, i, keyEvent ->
            if(i == KeyEvent.KEYCODE_ENTER)
            {
                if(!redTextView.text.isEmpty() && redTextView.text.toString().toFloat() in 0.00..1.00)
                {
                    this.savedRValue = (redTextView.text.toString().toFloat() * 255).toInt()
                    redColorValue = this.savedRValue
                    this.redSeekBar.progress = this.savedRValue
                    setRGBColor()
                }
                else
                {
                    this.redTextView.text = String.format("%.2f", (this.savedRValue / 255f))
                }
            }
            false
        }

        this.greenTextView.setOnKeyListener { view, i, keyEvent ->
            if(i == KeyEvent.KEYCODE_ENTER)
            {
                if(!greenTextView.text.isEmpty() && greenTextView.text.toString().toFloat() in 0.00..1.00)
                {
                    this.savedGValue = (greenTextView.text.toString().toFloat() * 255).toInt()
                    greenColorValue = this.savedGValue
                    this.greenSeekBar.progress = this.savedGValue
                    setRGBColor()
                }
                else
                {
                    this.greenTextView.text = String.format("%.2f", (this.savedGValue / 255f))
                }
            }
            false
        }

        this.blueTextView.setOnKeyListener { view, i, keyEvent ->
            if(i == KeyEvent.KEYCODE_ENTER)
            {
                if(!blueTextView.text.isEmpty() && blueTextView.text.toString().toFloat() in 0.00..1.00)
                {
                    this.savedBValue = (blueTextView.text.toString().toFloat() * 255).toInt()
                    blueColorValue = this.savedBValue
                    this.blueSeekBar.progress = this.savedBValue
                    setRGBColor()
                }
                else
                {
                    this.blueTextView.text = String.format("%.2f", (this.savedBValue / 255f))
                }
            }
            false
        }
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
