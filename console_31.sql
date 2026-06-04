create table movies
(
    id       serial primary key,
    title    varchar(100),
    director varchar(100),
    year     int
);


create or replace procedure add_movie(title_in varchar, director_in varchar, year_in int)
    language plpgsql
as
$$
begin
    insert into movies(title, director, year) VALUES (title_in, director_in, year_in);
end;
$$;

create or replace function list_movies()
    returns table
            (
                id       int,
                title    varchar,
                director varchar,
                year     int
            )
    language plpgsql
as
$$
begin
    return query select m.id, m.title, m.director, m.year from movies m;
end;
$$;

create or replace procedure update_movie(id_in int, title_new varchar, director_new varchar, year_new int)
    language plpgsql
as
$$
begin
    update movies set title = title_new, director = director_new, year = year_new where id = id_in;
end;
$$;

create or replace procedure delete_movie(id_in int)
    language plpgsql
as
$$
begin
    delete from movies where id = id_in;
end;
$$;

