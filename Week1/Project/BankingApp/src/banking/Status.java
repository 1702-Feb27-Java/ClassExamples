package banking;

// the Stats of an account
// ACTIVE has full services 
// APPLIED needs approval from an Employee 
// None is yet to apply
// APPLIED AND NONE can't access deposit/withdraw methods
public enum Status {ACTIVE, APPLIED, NONE};
