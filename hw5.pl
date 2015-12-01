/*Shal Xu hw5.1-5*/
/*Prolog program for processing sets,including Union, Complement, Difference, Equal and other functions*/

isMember(X,[X|_]). /*test if the element on the left is contained in the list on the right*/
isMember(X,[_|Y]) :- isMember(X,Y).

/*combine([],X,X). combines two lists
combine([A|As],B,C) :- combine(As,B,D), =(C,[A|D]).*/

isUnion([],[],[]). /*Test if the third list is union of previous two lists.*/
isUnion([],X,X).
isUnion([A|As],B,C) :- isMember(A,B),!,isUnion(As,B,C).
isUnion([A|As],B,[A|C]) :- isUnion(As,B,C).

isIntersection([],_,[]). /*Test if the third list is intersection of previous two lists.*/
isIntersection([A|As],B,[A|C]) :- isMember(A,B),!, isIntersection(As,B,C).
isIntersection([_|As],B,C) :- isIntersection(As,B,C).

/*isEqual(A,B):- isUnion(A,B,D),isIntersection(A,B,C),isUnion(C,D,E).*/
isEqualLeft([],_). /*test if all the elements in the left list are contained in the right list. */
isEqualLeft([A|As],B) :- isMember(A,B),isEqualLeft(As,B).
isEqualRight(_,[]). /*test if all the elements in the right list are contained in the left list*/
isEqualRight(A,[B|Bs]) :- isMember(B,A), isEqualRight(A,Bs).
isEqual([],[]).
isEqual(A,B):- isEqualLeft(A,B),isEqualRight(A,B).

isDifference([],_,[]).
isDifference([A|As],B,C) :- isMember(A,B),!,isDifference(As,B,D), isEqual(D,C).
isDifference([A|As],B,C) :- isDifference(As,B,D),isEqual(C,[A|D]).