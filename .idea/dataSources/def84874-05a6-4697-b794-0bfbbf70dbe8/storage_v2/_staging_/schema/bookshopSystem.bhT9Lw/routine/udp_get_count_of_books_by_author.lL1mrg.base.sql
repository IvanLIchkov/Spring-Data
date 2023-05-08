create
    definer = root@localhost procedure udp_get_count_of_books_by_author(IN first_name varchar(50), IN last_name varchar(50))
begin
	select count(b.id) from authors as a
		join books as b on b.author_id = a.id
        where a.first_name like first_name and a.last_name like last_name
        group by a.id;
end;

