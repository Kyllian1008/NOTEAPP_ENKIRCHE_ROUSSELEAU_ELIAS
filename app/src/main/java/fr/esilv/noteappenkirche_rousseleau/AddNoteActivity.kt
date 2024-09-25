package fr.esilv.noteappenkirche_rousseleau

/** impoter les packages */
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.activity.viewModels
import fr.esilv.noteappenkirche_rousseleau.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    /** déclarer les variables */
    private lateinit var binding: ActivityAddNoteBinding
    private val noteViewModel: NoteViewModel by viewModels()


/** Créer la vue */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //** Enregistrer la note **/
        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString().trim()
            val content = binding.contentEditText.text.toString().trim()

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Le titre et le contenu ne peuvent pas être vides", Toast.LENGTH_SHORT).show()
            } else {
                val note = Note(0, title, content)
                noteViewModel.insert(note)
                finish()
                Toast.makeText(this, "Note enregistrée", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
