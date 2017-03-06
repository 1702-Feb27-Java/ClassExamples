#!/bin/bash

if [[ $1 == "c" ]]
then
    javac -cp /c/Users/tobon/Downloads/log4j-1.2.17.jar com/bankingapp/testbanking/testMain.java
    if [ $? -eq 0 ]
    then
        echo "Compile successfull"
    else
        echo "Compile failed"
    fi
else
    java -cp "C:\Users\tobon\Documents\Revature\BankingApp\src;C:\Users\tobon\Downloads\log4j-1.2.17.jar" com.bankingapp.testbanking.testMain

fi

