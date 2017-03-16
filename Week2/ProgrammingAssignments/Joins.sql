-- INNER JOIN OF ALL 11 TABLES
SELECT c.LASTNAME "CUSTOMER", e.LASTNAME "EMPLOYEE", TOTAL, QUANTITY,
t.NAME "TRACK NAME", a.TITLE "ALBUM TITLE", art.NAME "ARTIST", g.NAME "GENRE",
m.NAME "MEDIA TYPE", p.NAME "PLAYLIST", pt.TRACKID
FROM CUSTOMER c
INNER JOIN EMPLOYEE e ON c.SUPPORTREPID=e.EMPLOYEEID
INNER JOIN INVOICE i ON c.CUSTOMERID=i.CUSTOMERID
INNER JOIN INVOICELINE il ON i.INVOICEID=il.INVOICEID
INNER JOIN TRACK t ON il.TRACKID=t.TRACKID
INNER JOIN ALBUM a ON t.ALBUMID=a.ALBUMID
INNER JOIN ARTIST art ON a.ARTISTID=art.ARTISTID
INNER JOIN GENRE g ON t.GENREID=g.GENREID
INNER JOIN MEDIATYPE m ON t.MEDIATYPEID=m.MEDIATYPEID
INNER JOIN PLAYLISTTRACK pt ON t.TRACKID=pt.TRACKID
INNER JOIN PLAYLIST p ON p.PLAYLISTID=pt.PLAYLISTID; 