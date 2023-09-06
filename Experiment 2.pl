/* all gaming laptops are computers
rog is a gaming laptop
hence, rog is a computer */
computer(X) :- gaming_laptop(X).
gaming_laptop(rog).

/* all superheroes have special powers
spiderman is a superhero
hence, spiderman has special powers */
has_special_powers(X) :- superhero(X).
superhero(spiderman).

/* all programming languages are tools
python is a programming language
hence, python is a tool */
programmingLanguage(X) :- tool(X).
tool(python).

/* anyone who is skilled in programming and has a laptop is techy 
sarthak is skilled in programming
sarthak has a laptop
hence, sarthak is techy */
techy(X) :- skilled_programming(X), has_laptop(X).
skilled_programming(sarthak).
has_laptop(sarthak).

/* all students who study both mathematics and computer science are considered geeks
sarthak studies mathematics and computer science
hence, sarthak is a geek */
geek(X) :- student(X), studies_subject(X, mathematics), studies_subject(X, computer_science).
student(sarthak).
studies_subject(sarthak, mathematics).
studies_subject(sarthak, computer_science).

/* all products that are either electronics or have a discount over 50% are on sale
earphone is a product and also an electronic item
book is a product and has discount of 60%
hence, book is on sale */
on_sale(X) :- product(X), (electronic(X) ; (discount(X, Y), Y > 50)).
product(earphone).
electronic(earphone).
product(book).
discount(book, 60).
