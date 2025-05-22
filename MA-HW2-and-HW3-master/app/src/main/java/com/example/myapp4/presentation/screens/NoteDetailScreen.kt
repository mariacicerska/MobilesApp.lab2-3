package com.example.myapp4.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp4.data.formater.TimeFormater
import com.example.myapp4.presentation.LocalNavController
import com.example.myapp4.presentation.viewModels.MainActivityViewModel

@Composable
fun NoteDetailScreen(
    noteId: Int,
    viewModel: MainActivityViewModel = viewModel()
) {
    val notes = viewModel.notes.collectAsState()
    val note = notes.value.find { it.id == noteId }
    val navController = LocalNavController.current

    if (noteId == 0) {
        Text(text = "Invalid note ID")
        return
    }

    if (note != null) {
        val timeFormater = TimeFormater()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Note Details")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Title: ${note.title}")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Text: ${note.text}")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Date added: ${timeFormater.formatToDate(note.timeStamp)}")
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {
                val route = "add_note/${note.id}"
                navController.navigate(route)
            }) {
                Text("Edit")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                navController.navigateUp()
            }) {
                Text("Back")
            }
        }
    } else {
        Text(text = "Note not found with ID: $noteId")
    }
}
