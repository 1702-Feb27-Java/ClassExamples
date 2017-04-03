/*******************************************************************************
 * CREATES THE SCHEMA AND THE USER FOR THE TRMS DATABASE.
 *
 * AUTHOR: Michael Hobbs
 * DATE: 2017 March 20
 */

CREATE USER TRMSDB
IDENTIFIED BY password2;

GRANT CONNECT TO TRMSDB;
GRANT RESOURCE TO TRMSDB;

