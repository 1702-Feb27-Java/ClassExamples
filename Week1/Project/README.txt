Zack Stefan Banking App

if there is no file "banking.txt" the program will create one and add an Admin userName: "Bo$$" password "123"

main screen 3 actions -login/register/exit
login- lets you enter a userName if it is a userName not in the system it output and error message
		if the userName exists then it lets you enter a password and checks if its a valid combination
		
register- letss a customer register an account if the userName they pick isn't in userName
exit- exits the program and writes to an output file

----------------------------------------------------------------------------------------------
after you login depending on if you are a Employee/Customers/Admin it will give you access to different commands 

**IMPORTANT YOU CAN TYPE "help" TO DISPLAY THE LIST OF COMMANDS YOU HAVE ACCESS TO***

Customers list of commands

These commands apply for an account only if that account isn't approved
apply savings
apply checking

These commads let the customer deposit money in their account only if their account is approved
deposit savings
deposit checking

These commands let the customer withdraw money from their account only if the account is approved and they have enough money.
withdraw savings
withdraw checking
--------------------------------------------

Employee list of commands

view all- views ONLY Customer accounts
view apply- views the Customer accounts who have applied for an account

---------------------------------------------------------
Admin list of commands
view all- views ALL accounts in the database
add- creates a new Employee in the database an sets up their information
edit- allows the admin to edit userName/password/checking balance/savings balance in the database





