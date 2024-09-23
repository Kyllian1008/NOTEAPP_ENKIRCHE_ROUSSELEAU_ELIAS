package fr.esilv.noteappguettab_combaret

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    /** definition des methodes pour la DAO */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}
