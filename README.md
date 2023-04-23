# Simple CPU based on nothing

idk how to make an actuall cpu or even how to start making one, so I simply tried to make this

is more like an interpreter for a programming language based on numbers that tries to mimic the behaviour of an actuall cpu but yeah idk

## Cpu Structure

- Program Counter (PC)

- Stack

- Memory (not cpu but idc)

- Registers:

  there are 4 registers, register A, B, C and Carry/borrow idk
  
  They're represented in an array of ints, so if you want to call register A you'll need to use the index 0, for register B you'll need to use index 1 and so on
  
  - A = [0]
  
  - B = [1]
  
  - C = [2]

## Opcodes

Before I start talking about the opcodes, keep in mind those are in decimal not hex (i'll probably change it later but for now they're in decimal)

- 1XXA  -> Load the XX value in the register A (it's actually the number of the register, like, in this case, 0)

- 20AB ->  Takes the value in the register A and puts it in B (like, B = A, or regs[1] = regs[0])

- 3ABC ->  Sums the values in A and B and stores the result in C, you can repeat the registers like 3AAA or 3ABA (3ABC -> C = A+B, or regs[2] = regs[0]+regs[1])

- 4ABC ->  Same as above but with a subtraction

- 5XXX ->  Jumps to the XXX value in memory

- 600A ->  Prints the value of the register A (can also be register B or C)

- 7XXX ->  Calls a subroutine in the value XXX in memory (I'll talk more about subroutines and how they work here)

- 8000 ->  Exits a subroutine

- 900A ->  Jumps next instruction if value in the register A is 0 (can also be the registers B and C)



## Subroutines

Subroutines are treated like functions here, so, when we're calling a subroutine we're basically just jumping to an adress in memory that does something, when it it done doing that thing, it returns to where we were previsously

so, when you call a subroutine using the instruction 7XXX, you're adding the current position in memory of this address to the stack, so when you return from the subroutine the program knows where it should go next, and jumping to the instruction stored in the position XXX in memory



As i said, idk what the f... i'm doing with this thing so i don't really know if any of these things make sense or no but idc, it's fun doing this
