package com.example.tictactoe

fun check(grid : Array<Array<Int>>):Boolean{
    if(grid[0][0]==grid[1][0] && grid[1][0] == grid[2][0] && grid[0][0] != -1){
        return true
    }
    if(grid[0][1]==grid[1][1] && grid[1][1] == grid[2][1] && grid[2][1] != -1){
        return true
    }
    if(grid[0][2]==grid[1][2] && grid[1][2] == grid[2][2] && grid[2][2] != -1){
        return true
    }
    if(grid[1][0]==grid[1][1] && grid[1][1] == grid[1][2] && grid[1][2] != -1){
        return true
    }
    if(grid[0][0]==grid[0][1] && grid[0][1] == grid[0][2] && grid[0][2] != -1){
        return true
    }
    if(grid[2][0]==grid[2][1] && grid[2][1] == grid[2][2] && grid[2][2] != -1){
        return true
    }
    if(grid[0][0]==grid[1][1] && grid[1][1] == grid[2][2] && grid[2][2] != -1){
        return true
    }
    if(grid[0][2]==grid[1][1] && grid[1][1] == grid[2][0] && grid[2][0] != -1){
        return true
    }

    return false

}