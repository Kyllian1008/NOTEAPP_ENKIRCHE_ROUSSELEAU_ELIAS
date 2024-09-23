package fr.esilv.noteappguettab_combaret


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository
    val allNotes = NoteDatabase.getDatabase(application).noteDao().getAllNotes()
//** Initialisation du repository */
    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
    }
// ** Fonction qui permet d'insérer une note */
    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }
// ** Fonction qui permet de mettre à jour une note */
    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }
// ** Fonction qui permet de supprimer une note */
    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }
}
