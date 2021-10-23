# A little SQL Homework within bachelor educate

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
  | 45 	| Беляев       	|
  | 51 	| Гусев        	|
  | 53 	| Киселёв      	|
  | 58 	| Кондратьев   	|
  | 65 	| Савельев     	|
  | 70 	| Сидоров      	|
  | 78 	| Игнатьев     	|
  | 79 	| Комаров      	|
  | 83 	| Голубев      	|
  | 84 	| Пономарёв    	|
  | 87 	| Кириллов     	|
  | 92 	| Троицкий     	|
  | 97 	| Константинов 	|
  | 98 	| Воронин      	|
</details>
