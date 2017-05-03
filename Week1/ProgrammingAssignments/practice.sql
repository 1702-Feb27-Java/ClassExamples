create view short_album as
select * from album
where albumid < 25;

create view short_artist as
select * from artist
where ARTISTID < 25;

select * from short_album;

select short_album.title, short_artist.name  -- selecting desired columns
from short_album    -- the home table 
inner join short_artist   -- what you are joining to the home table
on short_album.ARTISTID = short_artist.ARTISTID;  --relate these on thier foreign keys

select short_album.title, short_artist.name  -- selecting desired columns
from short_album    -- the home table 
full join short_artist   -- what you are joining to the home table
on short_album.ARTISTID = short_artist.ARTISTID;

select short_album.title AS "ALBUM TITLE", short_artist.name AS "ARTIST NAME"  -- selecting desired columns
from short_album    -- the home table 
inner join short_artist   -- what you are joining to the home table
on short_album.ARTISTID = short_artist.ARTISTID;  --relate these on thier foreign keys--relate these on thier foreign keys

select art.name AS "Artist Name", al.title AS "ALBUM TITLE", tr.name AS "TRACK NAME"
from artist art
inner join album al on art.ARTISTID = al.ARTISTID
inner join track tr on tr.ALBUMID = al.ALBUMID;

create view lotsofinnerjoins as
select art.name AS "Artist Name", al.title AS "ALBUM TITLE", tr.name AS "TRACK NAME", cust.customerid as "CUSTOMER ID", emp.EmployeeID as "EMPLOYEE ID", gen.GENREID as "GENRE ID", inv.invoiced as "INVOICE ID", ilin.invoicelineid as "INVOICE LINE ID", med.mediatypeid as "MEDIA TYPE ID", plist.playlistid as "PLAY LIST ID", ptr.playlistid as "PLAYLIST TRACK ID"
from artist art
inner join album al on art.artistid = al.artistid
inner join track tr on tr.albumid = al.albumid
inner join genre gen on gen.genreid = tr.genreid
inner join playlisttrack ptr on ptr.trackid = tr.trackid
inner join playlist plist on plist.playlistid = pt.playlistid
inner join invoiceline ilin on ilin.trackid = tr.trackid
inner join invoice inv on inv.invoiceid = il.invoiceid
inner join customer cust on cust.customerid = i.customerid
inner join employee emp on emp.employeeid = c.supportrepid;

select count(*) from lotsofinnerjoins;