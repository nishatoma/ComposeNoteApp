package com.example.composenoteapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composenoteapp.R
import com.example.composenoteapp.component.NoteButton
import com.example.composenoteapp.component.NoteInputText
import com.example.composenoteapp.ui.theme.NoteScreeButtonTitle
import com.example.composenoteapp.ui.theme.NoteScreenTextFieldDescription
import com.example.composenoteapp.ui.theme.NoteScreenTextFieldTitle
import com.example.composenoteapp.ui.theme.TopAppBarColor

@ExperimentalComposeUiApi
@Composable
fun NoteScreen() {

    var title = remember {
        mutableStateOf("")
    }

    var description = remember {
        mutableStateOf("")
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
                label = NoteScreenTextFieldTitle,
                onTextChange = {
//                    if (it.all { char ->
//                            char.isLetter() || char.isWhitespace()
//                        }) title.value = it
                    changeNoteContent(it, title)
                })
            NoteInputText(text = description.value,
                label = NoteScreenTextFieldDescription, onTextChange = {
                    changeNoteContent(it, description)
                })
            Spacer(modifier = Modifier.padding(8.dp))
            // Button
            NoteButton(text = NoteScreeButtonTitle, onClick = {
                // TODO: Implement Add NOTE functionality
            })

        }


    }
}

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
    NoteScreen()
}