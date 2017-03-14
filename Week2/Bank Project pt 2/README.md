This is a banking app written in Java.

There are three types of accounts that can interact with this banking app. Customer, employee, and admin accounts can all log into the app. 

Only CUSTOMER accounts can be created. Employee and admin accounts are present by default, and can be logged in for general purposes.

Customers can create new accounts with FIRSTNAME, LASTNAME, USERNAME, PASSWORD, and checking and savings account balances. At the time of account creation, an 8 character length alpha-numeric ID will be generated, but cannot be seen by the user. However, new accounts will need to be approved before using. If a customer tries to log into a new account, they will be kicked back out to the main menu.

Customer can deposit and withdraw from their accounts once approved. They cannot edit any fields in their account without admin approval.

Employees can view all and approve customer accounts, but cannot edit any customer info. Employees do not have their own checking and savings accounts. The default login for the employee is 'default' for username and '1234' for password.

Admins can view all and approve customer acccounts, and can edit a customer's FIRSTNAME, LASTNAME, USERNAME, AND PASSWORD. Admins do not have their own checking and savings accounts. The default login for the admin is 'admin' for username and '1234' for password.

---

All customer info is written and edited in the "customeraccounts.txt" in the root folder.

All employee info is stored in the "employeeaccount.txt" file.

All admin info is stored in the "admin.txt" file.