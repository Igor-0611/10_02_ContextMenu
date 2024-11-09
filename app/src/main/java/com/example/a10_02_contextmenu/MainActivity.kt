package com.example.a10_02_contextmenu

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textET: EditText
    private lateinit var randomNumberBTN: Button
    private var isRandomButtonClick: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textET = findViewById(R.id.textET)
        registerForContextMenu(textET)

        randomNumberBTN = findViewById(R.id.randomNumberBTN)
        randomNumberBTN.setOnClickListener {
            val getRandom = (1..50).random()
            textET.setText(getRandom.toString())
            textET.setBackgroundColor(getColor(R.color.white))
            isRandomButtonClick = true
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_change_color -> {
                if (!isRandomButtonClick) {
                    val estimation = textET.text.toString()

                    when (estimation) {
                        "1" -> textET.setBackgroundColor(getColor(R.color.orange))
                        "2" -> textET.setBackgroundColor(getColor(R.color.yellow))
                        "3" -> textET.setBackgroundColor(getColor(R.color.green))
                        "4" -> textET.setBackgroundColor(getColor(R.color.blue))
                        "5" -> textET.setBackgroundColor(getColor(R.color.red))
                        else -> {
                            textET.setBackgroundColor(getColor(R.color.white))
                            Toast.makeText(
                                this,
                                getString(R.string.error_estimation_text), Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
                } else {
                    val randomNum = textET.text.toString().toInt()

                    when (randomNum) {
                        in 1..10 -> textET.setBackgroundColor(getColor(R.color.red))
                        in 11..20 -> textET.setBackgroundColor(getColor(R.color.orange))
                        in 11..30 -> textET.setBackgroundColor(getColor(R.color.yellow))
                        in 31..40 -> textET.setBackgroundColor(getColor(R.color.green))
                        in 41..50 -> textET.setBackgroundColor(getColor(R.color.blue))
                        else -> {
                            textET.setBackgroundColor(getColor(R.color.white))
                            Toast.makeText(
                                this,
                                getString(R.string.error_estimation_text), Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    Toast.makeText(
                        this,
                        getString(R.string.random_mode_text), Toast.LENGTH_SHORT
                    ).show()
                }
            }

            R.id.menu_exit_program -> {
                finish()
                Toast.makeText(this,"Выход из приложения",Toast.LENGTH_LONG).show()
            }

            else -> return super.onContextItemSelected(item)
        }

        return true
    }
}