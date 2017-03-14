
create view innerjoinsmusic as
select art.name AS "Artist Name", al.title AS "Album Title", tr.name AS "Track Name", ge.name AS "Genre", mt.name AS "Media Type",
  pl.name AS "Playlist Name", pt.trackid AS "Track Id", invl.unitprice AS "Unit Price", inv.total AS "Invoice Total", cust.firstname AS "Customer First Name", 
  emp.title AS "Employee Title"
from artist art
inner join album al on art.ARTISTID = al.ARTISTID
inner join track tr on tr.albumid = al.albumid
inner join genre ge on ge.genreid = tr.genreid
inner join mediatype mt on tr.mediatypeid = mt.mediatypeid
inner join playlisttrack pt on pt.trackid = tr.trackid
inner join playlist pl on pl.playlistid = pt.playlistid
inner join invoiceline invl on invl.trackid = tr.trackid
inner join invoice inv on inv.invoiceid = invl.invoiceid
inner join customer cust on cust.customerid = inv.customerid
inner join employee emp on emp.employeeid = cust.supportrepid;
