package com.example.parcialsm100223

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)

        val layoutPerfil = findViewById<LinearLayout>(R.id.layoutPerfil)
        ViewCompat.setOnApplyWindowInsetsListener(layoutPerfil) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recibir datos del Intent
        val nombre = intent.getStringExtra("nombre") ?: ""
        val edad = intent.getIntExtra("edad", 0)
        val genero = intent.getStringExtra("genero") ?: ""
        val categoria = intent.getStringExtra("categoria") ?: ""

        // Mostrar datos en los TextViews
        findViewById<TextView>(R.id.tvNombre).text = nombre
        findViewById<TextView>(R.id.tvEdad).text = edad.toString()
        findViewById<TextView>(R.id.tvGenero).text = genero
        findViewById<TextView>(R.id.tvCategoria).text = categoria

        // Mensaje personalizado
        findViewById<TextView>(R.id.tvMensaje).text =
            "Hola $nombre, eres un $categoria de género $genero."

        // Cambiar color de fondo según categoría
        val colorRes = when (categoria) {
            getString(R.string.cat_nino) -> R.color.color_nino
            getString(R.string.cat_adolescente) -> R.color.color_adolescente
            getString(R.string.cat_adulto) -> R.color.color_adulto
            getString(R.string.cat_adulto_mayor) -> R.color.color_adulto_mayor
            else -> R.color.white
        }
        layoutPerfil.setBackgroundColor(ContextCompat.getColor(this, colorRes))

        // Botón Editar Perfil -> regresar con finish()
        findViewById<Button>(R.id.btnEditarPerfil).setOnClickListener {
            finish()
        }
    }
}
