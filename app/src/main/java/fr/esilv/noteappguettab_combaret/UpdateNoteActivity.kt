package fr.esilv.noteappguettab_combaret

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import fr.esilv.noteappguettab_combaret.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private val noteViewModel: NoteViewModel by viewModels()
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Récupération de l'ID de la note à partir de l'intent
        noteId = intent.getIntExtra("noteId", -1)
        if (noteId == -1) {
            finish()
            return
        }

        // Observer les notes et charger la note à modifier lorsque les données sont prêtes
        noteViewModel.allNotes.observe(this, Observer { notes ->
            val note = notes.find { it.id == noteId }
            if (note != null) {
                binding.updateTitleEditText.setText(note.title)
                binding.updateContentEditText.setText(note.content)
            }
        })

        // Sauvegarder les modifications
        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString().trim()
            val newContent = binding.updateContentEditText.text.toString().trim()

            if (newTitle.isEmpty() || newContent.isEmpty()) {
                Toast.makeText(this, "Les champs ne peuvent pas être vides", Toast.LENGTH_SHORT).show()
            } else {
                val updatedNote = noteViewModel.allNotes.value?.find { it.id == noteId }?.copy(
                    title = newTitle,
                    content = newContent
                )
                if (updatedNote != null) {
                    noteViewModel.update(updatedNote)
                    Toast.makeText(this, "Note mise à jour", Toast.LENGTH_SHORT).show()
                    finish() // Fermer l'activité après la mise à jour
                }
            }
        }
    }
}
