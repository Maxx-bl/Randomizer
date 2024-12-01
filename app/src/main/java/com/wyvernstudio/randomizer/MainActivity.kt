package com.wyvernstudio.randomizer

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.wyvernstudio.randomizer.databinding.ActivityMainBinding
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        val rollButton = findViewById<Button>(R.id.rollButton);
        val textView = findViewById<TextView>(R.id.resultText);
        val seekBar = findViewById<SeekBar>(R.id.seekBar);
        val textViewHowMany = findViewById<TextView>(R.id.howMany);

        seekBar.max = 100;

        fun updateMsg()
        {
           textViewHowMany.text = "Max selected: " + seekBar.progress.toString();
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateMsg();
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                textViewHowMany.setTypeface(null, Typeface.BOLD);
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                textViewHowMany.setTypeface(null, Typeface.NORMAL);
            }

        })

        rollButton.setOnClickListener{
            val rand = java.util.Random().nextInt(seekBar.progress+1);
            textView.text = rand.toString();
        }
    }
}