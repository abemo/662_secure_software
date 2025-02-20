/*
FIO30-C. Exclude user input from format strings
https://wiki.sei.cmu.edu/confluence/display/c/FIO30-C.+Exclude+user+input+from+format+strings 

One should never use a non-validated or non-sanitized user input as part of a format string. 
This is because the user input could contain malicious format string specifiers that could be used
to read or write arbitrary memory locations or run malicious code. This is similar to what happened
in the Heartbleed bug, where the lack of an input validation allowed an attacker to perform a buffer overread.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
	puts("Hello world!");
	return 0;
}
