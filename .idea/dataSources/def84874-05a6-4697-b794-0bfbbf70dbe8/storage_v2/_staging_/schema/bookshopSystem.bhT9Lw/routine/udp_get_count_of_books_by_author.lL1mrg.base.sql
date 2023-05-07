create
    definer = root@localhost procedure udp_get_count_of_books_by_author(IN first_name varchar(50), IN last_name varchar(50))
BEGIN
        SELECT count(b.author_id) from authors as a
                 join books b on authors.id = b.author_id
        where a.first_name like firstName and a.last_name like lastName
        group by authors.id;
    END;

