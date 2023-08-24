package com.axel.githubbrowser.ui.styles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryCard(
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {},
  content: @Composable () -> Unit,
) {
  Card(
    modifier = modifier,
    onClick = onClick,
    colors = AxelCardDefaults.primaryCardColors(),
    shape = RoundedCornerShape(smallUnit)
  ) {
    Column(modifier = Modifier.padding(mediumUnit)) {
      content()
    }
  }
}

private object AxelCardDefaults{
  @Composable
  fun primaryCardColors(
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
  ) = CardDefaults.cardColors(
    containerColor = containerColor,
    contentColor = contentColor,
  )
}