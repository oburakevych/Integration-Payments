insert into authorities(id, authority, username) values (1, 'ROLE_SUPERVISOR', 'ob1');
insert into authorities(id, authority, username) values (2, 'ROLE_OPERATOR', 'ob2');
insert into authorities(id, authority, username) values (3, 'ROLE_USER', 'ob3');

insert into account() values (1, curdate(), 'TEST 01', 1, 1);

-- Owner password hashed (MD5). The original password is: 123456789

insert into owner() values(1, null, '101', 'Lviv', 'UA', curdate(), 'ob@pollsoft.com', 1, 'Alex', 'Supervisor', '25f9e794323b453885f5181f1b624d0b', '51282828', 'Shevchenka', 1, 'ob1', '81051', 1);
insert into owner() values(2, null, '102', 'Lviv', 'UA', curdate(), 'ob@pollsoft.com', 1, 'Alex', 'Operator', '25f9e794323b453885f5181f1b624d0b', '51282828', 'Shevchenka', 1, 'ob2', '81051', 1);
insert into owner() values(3, null, '103', 'Lviv', 'UA', curdate(), 'ob@pollsoft.com', 1, 'Alex', 'User', '25f9e794323b453885f5181f1b624d0b', '51282828', 'Shevchenka', 1, 'ob2', '81051', 1);