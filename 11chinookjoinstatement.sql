--Xavier's 11 joins on the chinookDB

select * from artist
inner join album on album.artistid = artist.artistid
inner join track on track.albumid = album.albumid
inner join genre on genre.genreid = track.genreid
inner join playlisttrack on playlisttrack.trackid = track.trackid
inner join playlist on playlist.playlistid = playlisttrack.playlistid
inner join invoiceline on invoiceline.trackid = track.trackid
inner join invoice on invoice.invoiceid = invoiceline.invoiceid
inner join customer on customer.customerid = invoice.customerid
inner join employee on employee.employeeid = customer.supportrepid;