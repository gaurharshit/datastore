CREATE TABLE data_store
(
    id                  INT AUTO_INCREMENT,
    key_name                 VARCHAR2(64) NOT NULL PRIMARY KEY,
    value               VARCHAR2(64) NOT NULL,
    creation_time   TIMESTAMP
);

COMMENT ON COLUMN data_store.id IS 'Identifier of the data';
COMMENT ON COLUMN data_store.key_name IS 'key';
COMMENT ON COLUMN data_store.value IS 'value';
COMMENT ON COLUMN data_store.creation_time IS 'Time of registration of value';

INSERT INTO data_store (key_name, value, creation_time)
VALUES ('harshit', 'gaur',
        {ts '2022-01-20 07:23:48.112'});

INSERT INTO data_store (key_name, value, creation_time)
VALUES ('foobar', 'barfoo', {ts '2022-01-20 07:23:48.112'});

INSERT INTO data_store (key_name, value, creation_time)
VALUES ('delta', 'azerion', {ts '2022-01-20 07:23:48.112'});