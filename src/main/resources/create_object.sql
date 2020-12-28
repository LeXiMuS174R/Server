create table Players (
                         id serial primary key,
                         ip varchar(15) not null default '127.0.0.1',
                         name varchar(12),
                         constraint Uniq_Name unique (name),
                         max_points integer default 0,
                         win_count integer default 0,
                         lose_count integer default 0
);

create table Game (
                      id serial primary key,
                      start_datetime date,
                      duration time,
                      ACC1 integer,
                      foreign key (ACC1) references Players(id),
                      ACC2 integer,
                      foreign key (ACC2) references Players(id)
);

create table Shoot (
                       id serial primary key,
                       hit boolean,
                       game_id integer,
                       foreign key (game_id) references Game(id),
                       towards boolean,
                       datetime date
);