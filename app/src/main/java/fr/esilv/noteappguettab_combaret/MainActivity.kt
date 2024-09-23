package fr.esilv.noteappguettab_combaret


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import fr.esilv.noteappguettab_combaret.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter
    val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialisation de l'adaptateur
        notesAdapter = NotesAdapter(emptyList(), this)
        binding.notesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.notesRecyclerView.adapter = notesAdapter

        // Observer les données LiveData
        noteViewModel.allNotes.observe(this, Observer { notes ->
            notesAdapter.refreshDate(notes)
        })

        // Ajouter une nouvelle note
        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }
}
