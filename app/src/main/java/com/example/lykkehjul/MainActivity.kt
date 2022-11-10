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
import com.example.lykkehjul.data.specialChar
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

/**
 * The main game screen.
 */
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

/**
 * Composible that takes a word, creates a handler to keep track of
 * how many letters has been revealed, and displays the word in a row.
 * Automatically breaks up words longer than 10 characters into multiple rows.
 */
@Composable
fun WordDisplay(word: String) {
    val handler = WordHandler(word) //Create a word handler
    val wordArray = handler.getWord()
    var rows: Int = (wordArray.size + 9) / 10 //Number of rows with 10 letters on each row. Formula found here https://stackoverflow.com/questions/17944/how-to-round-up-the-result-of-integer-division
    var charIndex = 0 //Creating an index for the letters that isn't dependent on the rows
    Column(modifier = Modifier.fillMaxWidth()) { //a column to contain the rows of letters
        for (row in 1..rows) { //Creating the appropriate number of rows
            Row(modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 1..10) {
                    /* On the last 5 characters of each row but the last, check if there is a space.
                       If there is, immediately cut off the word. If not, put a hyphen in the 10th
                       character's place. If moving the rest of the characters to the next row would
                       make the last row overflow, increase the number of rows by one. */
                    if (i > 5 && rows > row) {
                        if (wordArray[charIndex].char == ' ') { //Check for space
                            if (((wordArray.size + 9 + (10-i)) / 10) > rows) { //Check whether the last row would overflow
                                rows++
                            }
                            charIndex++ //Since a linebreak still counts as a space, increment the counter
                            break
                        }
                        if (i == 10) { //Put a hyphen in the 10th spot. Don't increment the char counter
                            Text(
                                text = "-",
                                fontSize = 30.sp
                            )
                            if (((wordArray.size + 9 + (11-i)) / 10) > rows) { //Check whether the last row would overflow
                                rows++
                            }
                            break
                        }
                    }

                    //If neither of the above cases apply, add a character
                    LetterCard(letter = wordArray[charIndex])
                    if (charIndex != wordArray.lastIndex && i < 10 && wordArray[charIndex+1].char != ' ')
                        //If this character isn't the last and the next character isn't a space, add a spacer
                        Spacer(modifier = Modifier.width(20.dp))
                    if (charIndex == wordArray.lastIndex) break //If this is the last character
                    charIndex++
                }
            }
        }
    }


}

@Composable
fun LetterCard(letter: Letter) {
    if (letter.char == ' ') { //If the character is a space, add a spacer instead
        Spacer(modifier = Modifier.width(20.dp))
    }
    else {
        Text( //If the character is hidden, show an underscore. Otherwise, show the char. Certain characters are always visible
            text = if (letter.hidden && !specialChar.contains(letter.char)) "_" else letter.char.toString(),
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