//
//  SlidingPuzzleState.h
//  Homework_2PardeeXu
//
//  Created by Hanna on 2/9/15.
//  Copyright (c) 2015 University of Denver. All rights reserved.
//

#ifndef __Homework_2PardeeXu__SlidingPuzzleState__
#define __Homework_2PardeeXu__SlidingPuzzleState__

#include <list>
#include "LList.h"
#include <stdio.h>
class SlidingPuzzleState
{
public:
    //Use the representation up = 0, down = 1, left = 2, right = 3. Hint: look at the binary representation when writing your ApplyMove and UndoMove functions.
    SlidingPuzzleState(const int tiles[12]);
    
    // A function which takes a move and changes the state by applying the move in reverse. (UndoMove can be written using ApplyMove.) It returns true if the reverse of the move can be performed; false otherwise:
    bool UndoMove(int move);
    
    // A function which is passed a row [0..2] and column [0..3] and returns the number of the tile in that grid position (0 if empty, -1 if row or col are out of range):
    int GetTileInSquare(int row, int col);
    
    // A function which sets its arguments to the current row and column of the empty square:
    void GetEmptySquare(int &row, int &col);
    
    
    //A function which prints the current state, printing each row on a separate line, and each column 3 positions wide, right justified with leading spaces. The empty square should be printed as dot (period):
    void Print();
    
    //A function which determines if the puzzle state is the solution. It returns true if the state represents the goal state shown above, false otherwise:
    bool IsSolution();

    // A function which takes a move and changes the state according to the move. It returns true if the move is valid; false otherwise:
    bool ApplyMove(int move);
    
    //A function which returns a pointer to a new SlidingPuzzleState object which is an independent copy of the current object:
    SlidingPuzzleState *Clone();

    //A function which returns all legal moves. The return value is a linked list of the possible legal moves. The list uses the templated LList class developed in Lab 9. You may use the Lab 9 solution code or your own Lab 9 code:
    void GetMoves(LList<int> &moves);
    
    
private:
    int numbers[3][4];
    
};


#endif /* defined(__Homework_2PardeeXu__SlidingPuzzleState__) */
