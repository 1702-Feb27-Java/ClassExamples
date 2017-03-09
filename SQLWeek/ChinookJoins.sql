Create view short_album as
select * from album
where albumid < 25;

Create view short_artist as
select * from artist
where artistid < 25;

select * from short_album;

select short_album.title, short_artist.name
from short_album
inner join short_artist
on short_album.ARTISTID = short_artist.ARTISTID;

select art.name as "Artist Name", al.title as "Album Title", tr.name as "Track Name", genre.name as "Genre",
media.name as "Media Type", plt.playlistid as "Playlist Number", pl.name as "Playlist", il.invoicelineid as "Invoice Line",
invoice.invoiceid as "Invoice ID", customer.firstname as "Customer", employee.firstname as "Transaction Rep"

from artist art
inner join album al on art.artistid = al.artistid
inner join track tr on tr.albumid = al.albumid
inner join genre on tr.genreid = genre.genreid
inner join mediatype media on tr.mediatypeid = media.mediatypeid
inner join playlisttrack plt on tr.trackid = plt.trackid
inner join playlist pl on plt.playlistid = pl.playlistid
inner join invoiceline il on tr.trackid = il.trackid
inner join invoice on il.invoiceid = invoice.invoiceid
inner join customer on invoice.customerid = customer.customerid
inner join employee on customer.supportrepid = employee.employeeid;