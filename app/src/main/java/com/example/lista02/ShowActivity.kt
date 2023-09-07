package com.example.lista02

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class ShowActivity : MainActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.dark_mode -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            true
        }
        R.id.light_Mode->{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            true
        }
        R.id.Include -> {
            startActivity(Intent(this, IncludeActivity::class.java))
            true
        }
        R.id.Show->{
            startActivity(Intent(this,ShowActivity::class.java))
            true
        }
        R.id.Deletar -> {
            startActivity(Intent(this, DeleteActivity::class.java))
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val menuItemToHide = menu.findItem(R.id.Show)
        menuItemToHide?.isVisible = false

        return super.onPrepareOptionsMenu(menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        setSupportActionBar(findViewById(R.id.toolbar))
        val sharedPreference = SharedPreference(this) // Substitua 'Auxiliar' pelo nome correto da sua classe

        val text = sharedPreference.recuperarAnotacoes()
        val linearLayout = findViewById<LinearLayout>(R.id.linearlayout)

        for (text in text) {
            val textView = TextView(this)
            textView.text = text
            textView.textSize = 26f // Defina o tamanho do texto conforme necessário
            textView.setPadding(16,16,16,16)
            textView.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
            textView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, // Largura
                ViewGroup.LayoutParams.WRAP_CONTENT  // Altura
            )


            // Adicione o textView ao LinearLayout
            linearLayout.addView(textView)
        }
    }
}