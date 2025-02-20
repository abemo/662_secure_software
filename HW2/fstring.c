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

// The following code is vulnerable to a format string attack.
// The user input is directly used as part of the format string.
// An attacker can use a malicious input to read or write arbitrary memory locations or run malicious code.
void unsafeEcho(const char* input) {
	static const char echoFormat[] = "User's name is (unsafe): %s\n";
	size_t len = strlen(echoFormat) + strlen(input) - 1;
	char* format = (char*)malloc(len);
	if (format == NULL) {
		fprintf(stderr, "malloc failed\n");
		exit(EXIT_FAILURE);
	}
	snprintf(format, len, echoFormat, input);
	printf(format);
	free(format);
}

// Safe function that prints user input without using it as part of a format string
void safeEcho(const char* input) {
	static const char echoFormat[] = "User's name is (safe): %s\n";
	fprintf(stdout, echoFormat, input);
}

int main(void) {
	printf("Enter your name: ");
	char name[100];
	fgets(name, sizeof(name), stdin);
	unsafeEcho(name);
	safeEcho(name);
	return 0;
}
