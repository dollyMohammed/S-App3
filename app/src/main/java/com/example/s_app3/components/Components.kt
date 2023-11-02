package com.example.s_app3.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier=Modifier,
    valueState: MutableState<String>,
    labelId:String,
    enabled:Boolean,
    imeAction:ImeAction= ImeAction.Next,
    keyboardType: KeyboardType=KeyboardType.Number,
    isSingleLine:Boolean,
    onAction:KeyboardActions=KeyboardActions.Default


    ){
    OutlinedTextField(value = valueState.value,
        onValueChange = {valueState.value=it},
       // value = valueState.value,
        //onValueChange = { valueState.value },
        modifier = Modifier.padding(bottom = 10.dp, start = 10.dp,end=10.dp).fillMaxWidth(),
        label={ Text(text = labelId)},

        singleLine = isSingleLine,
        enabled = enabled,
        leadingIcon = { Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = "Money Icon") },

        textStyle = TextStyle(
            fontSize = 18.sp,
           color = MaterialTheme.colorScheme.onBackground
        ),
        keyboardActions = onAction,
       // KeyboardOptions = KeyboardOptions(keyboardType =keyboardType, imeAction = imeAction)


        )


}