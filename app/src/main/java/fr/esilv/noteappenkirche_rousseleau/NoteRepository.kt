package fr.esilv.noteappenkirche_rousseleau


class NoteRepository(private val noteDao: NoteDao) {

    val allNotes = noteDao.getAllNotes()
//** Fonction qui permet d'insérer une note */
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }
//** Fonction qui permet de mettre à jour une note */
    suspend fun update(note: Note) {
        noteDao.update(note)
    }
//** Fonction qui permet de supprimer une note */
    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}
