# Homework 3 Shal Xu
# Program to calculate a divided by b. Assume values are 6-bit unsigned integers
#The least-significant 12 bits of $v0 will store the quotient (least significant 6 bits)and remainder (next 6 bits)

j main

# =================================================================================================
# div function
# -------------------------------------------------------------------------------------------------
# a0 ->  dividend
# a1 ->  divisor
# jr has return address
# -------------------------------------------------------------------------------------------------
# v0 gets output
# =================================================================================================
div:
beq $a1,$zero,not_valid         # if divisor is 0, inputs are not valid
srl $t0,$a0,6                   # shift dividend right by 6 bits, store result in $t0
bne $t0,$zero,not_valid         # test if $t0 is 0. If not, $a0 is not a valid 6_bit input, jump to not_valid
srl $t0,$a1,6                   # shift divisor right by 6 bits, store result in $t0
bne $t0,$zero,not_valid         # test if $t0 is 0. If not, $a1 is not a valid 6_bit input, jump to not_valid
sll $t0,$a1,5                   # $t0 temporarily stores divisor in the process of shifting. Shift it all the way left first
addi $v1,$v1,0                  # v1 temporarily stores the quotient, initialize it to 0 
loop:
sub $a0,$a0,$t0                 # subtract divisor from dividend
blt $a0, $zero, add_back_divisor# if the subtracted result is smaller than 0, branch to add_back_divisor
addi $v1,$v1,1                  # add 1 to the quotient
ble $t0,$a1,exit                # if $t0 (temporary divisor) has been shifted right back to its original value, exit
sll $v1,$v1,1                   # shift quotient left by 1 bit
srl $t0,$t0,1                   # shift divisor right by 1 bit
j loop                          # jump back to loop
add_back_divisor:
add $a0,$a0,$t0                 # add divisor back to dividend            
ble $t0,$a1,exit                # if $t0 (temporary divisor) has been shifted right back to its original value, exit
srl $t0,$t0,1                   # shift divisor right by 1 bit
sll $v1,$v1,1                   # shift quotient left by 1 bit
j loop                          # jump back to loop
not_valid:
addi $v0,$zero,-1               # return -1 in $v0
jr $ra                          # return 
exit:
addi $v0,$a0,0                  # add remainder to $v0
sll $v0,$v0,6                   # shift $v0 left by 6 bits
add $v0,$v0,$v1                 # add quotient to $v0
jr $ra                          # return 

# =================================================================================================
	

# =================================================================================================
# main function
# =================================================================================================
# calculate 36 /6, return result in $v0
main:
addi $a0,$zero,36              # set $a0 to 36
addi $a1,$zero,6               # set $a1 to 6
jal div                        # call div function
# print out result
addi $a0,$v0,0                 # put result in $a0
li $v0,1                       # set $v0 to 1
syscall                        # syscall printing out integer in $a0
# print a new line
li $v0,11                      # set $v0 to 11
addi $a0,$zero,10              # set $a0 to 10 (new line feed)
syscall                        # syscall printing out ASCII character
# calculate 129/1, return result in $v0
addi $a0,$zero,129             # set $a0 to 129
addi $a1,$zero,1               # set $a1 to 1
jal div                        # call div function
# print out result
addi $a0,$v0,0                 # set $a0 to $v0
li $v0,1                       # set $v0 to 1
syscall                        # syscall printing out integer in $a0
# exit
li $v0,10                      # set $v0 to 10
syscall                        # syscall exiting 
