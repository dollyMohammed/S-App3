package com.example.s_app3.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


val IconButtonSizeModifier= Modifier.size(40.dp)

@Composable
fun RoundedIconButton(
    modifier: Modifier,
    imagevactor: ImageVector,
    onClick:() -> Unit,
    tint:Color= Color.Black.copy(alpha = 0.8f),
    backgroundColor: Color=MaterialTheme.colorScheme.onBackground,
    elevation: Dp =4.dp


    ){
    Card (modifier = Modifier
        .padding(3.dp)
        .clickable { onClick.invoke() }
        .then(IconButtonSizeModifier),
        shape = CircleShape,
        //backgroundColor=backgroundColor,
        elevation=CardDefaults.cardElevation()
        ){
        Icon(imageVector = imagevactor, contentDescription ="plus or minus Icon" )

    }

}