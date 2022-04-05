package com.shegz.jetnote.screen

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shegz.jetnote.R
import com.shegz.jetnote.components.NoteButton
import com.shegz.jetnote.components.NoteInputText
import com.shegz.jetnote.data.NoteDataSource
import com.shegz.jetnote.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(6.dp)
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Action Icon"
                )
            },

            backgroundColor = Color.LightGray
        )

        //Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                },
                imeAction = ImeAction.Next
            )
            NoteInputText(
                text = description, label = "Add a note", onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it

                }, imeAction = ImeAction.Done, maxLines = 3
            )
            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    
                }

            })

            Divider(modifier = Modifier.padding(10.dp))
            LazyColumn{
                items(notes){note->
                    NoteRow(note = note, onClicked = {

                    })
                }
            }

        }

    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onClicked: (Note) -> Unit
){
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = 33.dp,
                    bottomStart = 33.dp
                )
            )
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp
    ) {
        Column(
            modifier
                .clickable {

                }
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                ),
            horizontalAlignment = Alignment.Start
        ){
            Text(text = note.title,
            style = MaterialTheme.typography.subtitle2)
            
            Text(text = note.description,
            style = MaterialTheme.typography.subtitle1)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MM")),
                    style = MaterialTheme.typography.caption)
            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(
        notes = NoteDataSource.loadNotes(), onAddNote = {

        }, onRemoveNote = {}
    )
}