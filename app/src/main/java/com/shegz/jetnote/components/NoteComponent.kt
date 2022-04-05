package com.shegz.jetnote.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier.padding(
        top = 9.dp,
        bottom = 8.dp
    ),
    text: String,
    label: String,
    maxLines: Int = 1,
    onTextChange: (String) -> Unit,
    imeAction: ImeAction,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current


    TextField(
        value = text, onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
        maxLines = maxLines,
        label = {
            Text(text = label)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            onImeAction()

        },
            onNext = {
                localFocusManager.moveFocus(FocusDirection.Down)
            }),
        modifier = modifier
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = text)

    }

}