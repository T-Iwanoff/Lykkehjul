package com.example.lykkehjul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lykkehjul.model.Letter
import com.example.lykkehjul.model.WordHandler
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
    Column(
        Modifier
            .background(PurpleBackground)
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally),
            text = "Lykkehjulet",
            style = Typography.h2
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier
                .padding()
                .align(Alignment.CenterHorizontally),
            text = "Din kategori er: "+selector.getCategory(),
            style = Typography.h5
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier
                .padding()
                .align(Alignment.CenterHorizontally),
            text = selector.getWord(),
            style = Typography.body1
        )
        Spacer(modifier = Modifier.height(70.dp))
        WordDisplay(word = selector.getWord())

    }
}

@Composable
fun WordDisplay(word: String) {
    val handler = WordHandler(word)
    val wordArray = handler.getWord()
    val rows: Int = (wordArray.size + 9) / 10 //Number of rows with 10 letters on each row. Formula found here https://stackoverflow.com/questions/17944/how-to-round-up-the-result-of-integer-division
    var letterIndex: Int = 0
    Column(modifier = Modifier.fillMaxWidth()) {
        for (row in 1..rows) {
            Row(modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 1..10) {
                    LetterCard(letter = wordArray[letterIndex])
                    if (letterIndex != wordArray.lastIndex)
                        Spacer(modifier = Modifier.width(20.dp))
                    else break
                    letterIndex++
                }
            }
        }
    }


}

@Composable
fun LetterCard(letter: Letter) {
    if (letter.char == ' ') {
        //Spacer(modifier = Modifier.width(20.dp))
    }
    else {
        Text(
            text = if (letter.hidden) "_" else letter.char.toString(),
            fontSize = 30.sp
        )
    }
}

@Composable
fun LetterButton(letter: Char) {

}



@Composable
fun TempText() {
    val selector = WordSelector()

    Text(text = "The chosen category and word is: "+selector.getCategory()+
            " and "+selector.getWord())
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