package com.example.customlayoutdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.customlayoutdemo.ui.theme.CustomLayoutDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomLayoutDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

/**
 * Criando layouts personalizados que podem ser usados em Box, Columns e Rows
 */

@Composable
private fun MainScreen() {
    Box {
        CascadeLayout(spacing = 20) {
            Box(modifier = Modifier.size(60.dp).background(Color.Blue))
            Box(modifier = Modifier.size(80.dp, 40.dp).background(Color.Red))
            Box(modifier = Modifier.size(90.dp, 100.dp).background(Color.Cyan))
            Box(modifier = Modifier.size(50.dp).background(Color.Magenta))
            Box(modifier = Modifier.size(70.dp).background(Color.Green))
        }
    }
}

@Composable
private fun CascadeLayout(
    // definindo spacamento opcional
    spacing: Int = 0,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) {measurables, constraints ->
        var xCoord = 0
        layout(constraints.maxWidth, constraints.maxHeight) {
            var yCoord = 0
            val placeables = measurables.map {
                it.measure(constraints)
            }

            placeables.forEach {
                it.placeRelative(x = xCoord, y = yCoord)
                xCoord += it.width + spacing
                yCoord += it.height + spacing
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CustomLayoutDemoTheme {
        MainScreen()
    }
}