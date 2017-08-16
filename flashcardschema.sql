/* Database script for flashcard database */


/*Drop database if it exists */
drop user flashcard cascade;

/* create databse */
create user flashcard
identified by p4ssw0rd
default tablespace users
temporary tablespace temp
quota 10m on users;

grant connect to flashcard;
grant resource to flashcard;
grant create session to flashcard;
grant create table to flashcard;
grant create view to flashcard;

create table flashcard.flashcard
(
  flashcardid number(10) primary key,
  question varchar2(256) not null unique,
  answer varchar2(256) not null
);

create sequence flashcard.flashcard_key;

create or replace trigger flashcard.flash_key_trigger
before insert or update on flashcard.flashcard
for each row
begin
  if INSERTING then
    select flashcard_key.nextVal into :new.flashcardid from dual;
  elsif UPDATING then
    select :old.flashcardid into :new.flashcardid from dual;
 end if;
end;
/

create or replace procedure flashcard.insertCard
(
  ques IN varchar2,
  ans IN varchar2
) as
begin
  insert into flashcard (question, answer) values(ques,ans);
end;
/

create or replace procedure flashcard.betterInsertCard
(
  ques IN varchar2,
  ans IN varchar2,
  id OUT number
) as
begin
  insert into flashcard (question, answer) values(ques,ans);
  select flashcardid into id from flashcard where question = ques and answer=ans;
end;
/