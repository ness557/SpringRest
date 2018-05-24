--liquibase formatted sql

-- tables

--changeset ness:create_table_roles
CREATE TABLE ROLES (
  ID INTEGER NOT NULL,
  NAME varchar2(20) NOT NULL,
  CONSTRAINT roles_pk
  PRIMARY KEY (ID));
--rollback drop table ROLES;

--changeset ness:create_table_user_info
CREATE TABLE USER_INFO (
  ID INTEGER NOT NULL,
  EMAIL varchar2(40) NOT NULL,
  PHONE INTEGER NOT NULL,
  CONSTRAINT
    userinfo_pk PRIMARY KEY (ID));
--rollback drop table USER_INFO;

--changeset ness:create_teble_users
CREATE TABLE USERS (
  ID INTEGER NOT NULL,
  FIRSTNAME varchar2(40) NOT NULL,
  LASTNAME varchar2(40) NOT NULL,
  USERINFO_ID INTEGER NOT NULL,
  CONSTRAINT
    users_pk PRIMARY KEY (ID),
  CONSTRAINT fk_userinfo
  FOREIGN KEY (USERINFO_ID)
  REFERENCES USER_INFO(ID));
--rollback drop table USERS;

--changeset ness:create_table_USER_ROLE
CREATE TABLE USER_ROLE (
  USER_ID INTEGER NOT NULL,
  ROLE_ID INTEGER NOT NULL);
--rollback drop table USER_ROLE;

--changeset ness:create_table_STUDENTS
CREATE TABLE STUDENTS(
  ID INTEGER NOT NULL,
  NAME varchar2(40) NOT NULL,
  GROUPP varchar2(40) NOT NULL,
  CONSTRAINT
    student_pk PRIMARY KEY (ID));
--rollback drop table STUDENTS;

-- sequences

--changeset ness:create_seq_ROLE_SEQ
CREATE SEQUENCE ROLE_SEQ START WITH 0 MINVALUE 0;
--rollback drop sequence ROLE_SEQ;

--changeset ness:create_seq_USERINFO_SEQ
CREATE SEQUENCE USERINFO_SEQ START WITH 0 MINVALUE 0;
--rollback drop sequence USERINFO_SEQ;

--changeset ness:create_seq_USERS_SEQ
CREATE SEQUENCE USERS_SEQ START WITH 0 MINVALUE 0;
--rollback drop sequence USERS_SEQ;