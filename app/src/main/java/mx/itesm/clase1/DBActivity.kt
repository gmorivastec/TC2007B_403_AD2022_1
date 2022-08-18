package mx.itesm.clase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.EditTextPreference
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class DBActivity : AppCompatActivity() {

    private lateinit var db : DBHelper
    private lateinit var nombre : EditText
    private lateinit var edad : EditText
    private lateinit var id : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)

        nombre = findViewById(R.id.activityDbNombre)
        edad = findViewById(R.id.activityDbEdad)
        id = findViewById(R.id.activityDbId)
        db = DBHelper(this)
    }

    fun guardarDatos(view : View?) {

        db.save(nombre.text.toString(), edad.text.toString().toInt())
        Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show()
    }

    fun borrarDatos(view : View?) {

        val rows = db.delete(nombre.text.toString())
        Toast.makeText(this, "$rows rows afectadas", Toast.LENGTH_SHORT).show()
    }


    fun buscarDatos(view : View?) {

        val idEncontrada = db.find(nombre.text.toString())
        id.text = "$idEncontrada"
        Toast.makeText(this, "BUSCANDO DATOS...", Toast.LENGTH_SHORT).show()
    }
}