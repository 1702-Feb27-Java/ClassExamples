select *
from artist art
inner join album al on art.artistid = al.artistid
inner join track tr on tr.albumid = al.albumid
inner join genre gr on gr.genreid = tr.genreid
inner join playlisttrack pt on pt.trackid = tr.trackid
inner join playlist pl on pl.playlistid = pt.playlistid
inner join invoiceline il on il.trackid = tr.trackid
inner join invoice i on i.invoiceid = il.invoiceid
inner join customer c on c.customerid = i.customerid
inner join employee e on e.employeeid = c.supportrepid;