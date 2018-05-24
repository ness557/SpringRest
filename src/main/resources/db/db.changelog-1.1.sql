--liquibase formatted sql

-- insert into roles

--changeset ness:insert_into_roles_1
INSERT INTO ROLES VALUES (ROLE_SEQ.nextval, 'USER');
--rollback DELETE FROM ROLES WHERE ID = ROLE_SEQ.currval;

--changeset ness:insert_into_roles_2
INSERT INTO ROLES VALUES (ROLE_SEQ.nextval, 'ADMIN');
--rollback DELETE FROM ROLES WHERE ID = ROLE_SEQ.currval;

-- insert into users and userinfo

--changeset ness:insert_into_users_1
INSERT INTO USER_INFO VALUES (USERINFO_SEQ.nextval, 'ness55779@gmail.com', '0669031120');
INSERT INTO USERS VALUES  (USERS_SEQ.nextval, 'Roman', 'Bezruchko', USERINFO_SEQ.currval);
--rollback DELETE FROM USERS WHERE ID = USERS_SEQ.currval; DELETE FROM USER_INFO WHERE ID = USERINFO_SEQ.currval;

--changeset ness:insert_into_users_2
INSERT INTO USER_INFO VALUES (USERINFO_SEQ.nextval, 'somebodysmail@gmail.com', '0931691478');
INSERT INTO USERS VALUES  (USERS_SEQ.nextval, 'Ivan', 'Ivanov', USERINFO_SEQ.currval);
--rollback DELETE FROM USERS WHERE ID = USERS_SEQ.currval; DELETE FROM USER_INFO WHERE ID = USERINFO_SEQ.currval;

--changeset ness:insert_into_user_role_1
--preconditions onFail:CONTINUE onError:CONTINUE
INSERT INTO USER_ROLE VALUES (0, 0);
--rollback DELETE FROM USER_ROLE WHERE USER_ID = 0 AND ROLE_ID = 0;

--changeset ness:insert_into_user_role_2
--preconditions onFail:CONTINUE onError:CONTINUE
INSERT INTO USER_ROLE VALUES (0, 1);
--rollback DELETE FROM USER_ROLE WHERE USER_ID = 0 AND ROLE_ID = 1;

--changeset ness:insert_into_user_role_3
INSERT INTO USER_ROLE VALUES (1, 0);
--rollback DELETE FROM USER_ROLE WHERE USER_ID = 1 AND ROLE_ID = 0;

--changeset ness:insert_into_students_1
INSERT INTO STUDENTS VALUES (1, 'student1', 'group1');
--rollback DELETE FROM STUDENTS WHERE ID = 1;

--changeset ness:insert_into_students_2
INSERT INTO STUDENTS VALUES (2, 'student2', 'group2');
--rollback DELETE FROM STUDENTS WHERE ID = 2;