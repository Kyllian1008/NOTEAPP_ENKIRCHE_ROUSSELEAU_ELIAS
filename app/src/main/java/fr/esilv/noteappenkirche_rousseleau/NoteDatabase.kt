package fr.esilv.noteappenkirche_rousseleau


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.esilv.noteappenkirche_rousseleau.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    /** Volative : permet de rendre la variable INSTANCE accessible par tous les threads */
    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null
        //** Fonction qui permet de retourner une instance de la base de données */
        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
