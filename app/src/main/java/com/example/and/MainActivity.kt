package com.example.and

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : ComponentActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val switch = findViewById<Switch>(R.id.swth);

        val imbutton = findViewById<ImageView>(R.id.clickbutton);

        val image = findViewById<ImageView>(R.id.image);
        val close = findViewById<TextView>(R.id.close);
        val tview = findViewById<ConstraintLayout>(R.id.tview);


        close.setOnClickListener{ _ ->
            tview.visibility = View.GONE
            imbutton.visibility = View.VISIBLE
        }

        imbutton?.setOnClickListener{ _ ->

            val see = getToggle(this)
            setimage(see, image, switch)

            tview.visibility = View.VISIBLE
            imbutton.visibility = View.GONE
        }

        switch.setOnCheckedChangeListener { _, isChecked ->
            setToggle(this , isChecked)
            setimage(isChecked, image, switch);
        }/**/
    }
}

private const val PREF_NAME = "app"
private const val KEY_BOOLEAN = "toggle"

fun setimage(bool: Boolean, image: ImageView, @SuppressLint("UseSwitchCompatOrMaterialCode") swth: Switch) {
    swth.isChecked = bool
    if(bool){
        image.setImageResource(R.drawable.seen);
    } else {
        image.setImageResource(R.drawable.eye);
    }
}

fun setToggle(context: Context, value: Boolean) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putBoolean(KEY_BOOLEAN, value)
    editor.apply()
}

fun getToggle(context: Context): Boolean {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean(KEY_BOOLEAN, false)
}