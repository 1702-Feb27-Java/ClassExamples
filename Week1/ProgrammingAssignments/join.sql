CREATE VIEW SHORTALBUM AS 
SELECT * FROM ALBUM
WHERE ALBUMID < 25;

CREATE VIEW SHORTARTIST AS 
SELECT * FROM ARTIST
WHERE ARTISTID < 25;

SELECT al.TITLE AS "Album Title", art.name AS "Artist Name", tr.name as "Track Name", c.customerid as "Customer ID", e.EmployeeID as "Employee ID", g.GENREID as "Genre ID", i.invoiceid as "Invoice ID", il.invoicelineid as "Invoiceline ID", m.mediatypeid as "Media Type id", p.playlistid as "Play list id", pt.playlistid as "Play list track id"
FROM artist art
inner JOIN SHORTALBUM al
ON al.ARTISTID = art.ARTISTID
inner join track tr on tr.albumid = al.albumid
inner Join Customer c on al.albumid = c.customerid
inner Join Employee e on al.albumid = e.employeeid
inner Join Genre g on al.albumid = g.genreid
inner Join Invoice i on al.albumid = i.invoiceid
inner Join InvoiceLine il  on al.albumid = il.invoicelineid
inner Join Mediatype m on al.albumid = m.mediatypeid
inner Join playlist p on al.albumid = p.playlistid
inner Join playlisttrack pt on al.albumid = pt.playlistid;
