package com.example.composenoteapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composenoteapp.R
import com.example.composenoteapp.component.NoteButton
import com.example.composenoteapp.component.NoteInputText
import com.example.composenoteapp.data.NotesDataSource
import com.example.composenoteapp.model.Note
import com.example.composenoteapp.ui.theme.NoteBackGroundColor
import com.example.composenoteapp.ui.theme.StringConstants
import com.example.composenoteapp.ui.theme.StringConstants.NOTE_ENTRY_DATE_FORMAT
import com.example.composenoteapp.ui.theme.TopAppBarColor
import java.time.format.DateTimeFormatter

@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {


    val title = remember {
        mutableStateOf(StringConstants.NOTE_SCREEN_EMPTY_STRING)
    }

    val description = remember {
        mutableStateOf(StringConstants.NOTE_SCREEN_EMPTY_STRING)
    }

    Column(modifier = Modifier.padding(6.dp)) {
        // Top Bar
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
        }, backgroundColor = TopAppBarColor)

        // Column
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(text = title.value,
                label = StringConstants.NOTE_SCREEN_TEXT_FIELD_TITLE,
                onTextChange = {
                    changeNoteContent(it, title)
                })
            NoteInputText(text = description.value,
                label = StringConstants.NOTE_SCREEN_TEXT_FIELD_DESCRIPTION, onTextChange = {
                    changeNoteContent(it, description)
                })
            Spacer(modifier = Modifier.padding(8.dp))
            // Button
            NoteButton(text = StringConstants.NOTE_SCREEN_BUTTON_TITLE, onClick = {
                if (title.value.isNotEmpty() && description.value.isNotEmpty()) {
                    title.value = ""
                    description.value = ""
                }
            })

        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note, onNoteClicked = {})
            }
        }


    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit) {

    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = NoteBackGroundColor,
        elevation = 6.dp
    ) {
        Column(modifier = Modifier
            .clickable { }
            .padding(horizontal = 14.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.Start) {
            Text(text = note.title,
            style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text = note.entryDate.format(DateTimeFormatter.ofPattern(NOTE_ENTRY_DATE_FORMAT)),
            style = MaterialTheme.typography.caption)

        }

    }
}

/**
 * Checks if the character typed is a letter or white space.
 * Displays it in the text field if so.
 * @param it the string we wish to display in text field
 * @param state the state of the text field
 */
private fun changeNoteContent(it: String, state: MutableState<String>) {
    if (it.all { char ->
            char.isLetter() || char.isWhitespace()
        }) {
        state.value = it
    } else {
        state.value = state.value
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun NotesPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}