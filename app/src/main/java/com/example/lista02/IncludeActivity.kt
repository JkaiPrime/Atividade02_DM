package com.example.lista02

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.lista02.databinding.ActivityIncludeBinding

class IncludeActivity : MainActivity() {
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
        val menuItemToHide = menu.findItem(R.id.Include)
        menuItemToHide?.isVisible = false

        return super.onPrepareOptionsMenu(menu)
    }
    private lateinit var binding: ActivityIncludeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncludeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.btnIncluir.setOnClickListener {
            if (binding.edtAnota.text.isEmpty()){
                Toast.makeText(this, "Não pode incluir nada", Toast.LENGTH_SHORT).show()
            }else{
                val aux = SharedPreference(this)
                aux.adicionarAnotacao(binding.edtAnota.text.toString())
                binding.edtAnota.text.clear()
                Toast.makeText(this, "Incluido com sucesso", Toast.LENGTH_SHORT).show()
            }
        }
    }
}