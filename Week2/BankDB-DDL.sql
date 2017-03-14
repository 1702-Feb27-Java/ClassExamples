insert into users values ('', 'Xavier', 'Grogan', 'xpgrogan', 'password', 1);

create sequence account_seq
    start with 1
    increment by 1;
    
create or replace TRIGGER user_trigger --name
    BEFORE INSERT ON USERS --upon what event
    FOR EACH ROW --how often
    BEGIN --start what actually happens
        SELECT user_seq.NEXTVAL
        INTO :new.USER_ID
        FROM dual;
    END;
    
create or replace TRIGGER account_trigger --name
    BEFORE INSERT ON accounts --upon what event
    FOR EACH ROW --how often
    BEGIN --start what actually happens
        SELECT account_seq.NEXTVAL
        INTO :new.account_id
        FROM dual;
    END;
/
select * from users;
update users set adminpin = '1234' where USERNAME = 'xpgrogan';