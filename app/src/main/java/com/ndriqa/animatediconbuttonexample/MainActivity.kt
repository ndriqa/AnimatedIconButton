package com.ndriqa.animatediconbuttonexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ndriqa.animatediconbutton.listeners.OnDrawableChangeListener
import com.ndriqa.animatediconbuttonexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpButtons()
        onClickEvents()
    }

    private fun setUpButtons() {
        setUpLikeButton()
        setUpVideoQualityButton()
        setUpBluetoothButton()
        setUpColorButton()
    }

    private fun setUpColorButton() {
        binding.colorButton.setResources(
            R.drawable.color_red,
            R.drawable.color_green,
            R.drawable.color_blue,
            R.drawable.color_yellow,
            R.drawable.color_cyan,
            R.drawable.color_purple,
        )

        initColorButtons()
    }

    /** Using this method, other buttons(events) toggle the animated button to go straight to the
     * desired drawable, if it contains the drawable */
    private fun initColorButtons() {
        with(binding) {
            redButton.setOnClickListener { colorButton.toggle(R.drawable.color_red) }
            greenButton.setOnClickListener { colorButton.toggle(R.drawable.color_green) }
            blueButton.setOnClickListener { colorButton.toggle(R.drawable.color_blue) }
            yellowButton.setOnClickListener { colorButton.toggle(R.drawable.color_yellow) }
            cyanButton.setOnClickListener { colorButton.toggle(R.drawable.color_cyan) }
            purpleButton.setOnClickListener { colorButton.toggle(R.drawable.color_purple) }
        }
    }

    private fun setUpBluetoothButton() {
        binding.bluetoothButton.setResources(
            R.drawable.ic_bluetooth_disabled,
            R.drawable.ic_bluetooth_enabled,
            R.drawable.ic_bluetooth_connected,
        )

        binding.bluetoothButton.setOnDrawableChangeListener(object : OnDrawableChangeListener {
            override fun onDrawableChange(drawableResId: Int) {
                binding.bluetoothStateTextView.text = when(drawableResId) {
                    R.drawable.ic_bluetooth_disabled -> "off"
                    R.drawable.ic_bluetooth_enabled -> "on"
                    R.drawable.ic_bluetooth_connected -> "connected"
                    else -> "unknown"
                }
            }
        })
    }

    private fun setUpVideoQualityButton() {
        val videoQualityResources = listOf(
            R.drawable.ic_quality_hq,
            R.drawable.ic_quality_hd,
            R.drawable.ic_quality_2k,
            R.drawable.ic_quality_4k,
            R.drawable.ic_quality_8k,
        )
        binding.videoQualityButton.setResources(videoQualityResources)
    }

    private fun setUpLikeButton() {
        binding.likeButton.setResources(
            R.drawable.ic_heart_default,
            R.drawable.ic_heart_filled
        )
    }

    private fun onClickEvents() {
        binding.likeButton.setOnClickListener {
            binding.likeButton.toggle()
        }

        binding.videoQualityButton.setOnClickListener {
            binding.videoQualityButton.toggle()
        }

        binding.bluetoothButton.setOnClickListener {
            binding.bluetoothButton.toggle()
        }
    }
}