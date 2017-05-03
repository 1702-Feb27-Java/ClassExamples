CREATE VIEW short_album as
  SELECT * FROM album
  WHERE albumid < 25;
/

CREATE VIEW short_artist AS
  SELECT * FROM artist
  WHERE artistid < 25;
/

SELECT * FROM short_album;
/

--SELECT DESIRED COLUMNS
SELECT art.name AS "Artist Name",  al.title AS "Album Title"
  --TABLE ON LEFT(HOME TABLE)
  FROM short_album al
  RIGHT JOIN short_artist art
  --FOREING KEY
  ON al.artistid = art.artistid; 
/



--------ACTUAL TABLE
-- GET ALL SONGS FROM ALL ARTIST
---GENESIS EXAMPLES
-- PRINTING ALL SONGS FROM ALBUM FIRST 
SELECT art.name AS "Artist Name",  al.title AS "Album Title", tr.name AS "Track Name"
  --TABLE ON LEFT(HOME TABLE)
  FROM artist art
  INNER JOIN album al 
   --FOREING KEY
   -- GET ALL ALBUMS FROM THE SAME ARTIST
    ON art.artistid = al.artistid
  INNER JOIN track tr 
   --FOREING KEY
    ON tr.albumid = al.albumid;
/
--==============================================================================
-- JOINING ALL TABLES IN CHINKOOK
--==============================================================================
SELECT ar.name, al.title, ge.name, me.name, tr.name, pl.name, inv.quantity, invo.total, cu.firstname, em.firstname as "Worker", em2.firstname as "Manager"
    FROM artist ar
--1
    --ALBUM DEPENDENT ON ARTIST
    INNER JOIN album al
        ON ar.artistid = al.artistid
    --TRACK DEPENDENT ALBUM
    INNER JOIN track tr
        ON al.albumid = tr.albumid
    --INVOICE LINE DEPENDENT ON TRACK
    INNER JOIN invoiceline inv
        ON tr.trackid = inv.trackid
--2
    --TRACK DEPENDENT ON MEDIATYPE
    INNER JOIN mediatype me
        ON me.mediatypeid = tr.mediatypeid
--3
    --TRACK DEPENDENT ON GENRE
    INNER JOIN genre ge
        ON ge.genreid = tr.genreid
--4
    --PLAYLISTTRACK DEPENDENT ON TRACK
    INNER JOIN playlisttrack plt
        ON tr.trackid = plt.trackid
    --PLAYLISTTRACK DEPENDENT ON PLAYLIST
    INNER JOIN playlist pl
        ON pl.playlistid = plt.playlistid
--5
    --INVOICELINE DEPENDENT ON INVOICE
    INNER JOIN invoice invo
        ON invo.invoiceid = inv.invoiceid
    --INVOICE DEPENDENT ON CUSTOMER
    INNER JOIN customer cu
        ON cu.customerid = invo.customerid
    --CUSTOMER DEPENDENT ON EMPLOYEE
    INNER JOIN employee em
        ON em.employeeid = cu.supportrepid
    --EMPLOYEE DEPENDENT ON EMPLOYEE
    INNER JOIN employee em2
        ON em2.employeeid = em.reportsto;
/
