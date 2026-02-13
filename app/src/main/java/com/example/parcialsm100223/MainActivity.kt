package com.example.parcialsm100223

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: TextInputEditText
    private lateinit var etEdad: TextInputEditText
    private lateinit var spinnerGenero: Spinner
    private lateinit var cbTerminos: MaterialCheckBox
    private lateinit var switchRecordar: MaterialSwitch
    private lateinit var btnRegistrar: MaterialButton
    private lateinit var btnVerPerfil: MaterialButton
    private lateinit var prefs: SharedPreferences
    private var isFirstResume = true

    companion object {
        const val PREFS_NAME = "perfil_prefs"
        const val KEY_NOMBRE = "nombre"
        const val KEY_EDAD = "edad"
        const val KEY_GENERO = "genero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        spinnerGenero = findViewById(R.id.spinnerGenero)
        cbTerminos = findViewById(R.id.cbTerminos)
        switchRecordar = findViewById(R.id.switchRecordar)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnVerPerfil = findViewById(R.id.btnVerPerfil)

        btnRegistrar.setOnClickListener { registrar() }
        btnVerPerfil.setOnClickListener { cargarPerfilGuardado() }
    }

    private fun registrar() {
        var hayErrores = false

        val nombre = etNombre.text.toString().trim()
        val edadStr = etEdad.text.toString().trim()

        if (nombre.isEmpty()) {
            etNombre.setError(getString(R.string.error_nombre_vacio))
            hayErrores = true
        }

        if (edadStr.isEmpty()) {
            etEdad.setError(getString(R.string.error_edad_vacia))
            hayErrores = true
        } else {
            val edad = edadStr.toIntOrNull()
            if (edad == null || edad < 1 || edad > 120) {
                etEdad.setError(getString(R.string.error_edad_invalida))
                hayErrores = true
            }
        }

        if (!cbTerminos.isChecked) {
            cbTerminos.setError(getString(R.string.error_terminos))
            hayErrores = true
        } else {
            cbTerminos.error = null
        }

        if (hayErrores) return

        val edad = edadStr.toInt()
        val genero = spinnerGenero.selectedItem.toString()
        val categoria = determinarCategoria(edad)

        if (switchRecordar.isChecked) {
            prefs.edit()
                .putString(KEY_NOMBRE, nombre)
                .putInt(KEY_EDAD, edad)
                .putString(KEY_GENERO, genero)
                .apply()
        }

        val intent = Intent(this, PerfilActivity::class.java).apply {
            putExtra("nombre", nombre)
            putExtra("edad", edad)
            putExtra("genero", genero)
            putExtra("categoria", categoria)
        }
        startActivity(intent)
    }

    private fun cargarPerfilGuardado() {
        val nombre = prefs.getString(KEY_NOMBRE, null)

        if (nombre == null) {
            Toast.makeText(this, getString(R.string.toast_no_datos), Toast.LENGTH_SHORT).show()
            return
        }

        val edad = prefs.getInt(KEY_EDAD, 0)
        val genero = prefs.getString(KEY_GENERO, "") ?: ""

        etNombre.setText(nombre)
        etEdad.setText(edad.toString())

        val generoArray = resources.getStringArray(R.array.genero_array)
        val index = generoArray.indexOf(genero)
        if (index >= 0) {
            spinnerGenero.setSelection(index)
        }

        val categoria = determinarCategoria(edad)
        val intent = Intent(this, PerfilActivity::class.java).apply {
            putExtra("nombre", nombre)
            putExtra("edad", edad)
            putExtra("genero", genero)
            putExtra("categoria", categoria)
        }
        startActivity(intent)
    }

    private fun determinarCategoria(edad: Int): String {
        return when {
            edad in 0..12 -> getString(R.string.cat_nino)
            edad in 13..17 -> getString(R.string.cat_adolescente)
            edad in 18..59 -> getString(R.string.cat_adulto)
            else -> getString(R.string.cat_adulto_mayor)
        }
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, getString(R.string.toast_en_segundo_plano), Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        if (!isFirstResume) {
            Toast.makeText(this, getString(R.string.toast_bienvenido), Toast.LENGTH_SHORT).show()
        }
        isFirstResume = false
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_salir_titulo))
            .setMessage(getString(R.string.dialog_salir_mensaje))
            .setPositiveButton(getString(R.string.dialog_si)) { _, _ ->
                super.onBackPressed()
            }
            .setNegativeButton(getString(R.string.dialog_no), null)
            .show()
    }
}
