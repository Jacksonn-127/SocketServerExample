# SocketServerExample
A socket & server example that finds either palindromes of strings or quadratic equation

Only requirement is knowledge of working with command line and Java installed on your system. 

Start the server in command line using the command 

Java StartServer (5 digit port number) 
ensure you use same port number when connecting client

and the client, each of which is it's own file as follows 

java QuadraticEq 127.0.0.1 (matching 5 digit port number) (num1)!(num2)!(num3)
This will return either the roots of the 3 numbers or a message letting you know there are no roots.

or

java Palindrome 127.0.0.1 (matching 5 digit port number) (any string)
Returns either true or false on whether the string you sent is a palidrome.
