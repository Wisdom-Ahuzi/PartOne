package com.example.partone.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SoundButton(
    text:String,
    onClick:() -> Unit
){
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ),
    shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold))
    }
}

@Composable
fun SoundTextButton(
    text:String,
    onClick:() -> Unit
){
  TextButton(onClick = onClick) {
      Text(
          text = text,
          style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold,
          color = Color.LightGray
          ))

  }
}

@Preview
@Composable
fun ButtonPreview(){
    SoundButton("Button") { println("true") }
}