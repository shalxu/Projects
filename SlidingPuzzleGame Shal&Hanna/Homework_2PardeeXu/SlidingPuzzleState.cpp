//
//  SlidingPuzzleState.cpp
//  Homework_2PardeeXu
//
//  Created by Hanna on 2/9/15.
//  Copyright (c) 2015 University of Denver. All rights reserved.
//

#include "SlidingPuzzleState.h"
#include "LList.h"
#include <iostream>


//Use the representation up = 0, down = 1, left = 2, right = 3. Hint: look at the binary representation when writing your ApplyMove and UndoMove functions.
SlidingPuzzleState::SlidingPuzzleState(const int tiles[12]){
    for(int i=0;i<12;i++)
    {
        int row=i/4;
        int column=i%4;
        numbers[row][column]=tiles[i];
    }
}

// A function which is passed a row [0..2] and column [0..3] and returns the number of the tile in that grid position (0 if empty, -1 if row or col are out of range):
int SlidingPuzzleState::GetTileInSquare(int row, int col){
    if(row<0||row>2||col<0||col>3)
    {
        return -1;
    }
    return numbers[row][col];
}

// A function which sets its arguments to the current row and column of the empty square:
void SlidingPuzzleState::GetEmptySquare(int &row, int &col){
    for(int i=0;i<3;i++){
        for(int j=0;j<4;j++){
            if(numbers[i][j]==0)
            {
                row=i;
                col=j;
            }
        }
    }
}


//A function which prints the current state, printing each row on a separate line, and each column 3 positions wide, right justified with leading spaces. The empty square should be printed as dot (period):
void SlidingPuzzleState::Print(){
    for(int i=0;i<3;i++){
        for(int j=0;j<4;j++){
            if(numbers[i][j]==0)
            {
                std::cout<<".";
            }
            else
            {
                std::cout<<numbers[i][j];
            }
            std::cout<<"   ";
        }
        std::cout<<std::endl;
    }
}

//A function which determines if the puzzle state is the solution. It returns true if the state represents the goal state shown above, false otherwise:
bool SlidingPuzzleState::IsSolution(){
    bool result=true;
    for(int i=0;i<3;i++){
        for(int j=0;j<4;j++){
            if(numbers[i][j] != i*4+j){
                result=false;
            }
        }
    }
    return result;
}

// A function which takes a move and changes the state according to the move. It returns true if the move is valid; false otherwise:
bool SlidingPuzzleState::ApplyMove(int move){
    int temprow = 0;
    int tempcol = 0;
    GetEmptySquare(temprow, tempcol);
    switch(move){
        case 0:
            if(temprow == 0){
                return false;
            }
            numbers[temprow][tempcol] = GetTileInSquare(temprow - 1, tempcol);
            numbers[temprow-1][tempcol] = 0;
            return true;
            break;
            
        case 1:
            if(temprow == 2){
                return false;
            }
            numbers[temprow][tempcol] = GetTileInSquare(temprow + 1, tempcol);
            numbers[temprow+1][tempcol] = 0;
            return true;
            break;
            
        case 2:
            if(tempcol== 0){
                return false;
            }
            numbers[temprow][tempcol] = GetTileInSquare(temprow, tempcol - 1);
            numbers[temprow][tempcol - 1] = 0;
            return true;
            break;
            
        case 3:
            if(tempcol == 3){
                return false;
            }
            numbers[temprow][tempcol] = GetTileInSquare(temprow, tempcol + 1);
            numbers[temprow][tempcol + 1] = 0;
            return true;
            break;
    }
    return false;
}

// A function which takes a move and changes the state by applying the move in reverse. (UndoMove can be written using ApplyMove.) It returns true if the reverse of the move can be performed; false otherwise:
bool SlidingPuzzleState::UndoMove(int move){
    bool result=false;
    switch(move){
        case 0:
            result = ApplyMove(1);
            break;
        case 1:
            result = ApplyMove(0);
            break;
        case 2:
            result = ApplyMove(3);
            break;
        case 3:
            result = ApplyMove(2);
            break;
    }
    return result;
}

SlidingPuzzleState* SlidingPuzzleState::Clone(){
    int *param=new int[12];
    for(int i=0;i<12;i++){
        param[i]=numbers[i/4][i%4];
    }
    SlidingPuzzleState* newPuzzle=new SlidingPuzzleState(param);
    delete[] param;
    return newPuzzle;
}

void SlidingPuzzleState::GetMoves(LList<int> &moves){
    bool isMoveValid=false;
    for(int i=0;i<4;i++)
    {
        isMoveValid=ApplyMove(i);
<<<<<<< .mine
		if (isMoveValid)
		{
			moves->AddFront(i);
			std::cout << i;
		}
=======
        if(isMoveValid)
        {
            moves.AddFront(i);
            UndoMove(i);
        }
>>>>>>> .r42538
    }
}