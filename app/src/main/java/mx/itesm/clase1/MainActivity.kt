package mx.itesm.clase1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {


    private lateinit var textoEditable : EditText

    val lanzador = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        Toast.makeText(this, "REGRESANDO DE ACTIVIDAD", Toast.LENGTH_SHORT).show()

        // verificar código y actuar conforme a lo recibido
        if(result.resultCode == Activity.RESULT_OK){

            val datos = result.data

            // kotlin - safe call (llamada segura)
            // anula una línea si un objeto de ella es nulo
            /*
            if(datos != null) {
                Toast.makeText(this, datos.getStringExtra("resultadoNombre"), Toast.LENGTH_SHORT)
                    .show()
            }*/

            Toast.makeText(this, datos?.getStringExtra("resultadoNombre"), Toast.LENGTH_SHORT)
                .show()

            Toast.makeText(this, "${datos?.getIntExtra("hora", -1)}", Toast.LENGTH_SHORT)
                .show()

            // ?: - elvis operator (lo checamos después)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // como acceder a elemento de GUI
        // kotlin - var vs val

        // se puede declarar explícitamente el tipo de una variable
        // var textito : TextView = findViewById<TextView>(R.id.textView)

        // se puede INFERIR el tipo de una variable
        // var textito = findViewById<TextView>(R.id.textView)

        // se puede declarar var - mutable
        // var textito = findViewById<TextView>(R.id.textView)

        // se puede declarar val - inmutable
        val textito = findViewById<TextView>(R.id.textView)

        // prueba de mutabilidad
        // textito = findViewById<TextView>(R.id.textView)

        textoEditable = findViewById(R.id.editTextTextPersonName)
        val botoncito = findViewById<Button>(R.id.button)

        textito.text = "HOLA, SOY CÓDIGO"
        Log.wtf("MAIN", textoEditable.text.toString())
        botoncito.text = "PRUEBITA"

        botoncito.setOnClickListener {

            (it as Button).text = "AH OK"
            Toast.makeText(this, "HOLA DESDE BOTON 1", Toast.LENGTH_LONG).show()
        }

        // aquí vamos a hacer otra manera de agregar lógica a un widget

        // IMPORTANTE: en cualquier caso los tipos en Kotlin son RÍGIDOS
    }

    // manera de captar input |de widget
    // Declarar funcion que reciba un view como argumento
    fun boton2(view: View?){

        // todos los tipos pueden ser
        // non-nullable
        // nullable
        (view as Button).text = "PRESIONASTE!"
        Toast.makeText(this, "PRESIONASTE BOTON", Toast.LENGTH_SHORT).show()
    }

    fun cambiarActividad(view: View?){

        // el que cambia la actividad es el sistema operativo
        // nosotros le pedimos atentamente, no le ordenamos

        // creamos una solicitud que se llama intent
        // 2 maneras de abrir una actividad
        // 1 - con tipo explícito
        // 2 - con una acción
        val intent = Intent(this, SecondActivity::class.java)

        // como mandar datos hacia la nueva actividad
        intent.putExtra("nombre", textoEditable.text.toString())
        intent.putExtra("edad", 20.3f)
        intent.putExtra("calificacion", 40)

        //startActivity(intent)
        lanzador.launch(intent)
    }
}