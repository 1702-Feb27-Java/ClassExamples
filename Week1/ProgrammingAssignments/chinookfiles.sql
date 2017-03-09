Create view short_album as
select * from album
where albumid < 25;

Create view short_artist as
select * from artist
where artistid < 25;

select short_album.title, short_artist.name
from short_album
inner join short_artist
on short_album.ARTISTID = short_artist.ARTISTID;


Select art.name as "Artist Name", al.title as "Album Title", tr.name as "Track Name", g.name as "Genre",
  mt.name as "Media Type", pl.playlistid as "Playlist Number", pl.name as "Playlist", il.invoicelineid as "Invoice Line",
  i.invoiceid as "Invoice ID", c.firstname as "Customer", e.firstname as "Transaction Rep"
FROM Artist art
INNER JOIN album al on art.ARTISTID = al.ARTISTID
INNER JOIN track tr on tr.ALBUMID = al.ALBUMID
INNER JOIN genre g on tr.GENREID = g.GENREID
INNER JOIN mediatype mt on tr.MEDIATYPEID = mt.MEDIATYPEID
INNER JOIN playlist pl on tr.ALBUMID = tr.ALBUMID
INNER JOIN invoiceline il on tr.trackid = il.trackid
INNER JOIN invoice i on il.invoiceid = i.invoiceid
INNER JOIN customer c on c.customerid = i.customerid
INNER JOIN employee e on c.supportrepid = e.employeeid;