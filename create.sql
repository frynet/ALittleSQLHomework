DROP TABLE IF EXISTS sale_tickets;
DROP TABLE IF EXISTS hall;
DROP TABLE IF EXISTS spectacles_roles_actors;
DROP TABLE IF EXISTS spectacles_roles;
DROP TABLE IF EXISTS spectacles_actors;
DROP TABLE IF EXISTS repertoires;
DROP TABLE IF EXISTS spectacles;
DROP TABLE IF EXISTS roless;
DROP TABLE IF EXISTS actors;


CREATE TABLE IF NOT EXISTS actors
(
    id   SERIAL       NOT NULL
        CONSTRAINT k1 PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE roless
(
    id    SERIAL       NOT NULL
        CONSTRAINT k2 PRIMARY KEY,
    title VARCHAR(100) NOT NULL
);

CREATE TABLE spectacles
(
    id    SERIAL       NOT NULL
        CONSTRAINT k3 PRIMARY KEY,
    title VARCHAR(100) NOT NULL
);

CREATE TABLE spectacles_roles
(
    id_spec INT     NOT NULL,
    id_role INT     NOT NULL,
    main    BOOLEAN NOT NULL,
    CONSTRAINT k4 PRIMARY KEY (id_spec, id_role),
    CONSTRAINT c1 FOREIGN KEY (id_role) REFERENCES roless (id) ON DELETE CASCADE,
    CONSTRAINT c2 FOREIGN KEY (id_spec) REFERENCES spectacles (id) ON DELETE CASCADE
);

CREATE TABLE spectacles_actors
(
    id_spec  INT NOT NULL,
    id_actor INT NOT NULL,
    CONSTRAINT k5 PRIMARY KEY (id_actor, id_spec),
    CONSTRAINT c3 FOREIGN KEY (id_spec) REFERENCES spectacles (id) ON DELETE CASCADE,
    CONSTRAINT c4 FOREIGN KEY (id_actor) REFERENCES actors (id) ON DELETE CASCADE
);

CREATE TABLE spectacles_roles_actors
(
    id_spec  INT NOT NULL,
    id_role  INT NOT NULL,
    id_actor INT NOT NULL,
    CONSTRAINT k6 PRIMARY KEY (id_spec, id_role, id_actor),
    CONSTRAINT c6 FOREIGN KEY (id_spec, id_actor) REFERENCES spectacles_actors (id_spec, id_actor) ON DELETE CASCADE,
    CONSTRAINT c7 FOREIGN KEY (id_actor) REFERENCES actors (id) ON DELETE CASCADE,
    CONSTRAINT c8 FOREIGN KEY (id_role) REFERENCES roless (id) ON DELETE CASCADE,
    CONSTRAINT c9 FOREIGN KEY (id_spec, id_role) REFERENCES spectacles_roles (id_spec, id_role) ON DELETE CASCADE
);

CREATE TABLE repertoires
(
    id_spec INT  NOT NULL,
    _date   DATE NOT NULL,
    price   INT  NOT NULL,
    CONSTRAINT k7 PRIMARY KEY (id_spec, _date),
    CONSTRAINT c5 FOREIGN KEY (id_spec) REFERENCES spectacles (id) ON DELETE CASCADE
);

CREATE TABLE hall
(
    _row    INT NOT NULL,
    _column INT NOT NULL,
    CONSTRAINT k8 PRIMARY KEY (_row, _column)
);

CREATE TABLE sale_tickets
(
    id_ticket SERIAL NOT NULL
        CONSTRAINT K9 PRIMARY KEY,
    id_spec   INT    NOT NULL,
    _date     DATE   NOT NULL,
    price     INT    NOT NULL,
    _row      INT    NOT NULL,
    _col      INT    NOT NULL,
    CONSTRAINT k10 UNIQUE (id_spec, _date, _row, _col),
    CONSTRAINT c11 FOREIGN KEY (_row, _col) REFERENCES hall (_row, _column) ON DELETE RESTRICT,
    CONSTRAINT c10 FOREIGN KEY (id_spec, _date) REFERENCES repertoires (id_spec, _date) ON DELETE RESTRICT
);