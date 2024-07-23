package com.example.tictactoe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Board(gridState : Array<Array<GameData>>, winnerYet:Boolean, onCellClick:(Int,Int) -> Unit){

    Column{
        for(i in 0..2){
            Row{
                for(j in 0..2){
                    val cell = gridState[i][j]
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .border(1.dp, Color.Black)
                            .clickable(enabled = !winnerYet) { onCellClick(i, j) },
                        contentAlignment = Alignment.Center

                    ){
                        cell.imageResource?.let{resid ->
                            Image(
                                painter = painterResource(id = resid),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )

                        }
                    }
                }
            }

        }
    }
}

@Composable
fun TicTacToeGame(){
    val xImg = R.drawable.picx
    val oImg = R.drawable.pico

    var gridState by remember {
        mutableStateOf(Array(3) { Array(3) { GameData(imageResource = null) } })
    }
    var grid by remember {
        mutableStateOf(Array(3){Array(3){-1} })
    }


    var currentPlayer by remember {
        mutableStateOf("X")
    }
    var winner = remember {mutableStateOf("No winner yet")}
    var winnerYet = remember {mutableStateOf(false)}

    fun resetGame() {
        gridState = Array(3) { Array(3) { GameData(imageResource = null) } }
        grid = Array(3) { Array(3) { -1 } }
        currentPlayer = "X"
        winner.value = "No winner yet"
        winnerYet.value = false
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("${winner.value}", color = Color.Black,
            fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Board(gridState = gridState, winnerYet=winnerYet.value){row, col ->

            if(gridState[row][col].imageResource == null){
                gridState = gridState.copyOf().apply {
                    this[row][col] = GameData(imageResource = if(currentPlayer == "X") xImg else oImg)
                }
                grid[row][col] = if(gridState[row][col].imageResource == xImg) 1 else 0

                if(check(grid)){
                    winner.value = "The winner is ${currentPlayer}"
                    winnerYet.value = true
                }
                currentPlayer = if(currentPlayer == "X") "O" else "X"

            }
        }

        //Spacer(modifier = Modifier.height(30.dp))

        Button(modifier = Modifier.padding(16.dp),
            onClick = {
            resetGame()
        }) {
            Text("New Game",color = Color.Black, fontSize = 30.sp )
        }

    }
}



