male(jay).
male(aditya). 
male(mahir).
male(yash).
male(abhishek).
male(arjun).

female(dia).  
female(lehar).
female(sia). 
female(vaani).
female(skyler).
female(sanika).

married(jay, dia).
married(aditya, lehar).  
married(yash, sia).
married(abhishek, vaani).
married(mahir, skyler).

parent(jay, aditya).
parent(dia, aditya). 

parent(aditya, yash).  
parent(lehar, yash).

parent(yash, mahir).
parent(sia, mahir).

parent(aditya, sanika). 
parent(lehar, sanika).

parent(jay, vaani).
parent(dia, vaani).

sibling(mahir, arjun).

sibling(X, Y) :- parent(Z, X), parent(Z, Y), X \= Y.

brother(X,Y) :- male(X), sibling(X,Y). 
sister(X,Y) :- female(X), sibling(X,Y).

son(X,Y) :- male(X), parent(Y,X).
daughter(X,Y) :- female(X), parent(Y,X).  

spouse(X, Y) :- married(X, Y).  
spouse(Y, X) :- married(X, Y).

aunt_uncle(X, Z) :- sibling(Y, X), parent(Y, Z).
niece_nephew(X, Y) :- sibling(Z, A), parent(A, Y), parent(Z, X). 

cousin(X, Y) :- parent(A, X), sibling(A, B), parent(B, Y), X \= Y.

grandparent(X, Z) :- parent(X, Y), parent(Y, Z).  
grandchild(X, Z) :- grandparent(Z, X).
