package mx.itesm.clase1

import android.database.sqlite.SQLiteOpenHelper


// esta clase la vamos a usar para interactuar con el API de
// SQLite que vive en el SO

//class DBHelper : SQLiteOpenHelper() {
class DBHelper {

    companion object {

        private const val DB_FILE = "alumnos.db"
        private const val TABLE = "alumnitos"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "nombre"
        private const val COLUMN_AGE = "edad"
    }
}