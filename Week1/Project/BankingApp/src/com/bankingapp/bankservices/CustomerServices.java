package com.bankingapp.bankservices;

//Services customers have available to them
public interface CustomerServices
{
    //Sign up for portal with
    //USERNAME a username
    //PASSWORD for their password
    //
    //TRUE if creation succesful
    //FALSE if error occurs
    public boolean signUp (String username, String password);

    //======================================================
    // ANOTHER WAY TO DO IT IN THE FUTURE POSSIBILY
    //======================================================
    //Apply for a checking account
    //void ApplyChkAccnt ();

    //Apply for a savings account
    //void ApplySavAccnt ();
    //======================================================

    //Apply for an account
    //TYPE  type of account application 
    //
    //TRUE if application went through
    //FALSE otherwise
    public boolean applyAccnt (String type);

    //Withdraw from account 
    // AMOUNT amount no cents
    // TYPE accont type (savings,checking)
    // 
    // TRUE if successful
    // FALSE otherwise
    public int withdraw (int amount, String type);
    
    //Deposit from account 
    // AMOUNT amount no cents
    // TYPE accont type (savings,checking)
    // 
    // TRUE if successful
    // FALSE otherwise
    public int deposit (int amount, String type);
}
