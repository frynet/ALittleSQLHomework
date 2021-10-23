# A little SQL Homework within bachelor education

### [ER-model](https://raw.githubusercontent.com/frynet/TheatreManager/master/img/ER-model.svg)

```SQL
dialect: PostgreSQL
database: create.sql join with generated fill.sql
```

## Queries:

<details>
  <summary>Найти актёров, которые будут не заняты в определённый промежуток времени.</summary>
  
  ```SQL
  DROP VIEW IF EXISTS R3;
  DROP VIEW IF EXISTS R2;
  DROP VIEW IF EXISTS R1;
  DROP VIEW IF EXISTS R0;

  CREATE VIEW R0 AS
      SELECT id_spec, _date
      FROM repertoires
      WHERE _date > '2021-09-25' AND _date < '2021-11-25';
  CREATE VIEW R1 AS
      SELECT DISTINCT t1.id_spec, id_actor
      FROM R0 t1 JOIN spectacles_actors t2
      ON t1.id_spec = t2.id_spec;
  CREATE VIEW R2 AS
      SELECT id
      FROM actors
      WHERE id NOT IN (
          SELECT id_actor FROM R1
      );
  CREATE VIEW R3 AS
      SELECT t1.id, name
      FROM R2 t2 INNER JOIN actors t1
      ON t1.id = t2.id;
                                              
  SELECT * FROM R3;
  ```
  
  example output: 
  | id 	| name         	|
  |:--:	|--------------	|
  |  2 	| Петров       	|
  |  6 	| Соколов      	|
  |  9 	| Андреев      	|
  | 20 	| Егоров       	|
  | 21 	| Волков       	|
  | 25 	| Тимофеев     	|
  | 27 	| Афанасьев    	|
  | 32 	| Виноградов   	|
  | 33 	| Кузьмин      	|
  | 37 	| Герасимов    	|
  | 41 	| Романов      	|
</details>
  
<details>
  <summary>Какие роли исполняли только одни актёры?</summary>
  
  ```SQL
  DROP VIEW IF EXISTS R1;
  DROP VIEW IF EXISTS R0;

  CREATE VIEW R0 AS
      SELECT id_role, COUNT(*) AS K
      FROM spectacles_roles_actors
      GROUP BY id_role HAVING COUNT(*) < 2;
  CREATE VIEW R1 AS
      SELECT t1.id, title
      FROM R0 INNER JOIN roless t1
      ON t1.id = id_role;
                                           
  SELECT * FROM R1;
  ```
  
  example output: 
  | id 	| title     	|
  |:--:	|-----------	|
  | 2  	| тигр      	|
  | 4  	| птица     	|
  | 13 	| помощник  	|
  | 30 	| водитель  	|
  | 38 	| жеребёнок 	|
  | 40 	| ягнёнок   	|
  | 41 	| сокол     	|
  | 49 	| дворецкий 	|
  | 51 	| канцлер   	|
</details>

<details>
  <summary>Найти спектакли, в которых заняты все актёры театра.</summary>
  
  ```SQL
  DROP VIEW IF EXISTS R3;
  DROP VIEW IF EXISTS R2;
  DROP VIEW IF EXISTS R1;
  DROP VIEW IF EXISTS R0;

  CREATE VIEW R0 AS
      SELECT COUNT(*) AS K
      FROM actors;
  CREATE VIEW R1 AS
      SELECT id_spec, COUNT(*) AS M
      FROM spectacles_actors
      GROUP BY id_spec HAVING COUNT(*) > 0;
  CREATE VIEW R2 AS
      SELECT id_spec
      FROM R1 INNER JOIN R0
      ON R1.M = R0.K;
  CREATE VIEW R3 AS
      SELECT id, title
      FROM R2 INNER JOIN spectacles
      ON id = id_spec;

  SELECT * FROM R3;
  ```
  
  example output: 
  | id 	| title                         	|
  |:--:	|-------------------------------	|
  | 9  	| Тёмный рыцарь                 	|
  | 3  	| Бен-Гур                       	|
  | 4  	| Телохранитель                 	|
  | 0  	| Властелин колец: Две крепости 	|
  | 8  	| Шестое чувство                	|
</details>
