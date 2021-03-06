//RFC7
SELECT * 
FROM OPERACIONES 
WHERE FECHA BETWEEN '01/01/2020' AND '30/11/2020' AND TIPO_ID = 1 AND NUMERO_ID = 1010090987;

SELECT * 
FROM OPERACIONES 
WHERE FECHA BETWEEN '01/01/2020' AND '30/11/2020' AND NUMERO_CUENTA = 2543678556;

SELECT * 
FROM OPERACIONES 
WHERE FECHA BETWEEN '01/01/2020' AND '30/11/2020' AND VALOR BETWEEN 2000000 AND 5000000;

SELECT * 
FROM OPERACIONES 
WHERE FECHA BETWEEN '01/01/2020' AND '30/11/2020' AND TIPO_OPERACION = 2;

//RFC8
SELECT * 
FROM OPERACIONES 
WHERE FECHA BETWEEN '01/01/2020' AND '30/11/2020' AND TIPO_ID <> 1 AND NUMERO_ID <> 1010090987;

SELECT * 
FROM OPERACIONES 
WHERE FECHA BETWEEN '01/01/2020' AND '30/11/2020' AND NUMERO_CUENTA <> 2543678556;

SELECT * 
FROM OPERACIONES 
WHERE FECHA BETWEEN '01/01/2020' AND '30/11/2020' AND VALOR NOT BETWEEN 2000000 AND 5000000;

SELECT * 
FROM OPERACIONES 
WHERE FECHA BETWEEN '01/01/2020' AND '30/11/2020' AND TIPO_OPERACION <> 2;

//RFC9
SELECT * 
FROM OPERACIONES O, CUENTAS C
WHERE O.TIPO_OPERACION = 1 AND O.VALOR > 250000
                            AND O.TIPO_ID = C.TIPO_ID
                            AND O.NUMERO_ID = C.NUMERO_ID
                            AND C.TIPO_CUENTA = 1;

//RFC10
SELECT *
FROM USUARIOS U, OPERACIONES O, (SELECT * FROM
                                (SELECT TIPO_ID, NUMERO_ID 
                                FROM OPERACIONES 
                                WHERE ID_OFICINA = 1) NATURAL INNER JOIN 
                                (SELECT TIPO_ID, NUMERO_ID 
                                FROM OPERACIONES 
                                WHERE ID_OFICINA = 2) 
                                )J
WHERE U.TIPO_ID = J.TIPO_ID AND 
        U.NUMERO_ID = J.NUMERO_ID AND 
        O.TIPO_ID = J.TIPO_ID AND 
        O.NUMERO_ID = J.NUMERO_ID;