# MicroVM

## Memory
- Program
- RAM
- Stack

## Register
- PC (Program counter)
- SP (Stack pointer)

## Commands
### Load/Store
- STORE <address> (Pop head of stack and save it to address)
- LOAD <address> (Load data at address and push it to stack)
- ALLOC <immediate> (allocates a given number of cells)

### Arithmetic
- ADD (Pop two integers from stack, add them and push the result back to the stack)
- SUB (Pop two integers from stack, subtract them and push the result back to the stack)
- MUL
- DIV (integer division) 
- MOD
- NEG (Pop an integer from stack, multiply by -1 and push the result back to the stack)

### Comparison
- LT (less than)
- LTE (less than or equals)
- GT (greater than)
- GTE (greater than or equals)
- EQ (equals)
- NEQ (not equals)

### Boolean
- AND (logical and)
- OR (logical or)
- NOT (logical not)
	
### Constants
- CONST <immediate> (push immediate to stack)
- TRUE (push 'true' to stack)
- FALSE (push 'false' to stack)
	
### Control
- JUMP <marker> (jump to marker)
- FJUMP <marker> (pop value from stack, if 'false' then jump to marker)
- HALT (stops the program immediately)

### IO
- READ (read a value from standard input and push it to the stack)
- WRITE (pop a value from the stack and write it to the standard output)