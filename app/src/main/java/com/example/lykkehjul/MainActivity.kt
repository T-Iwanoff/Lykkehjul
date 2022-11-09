package com.example.lykkehjul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lykkehjul.ui.theme.LykkehjulTheme
import com.example.lykkehjul.ui.theme.PurpleBackground
import com.example.lykkehjul.ui.theme.Typography
import com.example.lykkehjul.model.WordSelector as WordSelector

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LykkehjulTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}

@Composable
fun GameScreen() {
    val selector = WordSelector()
    Column(Modifier
        .background(PurpleBackground)
        .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(top = 12.dp)
                .align(Alignment.CenterHorizontally),
            text = "Lykkehjulet",
            style = Typography.h2
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier.padding(top = 12.dp, start = 17.dp, bottom = 12.dp),
            text = "Din kategori er: "+selector.getCategory(),
            style = Typography.h5
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.padding(top = 12.dp, start = 17.dp, bottom = 12.dp),
            text = selector.getWord(),
            style = Typography.body1
        )
    }
}

@Composable
fun WordDisplay(word: String) {

}



@Composable
fun TempText() {
    val selector = WordSelector()
    var chars: String = ""
    for (i in selector.getWordArray()) {
        chars = "$chars$i+"
    }
    Text(text = "The chosen category and word is: "+selector.getCategory()+
            " and "+selector.getWord()+" or "+chars)
}

@Preview(showBackground = true)
@Composable
fun TextPreview() {
    LykkehjulTheme {
        TempText()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LykkehjulTheme {
        GameScreen()
    }
}