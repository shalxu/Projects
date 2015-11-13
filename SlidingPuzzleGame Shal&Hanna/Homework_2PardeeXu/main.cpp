//
//  main.cpp
//  Homework_2PardeeXu
//
//  Created by Hanna on 2/9/15.
//  Copyright (c) 2015 University of Denver. All rights reserved.
//

#include <iostream>
#include "SlidingPuzzleState.h"
#include <assert.h>

const int kUp = 0;

const int kDown = 1;

const int kLeft = 2;

const int kRight = 3;


void DepthLimitedDFS(int depth, SlidingPuzzleState *state) {
      state->Print();
    std::cout<<std::endl;
    if (depth == 0 || state->IsSolution())
    {
        return;
    }
    
    LList<int> *moveList= new LList<int>();
    state->GetMoves(*moveList);
    int move=-1;
    while(moveList->IsEmpty() == false){
        move=moveList->PeekFront();
        std::cout<<"m"<<move;
        std::cout<<std::endl;
        moveList->RemoveFront();
<<<<<<< .mine
        state->ApplyMove(move);
        DepthLimitedDFS(depth -1, state);
=======
        state->ApplyMove(move);
        DepthLimitedDFS(depth - 1, state);
>>>>>>> .r42538
        state->UndoMove(move);
        
    }
    moveList=nullptr;
    delete moveList;
}

void TestHomework2()
{
    const int goal[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    SlidingPuzzleState state(goal);
    
    // Perform solution test
    assert(state.IsSolution());
    
    // test that we can clone the state properly
    SlidingPuzzleState *clone = state.Clone();
    for (int row = 0; row < 3; row++)
    {
        for (int col = 0; col < 4; col++)
        {
            assert(state.GetTileInSquare(row, col) == clone->GetTileInSquare(row, col));
        }
    }
    delete clone;
    clone = 0;
    
    // Test that we end up in the proper state
    
    const int moves[] = {kRight, kUp, kUp, kLeft, kLeft, kDown, kDown, kLeft, kUp, kRight, kRight, kDown, kRight, kUp, kLeft, kUp, kRight, kDown, kDown, kLeft, kUp, kRight, kUp, kLeft, kLeft, kLeft};
    
    for (int x = 25; x >= 0 ; x--)
    {
        assert(state.UndoMove(moves[x]) == true);
        assert(state.IsSolution() == false);
    }
    
    const int sol[] = {1, 10, 3, 6, 8, 2, 5, 11, 9, 4, 0, 7};
    SlidingPuzzleState solution(sol);
    for (int row = 0; row < 3; row++)
    {
        for (int col = 0; col < 4; col++)
        {
            assert(state.GetTileInSquare(row, col) == solution.GetTileInSquare(row, col));
        }
    }
    
    // Test that we get back to the start state
    for (int x = 0; x < 26; x++)
    {
        assert(state.ApplyMove(moves[x]) == true);
    }
    assert(state.IsSolution() == true);
    
    
    // Test legal actions as we move across the board
    
    assert(solution.ApplyMove(kDown) == false);
    assert(solution.IsSolution() == false);
    assert(solution.ApplyMove(kRight) == true);
    assert(solution.IsSolution() == false);
    assert(solution.ApplyMove(kRight) == false);
    assert(solution.IsSolution() == false);
    assert(solution.ApplyMove(kUp) == true);
    assert(solution.IsSolution() == false);
    assert(solution.ApplyMove(kUp) == true);
    assert(solution.IsSolution() == false);
    assert(solution.ApplyMove(kUp) == false);
    assert(solution.IsSolution() == false);
    assert(solution.ApplyMove(kLeft) == true);
    assert(solution.IsSolution() == false);
    assert(solution.ApplyMove(kLeft) == true);
    assert(solution.IsSolution() == false);
    assert(solution.ApplyMove(kLeft) == true);
    assert(solution.IsSolution() == false);
    assert(solution.ApplyMove(kLeft) == false);
    assert(solution.IsSolution() == false);
    assert(solution.ApplyMove(kDown) == true);
    assert(solution.IsSolution() == false);
    assert(solution.ApplyMove(kDown) == true);
    assert(solution.IsSolution() == false);
    assert(solution.ApplyMove(kDown) == false);
    assert(solution.IsSolution() == false);
    
    solution.Print();
    
    // Test that we end up in the proper state
    
    const int modifiedState[] = { 8, 1, 10, 3, 9, 2, 5, 6, 0, 4, 7, 11};
    SlidingPuzzleState modified(modifiedState);
    for (int row = 0; row < 3; row++)
    {
        for (int col = 0; col < 4; col++)
        {
            assert(modified.GetTileInSquare(row, col) == solution.GetTileInSquare(row, col));
        }
    }
    
    // Test that you don't generate illegal moves in the corners
    SlidingPuzzleState newstate(goal);
    LList<int> acts;
    newstate.GetMoves(acts);
    for (int x = 0; x < 2; x++)
    {
        assert(acts.IsEmpty() == false);
        assert(acts.PeekFront() != kUp && acts.PeekFront() != kLeft);
        acts.RemoveFront();
    }
    assert(newstate.ApplyMove(kRight) == true);
    assert(newstate.ApplyMove(kRight) == true);
    assert(newstate.ApplyMove(kRight) == true);
    assert(newstate.ApplyMove(kDown) == true);
    assert(newstate.ApplyMove(kDown) == true);
    
    newstate.GetMoves(acts);
    newstate.GetMoves(acts); // should still only have 2 moves in the list
    for (int x = 0; x < 2; x++)
    {
        assert(acts.IsEmpty() == false);
        assert(acts.PeekFront() != kRight && acts.PeekFront() != kDown);
        acts.RemoveFront();
    }
    
    
}
int main(int argc, const char * argv[]) {
    //Your code should check that all tile values are in the range [0..11] and that there are no duplicates (in case of error, write a message to std::cerr).
<<<<<<< .mine
    const int exampleTiles[12] = { 0, 5, 3, 7, 6, 1, 2, 11, 4, 8, 9, 10 };
    SlidingPuzzleState *puzzleState = new SlidingPuzzleState(exampleTiles);
    puzzleState->Print();
	std::cout<<puzzleState->ApplyMove(1);
	puzzleState->UndoMove(1);
	puzzleState->Print();
	//LList<int> *moveList = new LList<int>(); 
	//*moveList=puzzleState->GetMoves();
	/*while (moveList->IsEmpty() == false)
	//{
		std::cout << moveList->PeekFront();
		std::cout << "?";
		moveList->RemoveFront();
	//}
	*/
    //DepthLimitedDFS(1, puzzleState);

=======
    //const int exampleTiles[12] = { 0, 5, 3, 7, 6, 1, 2, 11, 4, 8, 9, 10 };
    //SlidingPuzzleState *puzzleState = new SlidingPuzzleState(exampleTiles);
    //puzzleState->Print();
    //DepthLimitedDFS(10, puzzleState);
    TestHomework2();
>>>>>>> .r42538
         return 0;
}

