package com.example.quill

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quill.database.NoteDatabase
import com.example.quill.repository.NoteRepository
import com.example.quill.viewmodel.NoteViewModel
import com.example.quill.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val infoIcon = findViewById<ImageView>(R.id.infoIcon)
        infoIcon.setOnClickListener {
            showInstructionsDialog()
        }

        setupViewModel()
    }

    private fun setupViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))
        val viewModelFactoryProvider = NoteViewModelFactory(application, noteRepository)
        noteViewModel = ViewModelProvider(this, viewModelFactoryProvider)[NoteViewModel::class.java]
    }

    private fun showInstructionsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Instructions")
        builder.setMessage("\n1. Open the Quill app to view your notes." +
                "\n2. To create a new note, tap on the '+' icon button." +
                "\n3. Enter your note's title and description in the respective fields." +
                "\n4. Save your note by tapping the 'Save' button at the top right corner." +
                "\n5. Your note will be saved and displayed in the list of notes on the home screen." +
                "\n6. To edit a note, tap on the note you wish to edit from the list. This will open the note in an edit view where you can make changes." +
                "\n7. Save your changes by tapping the 'Save' button again." +
                "\n8. To delete a note, open the note you wish to delete and tap on the 'Delete' icon button." +
                "\n9. You can search for notes by tapping the search icon and entering keywords related to your notes.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}