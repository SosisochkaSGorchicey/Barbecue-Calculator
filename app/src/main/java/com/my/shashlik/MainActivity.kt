package com.my.shashlik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.example.shashlik.databinding.ActivityMainBinding
import kotlin.math.roundToInt



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sashlickResult: Double
        var meatType = 0.0
        var time = 0.0
        var timeClicked = 0
        var hunger = 0.0

        val animals = arrayOf(binding.chicken, binding.pig, binding.sheep, binding.cow)
        val animalsBackground = arrayOf(binding.chickenBackground, binding.pigBackground, binding.sheepBackground, binding.cowBackground)


        val dr0 = AppCompatResources.getDrawable(this, com.example.shashlik.R.drawable.face_f1)
        binding.sbHunger.thumb = dr0

        //   ***** Meat type *****
        binding.cow.setOnClickListener {
            for (x in 0..animals.size-1) {
                if (animals[x]==binding.cow) {
                    animals[x].alpha = 1F
                    animalsBackground[x].visibility = View.VISIBLE
                } else {
                    animals[x].alpha = 0.5F
                    animalsBackground[x].visibility = View.INVISIBLE
                }
            }

            meatType = 0.3
        }

        binding.chicken.setOnClickListener {
            for (x in 0..animals.size-1) {
                if (animals[x]==binding.chicken) {
                    animals[x].alpha = 1F
                    animalsBackground[x].visibility = View.VISIBLE
                } else {
                    animals[x].alpha = 0.5F
                    animalsBackground[x].visibility = View.INVISIBLE
                }
            }

            meatType = 0.35
        }

        binding.pig.setOnClickListener {
            for (x in 0..animals.size-1) {
                if (animals[x]==binding.pig) {
                    animals[x].alpha = 1F
                    animalsBackground[x].visibility = View.VISIBLE
                } else {
                    animals[x].alpha = 0.5F
                    animalsBackground[x].visibility = View.INVISIBLE
                }
            }

            meatType = 0.3
        }

        binding.sheep.setOnClickListener {
            for (x in 0..animals.size-1) {
                if (animals[x]==binding.sheep) {
                    animals[x].alpha = 1F
                    animalsBackground[x].visibility = View.VISIBLE
                } else {
                    animals[x].alpha = 0.5F
                    animalsBackground[x].visibility = View.INVISIBLE
                }
            }

            meatType = 0.3
        }


        //   ***** Time *****
        binding.cbLittle.setOnClickListener {
            binding.cbMiddle.isChecked = false
            binding.cbLarge.isChecked = false

            time = 0.0
            timeClicked = 1
        }

        binding.cbMiddle.setOnClickListener {
            binding.cbLittle.isChecked = false
            binding.cbLarge.isChecked = false

            time = 0.1
            timeClicked = 1
        }

        binding.cbLarge.setOnClickListener {
            binding.cbMiddle.isChecked = false
            binding.cbLittle.isChecked = false

            time = 0.2
            timeClicked = 1
        }

        //   ***** Hunger *****
        binding.sbHunger.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (i in 0..24) {
                    val dr1 = AppCompatResources.getDrawable(this@MainActivity,com.example.shashlik.R.drawable.face_f1)
                    binding.sbHunger.thumb = dr1

                } else if (i in 25..49) {
                    val dr2 = AppCompatResources.getDrawable(this@MainActivity,com.example.shashlik.R.drawable.face_f2)
                    binding.sbHunger.thumb = dr2

                } else if (i in 50..74) {
                    val dr3 = AppCompatResources.getDrawable(this@MainActivity,com.example.shashlik.R.drawable.face_f3)
                    binding.sbHunger.thumb = dr3

                } else if (i in 75..100) {
                    val dr4 = AppCompatResources.getDrawable(this@MainActivity,com.example.shashlik.R.drawable.face_f4)
                    binding.sbHunger.thumb = dr4

                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        //   ***** Count result *****
        binding.bDone.setOnClickListener {

            // TOAST - Not all info
            if (meatType==0.0 || binding.etPeople.text.toString()=="" || timeClicked == 0) {
                Toast.makeText(this, getString(com.example.shashlik.R.string.toast), Toast.LENGTH_SHORT).show();
            }

            // TOAST - Too little or too much people
            else if (binding.etPeople.text.toString().toInt() !in 1..50) {
                Toast.makeText(this, getString(com.example.shashlik.R.string.toast_people), Toast.LENGTH_SHORT).show();
            }

            // Counting result
            else {

                // Hunger seekbar
                if (binding.sbHunger.progress.toDouble() in 25.0..50.0) {
                    hunger = 0.1
                } else if (binding.sbHunger.progress.toDouble() in 51.0..70.0) {
                    hunger = 0.15
                } else if (binding.sbHunger.progress.toDouble() > 70.0) {
                    hunger = 0.2
                }

                sashlickResult = binding.etPeople.text.toString().toDouble() * (meatType + hunger + time)
                sashlickResult = (sashlickResult * 100).roundToInt() / 100.0

                binding.tvResult.text = getString(com.example.shashlik.R.string.result_calculated, sashlickResult.toString())


            }

        }
    }
}