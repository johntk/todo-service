DROP TABLE IF EXISTS TODOS;

CREATE SEQUENCE TODO_SEQUENCE;

CREATE TABLE TODOS
(
    id         BIGINT PRIMARY KEY,
    user       VARCHAR(30),
    desc       VARCHAR(30),
    lastUpdated Date,
    done       VARCHAR(5)
);

INSERT INTO TODOS VALUES (TODO_SEQUENCE.nextval, 'user1', 'Test Description', sysdate, 'false');
