-- JOINS
/* practice
CREATE VIEW short_album AS
SELECT * FROM album
WHERE albumid < 25;

CREATE VIEW short_artist AS
SELECT * FROM artist
WHERE artistid < 25;

SELECT * FROM short_album;

SELECT short_album.title, short_artist.name
FROM short_album
INNER JOIN short_artist
ON short_album.artistid = short_artist.artistid;
*/

---- producing a join of all tables in Chinook db:
SELECT art.name as "artist name", al.title as "album title", tr.name as "track name", me.name as "media type", play.name as "playlist name", inline.unitprice as "cost"
from artist art
inner join album al on art.artistid = al.artistid
inner join track tr on tr.albumid = al.albumid
inner join mediatype me on me.mediatypeid = tr.mediatypeid
inner join playlisttrack playtr on playtr.trackid = tr.trackid
inner join playlist play on play.playlistid = playtr.playlistid
inner join invoiceline inline on inline.trackid = playtr.trackid
inner join invoice invoice on invoice.invoiceid = inline.invoiceid
inner join customer customer on customer.customerid = invoice.customerid
inner join employee employee on employeeid = customer.supportrepid
;