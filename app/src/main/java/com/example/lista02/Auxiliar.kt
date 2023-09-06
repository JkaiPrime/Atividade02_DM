package com.example.lista02
import android.content.Context
import android.content.SharedPreferences

class Auxiliar(private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AnotacoesPrefs", Context.MODE_PRIVATE)

    init {
        if (recuperarAnotacoes().isEmpty()) {
            val editor = sharedPreferences.edit()
            editor.putInt("contador", 0)
            editor.apply()
        }
    }

    fun adicionarAnotacao(texto: String) {
        val editor = sharedPreferences.edit()

        // Recupere o valor atual do contador
        val i = sharedPreferences.getInt("contador", 0)

        // Crie um ID único para a nova anotação
        val j = i + 1

        // Salve a anotação com o novo ID
        editor.putString("anotacao_$j", texto)
        editor.putInt("contador", j)

        // Salve as mudanças
        editor.apply()
    }
    fun recuperarAnotacoes(): List<String> {
        val anotacoes = mutableListOf<String>()
        val contador = sharedPreferences.getInt("contador", 0)

        // Percorra os IDs únicos das anotações e recupere seus valores
        for (i in 1..contador) {
            val anotacao = sharedPreferences.getString("anotacao_$i", null)
            if (anotacao != null) {
                anotacoes.add(anotacao)
            }
        }

        return anotacoes
    }
    fun removerAnotacao(id: Int) {
        val editor = sharedPreferences.edit()
        // Remova a anotação usando o ID
        editor.remove("anotacao_$id")

        // Salve as mudanças
        editor.apply()
    }
}