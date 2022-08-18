package mx.itesm.clase1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


// esta clase la vamos a usar para interactuar con el API de
// SQLite que vive en el SO

class DBHelper(context : Context) : SQLiteOpenHelper(context, DB_FILE, null, 1) {

    companion object {

        private const val DB_FILE = "alumnos.db"
        private const val TABLE = "alumnitos"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "nombre"
        private const val COLUMN_AGE = "edad"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        // lógica que se ejecuta en caso de necesitar
        // crear la BD
        val query = "CREATE TABLE $TABLE(" +
                "$COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_AGE INTEGER)"

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, vAnterior: Int, vActual: Int) {

        // cambio la versión de la db

        // transferencia de datos de una db a otra

        // lo más básico - borra la anterior y pon la nueva

        // prepared statement - query parametrizada
        val query = "DROP TABLE IF EXISTS ?"
        val args = arrayOf(TABLE)

        db?.execSQL(query, args)
        onCreate(db)
    }

    // guardar datos nuevos

    fun save(nombre: String, edad: Int){

        // necesitamos el objeto contentvalues para nuestros argumentos
        val valores = ContentValues()
        valores.put(COLUMN_NAME, nombre)
        valores.put(COLUMN_AGE, edad)

        // vamos a utilizar un método de conveniencia para guardar
        // insert
        // 2 maneras de acceder la db que estamos manipulando
        writableDatabase.insert(TABLE, null, valores)
    }
    // borrar
    fun delete(nombre : String) : Int {

        // clausula en un query se usa para limitar el alcance
        // de la acción que queremos
        val clause = "$COLUMN_NAME = ?"
        val args = arrayOf(nombre)

        return writableDatabase.delete(TABLE, clause, args)
    }

    // buscar datos
    fun find(nombre : String) : Int {

        val clause = "$COLUMN_NAME = ?"
        val args = arrayOf(nombre)

        // readable o writable?
        val cursor = readableDatabase.query(TABLE, null, clause, args, null, null, null)

        var result = -1

        // movemos el cursor al primer renglon del conjunto respuesta
        if(cursor.moveToFirst()){

            result = cursor.getInt(0)
        }

        // si quieres recorrer todos los datos
        while(cursor.moveToNext()){
            // lógica para cada renglón
        }

        return result
    }
}