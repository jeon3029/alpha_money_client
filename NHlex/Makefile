
CC = cc
LEX = lex
all : pro

pro : lex.yy.c
	$(CC) lex.yy.c -o a.out 
lex.yy.c : NHlex.l
	$(LEX) NHlex.l

clean : 
	rm -f a.out lex.yy.c NH.txt
test : 
	./a.out < text 
