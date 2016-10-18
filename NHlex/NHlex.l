%{
char pay[100];
char store[100];
char price[100];
char temp[100];


#include <stdio.h>
void main() {
	int i;
	FILE *fp;
	fp = fopen("NH.txt", "w");
	yylex();
	fprintf(fp, "%s\n", pay);
	fprintf(fp, "%s\n", store);


	char* pr = price;
	char* prOld = strdup( price );
	char* prNew;
	char* prOldFree = prOld;
     
	prNew = strchr( prOld, ',' );
	while( prNew )
	{
            strncpy( pr, prOld, prNew-prOld);
            *(pr+(prNew-prOld)) = 0;
            
            pr += (prNew-prOld);
            
            prOld = prNew + 1;
            prNew = strchr( prOld, ',' );
            
            if( prNew == NULL )
            strcat(pr, prOld );
	}
     free(prOldFree);

	fprintf(fp, "%s", price);
	fclose(fp);
}
%}
digit [0-9]
%x TT
%%
{digit}*(","{digit}*)+	{ strcpy(temp, yytext); }
"원"	BEGIN TT; { strcpy(price, temp); }
"\n"	;
<TT>"\n"	;
<TT>.*"("{digit}"*"{digit}"*)"	{ strcpy(pay, yytext); }
<TT><<EOF>>	{ strcpy(store, temp); return 0;}
<TT>.*	{ strcpy(temp, yytext); }
%%
int yywrap()
{
	return 1;
}
