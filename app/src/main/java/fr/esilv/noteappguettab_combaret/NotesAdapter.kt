package fr.esilv.noteappguettab_combaret

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import fr.esilv.noteappguettab_combaret.R

class NotesAdapter(private var notes: List<Note>, private val context: Context): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        // Bouton de mise à jour de la note
        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply {
                putExtra("noteId", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        // Bouton de suppression de la note
        holder.deleteButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Note supprimée", Toast.LENGTH_SHORT).show()
            // Il est recommandé d'utiliser une fonction dans ViewModel pour supprimer la note de la base de données
            (holder.itemView.context as MainActivity).noteViewModel.delete(note)
        }
    }

    // Méthode pour rafraîchir les données de la RecyclerView
    fun refreshDate(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}

