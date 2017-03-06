package banking;

// Type is the status of a person in the database
// ADMIN can change userName/password/checking amount/saving amount
// ADMIN can add employee and view every account
// EMPLOYEE can approve accounts and view all customers, and view all applications
// CUSTOMER can apply for checking/savings, and deposit/withdraw money
public enum Type{EMPLOYEE, CUSTOMER, ADMIN};
