DECLARE SUB menu5 (axz!)
DECLARE SUB ejer5 ()
DECLARE SUB ejer7 ()
DECLARE SUB ejer3 ()
DECLARE SUB ejer1 ()
DECLARE SUB marco4 ()
DECLARE SUB ejercicio1 ()
DECLARE SUB ejemplo2 ()
DECLARE SUB suma ()
DECLARE SUB producto ()
DECLARE SUB miscelanea ()
DECLARE SUB fecha ()
DECLARE SUB tiemp (inicio!)
DECLARE SUB pintura3 ()
DECLARE SUB ejercicio5 ()
DECLARE SUB dproduc ()
DECLARE SUB dsuma ()
DECLARE SUB pintura ()
DECLARE SUB menu (texto$(), nop!, a$, teclas$)
DECLARE SUB despedida (q%)
DECLARE SUB marco2 ()
DECLARE SUB coeficientes (b!, c!, d!, e!, f!, g!, h!, i!, j!, n!)
DECLARE SUB aleatorios (a!, n!)
DECLARE SUB filtro (acep$, reng!, colu%, cade$)
DECLARE SUB DATOS ()
DECLARE SUB VE4 ()
DECLARE SUB VE5 ()
DECLARE SUB VE6 ()
DECLARE SUB VE7 ()
DECLARE SUB VE8 ()
DECLARE SUB VE9 ()
DECLARE SUB ayuda ()
DECLARE SUB ejercicio3 ()
DECLARE SUB espera (tiempo!)
DECLARE SUB ejercicio2 ()
DECLARE SUB positivos ()
DECLARE SUB diferentes ()
DECLARE SUB ejemplo4 ()
DECLARE SUB ordenejerc ()
DECLARE SUB ejercicio7 ()
DECLARE SUB iguales ()
DECLARE SUB ppositivos ()
DECLARE SUB momento (a$)
DECLARE SUB AE1e ()
DECLARE SUB AE3e ()
DECLARE SUB AE5e ()
DECLARE SUB AE7e ()
DECLARE SUB AE9e ()

  '========================================================================
  '=                 DECLARACION DE VARIABLES GLOBALES                    =
  '========================================================================               
 
                       DIM SHARED TEX$(10)
                     
                       COMMON SHARED bue, mal   'BUE contador de ejercicios
                                                'buenos
                       DIM SHARED cad$(1)       'MAL contador de ejercicios
                                                'malos
                       DIM SHARED lista$        'de los alumnos
                       DIM SHARED alumno$, prim

                       DIM SHARED bueno(20)    'espacio para guardar los
                       DIM SHARED malo(20)    ' ejercicios buenos y malos
 
  '==========================================================================


   CALL pintura3
     
   LOCATE 5, 11
   PRINT "SOFTWARE EDUCATIVO"

'                                                 L
'Utilzaci¢n de la sentencia LINE
LINE (15, 65)-(51, 135), 2, BF'Dibujar un rectangulo coloreado
   'Dibujar la letra L dentro  del rectangulo
   READ x, y
   PSET (x, y)
   FOR n = 1 TO 2
      READ x, y
      LINE -(x, y)
   NEXT n
DATA 20, 67, 20, 133, 46,133
   CALL espera(1)

'                                                 U

   LINE (61, 65)-(97, 135), 2, BF'Dibujar un rectangulo coloreado
   'Dibujar la letra U dentro  del rectangulo
   READ x, y
   PSET (x, y)
   FOR n = 1 TO 3
      READ x, y
      LINE -(x, y)
   NEXT n
   DATA 66, 67, 66, 133, 92, 133, 92, 67
   CALL espera(1)

  '                                             D
   LINE (107, 65)-(143, 135), 2, BF'Dibujar un rectangulo coloreado
   'Dibujar la letra D dentro  del rectangulo
   READ x, y
   PSET (x, y)
   FOR n = 1 TO 5
      READ x, y
      LINE -(x, y)
   NEXT n
   DATA 109, 67, 138, 67, 138, 133, 109, 133, 112, 133, 112, 67
   CALL espera(1)

  '                                             W
   LINE (153, 65)-(189, 135), 2, BF'Dibujar un rectangulo coloreado
   'Dibujar la letra W dentro  del rectangulo
   READ x, y
   PSET (x, y)
   FOR n = 1 TO 4
      READ x, y
      LINE -(x, y)
   NEXT n
   DATA 155, 67, 155, 133, 171, 67,187,133,187,67
   CALL espera(1)

   '                                            I
   LINE (199, 65)-(211, 135), 2, BF'Dibujar un rectangulo coloreado
   'Dibujar la letra I dentro  del rectangulo
   READ x, y
   PSET (x, y)
   FOR n = 1 TO 1
      READ x, y
      LINE -(x, y)
   NEXT n
   DATA  205,67,205, 133
   CALL espera(1)

    '                                           N

   LINE (221, 65)-(257, 135), 2, BF'Dibujar un rectangulo coloreado
   'Dibujar la letra A dentro  del rectangulo
   READ x, y
   PSET (x, y)
   FOR n = 1 TO 3
      READ x, y
      LINE -(x, y)
   NEXT n
   DATA 223, 133, 223, 67, 255, 133, 255, 67
   CALL espera(1)

'                                               G
   LINE (267, 65)-(303, 135), 2, BF'Dibujar un rectangulo coloreado
   'Dibujar la letra G dentro  del rectangulo
   READ x, y
   PSET (x, y)
   FOR n = 1 TO 6
      READ x, y
      LINE -(x, y)
   NEXT n
   DATA 301, 69, 301, 67, 269, 67, 269, 133, 301, 133, 301, 113, 281, 113
   CALL espera(1)

   LOCATE 20, 15
   PRINT "BIENVENIDO"
   LOCATE 22, 5
   PRINT "OPERACIONES CON NUMEROS ENTEROS"

  
   LOCATE 21, 28
   PRINT "2010-ver 0.2"
    CALL espera(2)
   CALL menu5(axz)

  IF axz <= 0 THEN
    CLS
  ELSE

     SCREEN 2
     CALL pintura
     CALL DATOS
     CALL ayuda

  END IF

SUB aleatorios (a, n)
   
    RANDOMIZE TIMER
    a = INT(n * RND)

END SUB

SUB ayuda
 prim = TIMER
 DO
   CLS
   TEX$(0) = " TUTORIAL DE OPERACIONES CON NUMEROS ENTEROS"
   TEX$(1) = "1) OPERACIONES CON NUMEROS DE SIGNOS IGUALES"
   TEX$(2) = "2) OPERACIONES CON NUMEROS DE SIGNOS DIFERENTES"
   TEX$(3) = "3) SUMAS DE NUMEROS ENTEROS"
   TEX$(4) = "4) PRODUCTO DE NUMEROS ENTEROS"
   TEX$(5) = "5) MISCELANEA"
   TEX$(6) = "6) TERMINAR"
   TEX$(10) = "ELIGE UNA OPCION: "
   CALL menu(TEX$(), 6, a$, "123456")
SELECT CASE a$
        CASE "1"
           CALL iguales
        CASE "2"
           CALL diferentes
        CASE "3"
           CALL suma
        CASE "4"
           CALL producto
        CASE "5"
           CALL miscelanea
        CASE "6"
          CALL despedida(q%)
    END SELECT
 LOOP UNTIL q%
END SUB

SUB coeficientes (b, c, d, e, f, g, h, i, j, n)
     CALL aleatorios(b, 10)
     CALL aleatorios(c, 10)
     CALL aleatorios(d, 10)
     CALL aleatorios(e, 10)
     CALL aleatorios(f, 10)
     CALL aleatorios(g, 10)
     CALL aleatorios(h, 10)
     CALL aleatorios(i, 10)
     CALL aleatorios(j, 10)
END SUB

SUB DATOS
   CLS
   CALL marco2
   CALL fecha
   LOCATE 4, 30
   PRINT "Este software es complemento del libro"
   LOCATE 5, 30
   PRINT "TITULO: ALGEBRA"
   LOCATE 6, 30
   PRINT "AUTOR: LUDWING J. SALAZAR"
   LOCATE 7, 30
   PRINT "EDITORIAL: PUBLICACIONES CULTURALES"
   LOCATE 8, 10
   PRINT "CORREO ELECTRONICO  juegosmatematicos@hotmail.com"
   LOCATE 10, 30
   PRINT "Programas protegidos por el derecho de autor"
   LOCATE 13, 10
   PRINT "OPERACIONES CON NUMEROS ENTEROS"
   LOCATE 16, 10
   PRINT "TECLEA LO QUE SE TE PIDE"
   LOCATE 18, 20
   PRINT "ALUMNO: "
   LOCATE 18, 29
   INPUT "", alumno$
   alumno$ = UCASE$(alumno$)
   LOCATE 20, 20
   PRINT "NUMERO DE LISTA: "
   LOCATE 20, 38
   INPUT "", lista$
   lista$ = UCASE$(lista$)
   LOCATE 22, 20
   PRINT "GRUPO: "
   LOCATE 22, 28
   INPUT "", grupo$
   grupo$ = UCASE$(grupo$)


END SUB

SUB despedida (q%)
  CLS
  CALL pintura
  CALL fecha
  LOCATE 5, 10
  PRINT "ADIOS"
  LOCATE 5, 20
  PRINT alumno$
  LOCATE 3, 50
  PRINT "L U D W I N G"
  LOCATE 7, 10
  PRINT "NUMERO DE LISTA: "; lista$
  LOCATE 9, 10
  PRINT "GRUPO:  "; grupo$
   IF (bue + mal) = 0 THEN
   ELSE
      LOCATE 15, 17
      PRINT "CALIFICACION "; (bue / (bue + mal)) * 10
   END IF

  LOCATE 17, 17
  PRINT "NUMERO ACIERTOS:  "; bue

  LOCATE 19, 17
  PRINT "NUMERO DE ERRORES COMETIDOS: "; mal
  CALL tiemp(prim)
  CALL marco4
  q% = 1

 



END SUB

SUB diferentes
 DO
   CLS
   TEX$(0) = "OPERACIONES CON SIGNOS DIFERENTES"
   TEX$(1) = "1) SUMA"
   TEX$(2) = "2) PRODUCTO"
   TEX$(3) = "5) REGRESAR AL MENU ANTERIOR"
   TEX$(10) = "ELIGE UNA OPCION: "
   CALL menu(TEX$(), 3, a$, "125")
SELECT CASE a$
        CASE "1"
           CALL dsuma
        CASE "2"
           CALL dproduc
        CASE "5"
           q% = 1
    END SELECT
 LOOP UNTIL q%
END SUB

SUB dproduc
    CALL pintura
    CALL marco2
    LOCATE 3, 6
    PRINT " REGLA 4"
    LOCATE 5, 6
    PRINT "Cuando se multiplican dos n£meros de diferente signo, el"
    LOCATE 7, 6
    PRINT "producto es de signo negativo y los dos n£meros se multiplican."
    LOCATE 11, 6
    PRINT "Ejemplo:"
       DO
        CALL coeficientes(b, c, d, e, f, g, h, i, j, 10)
      LOOP UNTIL b > 0 AND c > 0
        LOCATE 11, 30
            PRINT "(-"; b; ")("; c; ") = "; (-b) * (c)
    CALL ejercicio5

END SUB

SUB dsuma
    CALL pintura
    CALL marco2
    LOCATE 3, 6
    PRINT " REGLA 3"
    LOCATE 5, 6
    PRINT "Cuando la suma de los n£meros son de diferente signo, se observa"
    LOCATE 7, 6
    PRINT "cual es el mayor de ellos sin tomar encuenta el signo y se resta"
    LOCATE 9, 6
    PRINT "del mayor el menor y al resultado se le pone el signo del mayor."
    LOCATE 11, 6
    PRINT "Ejemplo:"
        CALL coeficientes(b, c, d, e, f, g, h, i, j, 10)
        x = -b + c
            LOCATE 11, 30
            PRINT "-"; b; "+"; c; "= "; x

    CALL ejercicio3

END SUB

SUB ejemplo1
       DO
        CALL coeficientes(b, c, d, e, f, g, h, i, j, 10)
      LOOP UNTIL b > 0 AND c > 0
        LOCATE 11, 30
            PRINT "(-"; b; ")("; c; ") = "; (-b) * (c)

END SUB

SUB ejemplo4
        CALL coeficientes(b, c, d, e, f, g, h, i, j, 10)
        x = b + c * d
            LOCATE 12, 30
            PRINT b; "+"; c; "x"; d; "= "; x
END SUB

SUB ejemplo5
    CALL coeficientes(b, c, d, e, f, g, h, i, j, 10)
     x = (b + c) + (d + e)
            LOCATE 18, 20
        PRINT "("; b; "+"; c; ")"; "+"; "("; d; "+"; e; ") = "; x
        LOCATE 20, 30
        PRINT (b + c); "+"; (d + e); "="; x
                LOCATE 22, 30
                PRINT (b + c) + (d + e); "="; x
END SUB

SUB ejemplo6
    CALL coeficientes(b, c, d, e, f, g, h, i, j, 10)
    x = b * (c + (d + e * (f - g)))
    LOCATE 15, 20                 'AE0
    PRINT b; "{"; c; "+"; "["; d; "+"; e; "("; f; "-"; g; ")"; "]"; "} = "
    CALL espera(1)
        LOCATE 17, 20
        PRINT b; "{"; c; "+"; "["; d; "+"; e; "("; f - g; ")"; "]"; "} = "
        CALL espera(1)
      IF e * (f - g) > 0 THEN
        LOCATE 19, 20
        PRINT b; "{"; c; "+"; "["; d; "+"; e * (f - g); "]"; "} = "
      ELSE
        IF e * (f - g) = 0 THEN
            LOCATE 19, 20
            PRINT b; "{"; c; "+"; "["; d; "+"; e * (f - g); "]"; "} = "
        ELSE                    'ff'
          LOCATE 19, 20
          PRINT b; "{"; c; "+"; "["; d; e * (f - g); "]"; "} = "
        END IF
      END IF
        LOCATE 17, 48
        PRINT "SE ANULA EL PARENTESIS"
   IF d + e * (f - g) < 0 THEN   'rthrththrh
                LOCATE 21, 20
                PRINT b; "{"; c; "+"; "["; d + e * (f - g); "]"; "} = "
          ELSE
              LOCATE 21, 20
              PRINT b; "{"; c; "+"; d + e * (f - g); "} = "
              END IF
         LOCATE 21, 48
         PRINT "SE ANULA EL CORCHETE"
                LOCATE 23, 20
                PRINT b; "{"; c + (d + e * (f - g)); "} = "; x
         LOCATE 23, 48
         PRINT "SE ANULA LA LLAVE"
END SUB

SUB ejer1


 CLS
   DO
        CALL aleatorios(b, 10)
        CALL aleatorios(c, 10)
   LOOP UNTIL b > 0 AND c > 0
        zx = 0
          
            LOCATE 16, 18
            PRINT "Realiza la siguiente operaci¢n"
    
     CALL aleatorios(a, 10)
        
     SELECT CASE a
      CASE IS < 5
            LOCATE 18, 30
            PRINT b; "+"; c; " =    "
           x = b + c
      CASE IS >= 5
            LOCATE 18, 30
            PRINT -b; "-"; c; " =    "
           x = -b - c
      END SELECT

 DO
            zx = zx + 1
            CALL filtro("-1234567890", 18, 42, cad$(1))'cadena long max.
            z = VAL(cad$(1))
        IF z = x THEN
              bue = bue + 1
              LOCATE 6, 20
              PRINT "MALOS"; mal
              LOCATE 6, 50
              PRINT "BUENOS "; bue
              LOCATE 12, 32
              PRINT "MUY BIEN"
              LOCATE 22, 25
              PRINT SPACE$(29)

              zx = 2
        ELSE
              LOCATE 22, 25
              PRINT "INCORRECTO INTENTALO DE NUEVO"
              LOCATE 18, 42
              PRINT SPACE$(7)

            IF zx >= 2 THEN
                      mal = mal + 1
                      LOCATE 6, 20
                      PRINT "MALOS"; mal
                      LOCATE 6, 50
                      PRINT "BUENOS "; bue
                      LOCATE 22, 25
                      PRINT SPACE$(37)

                      LOCATE 7, 30
                      PRINT " S O L U C I O N "
                     
                      LOCATE 22, 25
                      PRINT SPACE$(29)
                      LOCATE 10, 30
                 IF x >= 0 THEN
                      PRINT b; "+"; c; " = "; x
                 ELSE
                      PRINT -b; -c; " = "; x
                 END IF
            END IF
        
       END IF

LOOP UNTIL zx = 2
  
END SUB

SUB ejer3
CLS
  DO
        CALL aleatorios(b, 10)
        CALL aleatorios(c, 10)
  LOOP UNTIL b > 0 AND c > 0 AND b <> c
        zx = 0
        x = -b + c
            LOCATE 18, 18
            PRINT "Realiza la siguiente operaci¢n"
         
            CALL aleatorios(a, 10)
         az = a
     SELECT CASE az
      CASE IS < 5
            LOCATE 20, 30
            PRINT -b; "+"; c; " =    "
           x = -b + c
      CASE IS >= 5
            LOCATE 20, 30
            PRINT b; -c; " =    "
           x = b - c
      END SELECT
 DO
        zx = zx + 1
        no = no + 1
            CALL filtro("-1234567890", 20, 39, cad$(1))'cadena long max.
            z = VAL(cad$(1))
         
        IF z = x THEN
              bue = bue + 1
              LOCATE 6, 20
              PRINT "MALOS"; mal
              LOCATE 6, 50
              PRINT "BUENOS "; bue
              LOCATE 12, 32
              PRINT "MUY BIEN"
              LOCATE 22, 25
              PRINT SPACE$(29)

              zx = 2
        ELSE
              LOCATE 20, 39
              PRINT SPACE$(7)
              LOCATE 22, 25
              PRINT "INCORRECTO INTENTALO DE NUEVO"

            IF zx >= 2 THEN
                      mal = mal + 1
                      LOCATE 6, 20
                      PRINT "MALOS"; mal
                      LOCATE 6, 50
                      PRINT "BUENOS "; bue


                      LOCATE 7, 30
                      PRINT " S O L U C I O N "
                      LOCATE 22, 25
                      PRINT SPACE$(50)
                IF x = -b + c THEN
                      LOCATE 10, 30
                      PRINT "-"; b; "+"; c; "= "; x
                ELSE
                      LOCATE 10, 30
                      PRINT b; -c; "= "; x
                END IF
            END IF
       
       END IF

LOOP UNTIL zx = 2
  
  
  
  
   LOCATE 23, 6
   PRINT SPACE$(23)


END SUB

SUB ejer5
 CLS
  DO
        CALL aleatorios(b, 10)
        CALL aleatorios(c, 10)
  LOOP UNTIL b > 0 AND c > 0 AND b <> c
        zx = 0
        x = (-b) * (c)
            LOCATE 18, 18
            PRINT "Realiza la siguiente operaci¢n"
           
            CALL aleatorios(a, 10)
     SELECT CASE a

      CASE IS < 5
            LOCATE 20, 30
            PRINT "("; -b; ")("; c; ") = "
      CASE IS >= 5
            LOCATE 20, 30
            PRINT "("; b; ")("; -c; ") =  "
      END SELECT
            x = (-b) * (c)

 DO
        zx = zx + 1
        no = no + 1
            CALL filtro("-1234567890", 20, 43, cad$(1)) 'cadena long max.
            z = VAL(cad$(1))
        IF z = x THEN
              bue = bue + 1
              LOCATE 6, 20
              PRINT "MALOS"; mal
              LOCATE 6, 50
              PRINT "BUENOS "; bue
              LOCATE 12, 32
              PRINT "MUY BIEN"
              LOCATE 22, 25
              PRINT SPACE$(29)

              zx = 2
        ELSE
              LOCATE 20, 43
              PRINT SPACE$(7)
              LOCATE 22, 25
              PRINT "INCORRECTO INTENTALO DE NUEVO"

            IF zx >= 2 THEN
                      mal = mal + 1
                      LOCATE 6, 20
                      PRINT "MALOS"; mal
                      LOCATE 6, 50
                      PRINT "BUENOS "; bue
                      LOCATE 7, 30
                      PRINT " S O L U C I O N "
                      LOCATE 22, 25
                      PRINT SPACE$(30)

               
            LOCATE 10, 30
         SELECT CASE a
      CASE IS < 5
            PRINT "("; -b; ")("; c; ") = "; x
      CASE IS >= 5
            PRINT "("; b; ")("; -c; ") = "; x
      END SELECT
               
               
            END IF
      
       END IF

LOOP UNTIL zx = 2



END SUB

SUB ejer7
CLS
 DO
        CALL aleatorios(b, 10)
        CALL aleatorios(c, 10)
  LOOP UNTIL b > 0 AND c > 0
        zx = 0
            LOCATE 18, 18
            PRINT "Realiza la siguiente operaci¢n"
            CALL aleatorios(a, 10)
    
     SELECT CASE a
      CASE IS < 5
            LOCATE 20, 30
            PRINT "("; b; ")("; c; ") = "
           x = b * c
           x1 = 0
      CASE IS >= 5
            LOCATE 20, 30
            PRINT "("; -b; ")("; -c; ") =    "
           x = b * c
           x1 = 1
      END SELECT
 DO
            zx = zx + 1
            CALL filtro("-1234567890", 20, 43, cad$(1))'cadena long max.
            z = VAL(cad$(1))
        IF x = z THEN
              bue = bue + 1
              LOCATE 6, 20
              PRINT "MALOS"; mal
              LOCATE 6, 50
              PRINT "BUENOS "; bue
              LOCATE 12, 32
              PRINT "MUY BIEN"
              LOCATE 22, 25
              PRINT SPACE$(29)

              zx = 2
        ELSE
              LOCATE 20, 42
              PRINT SPACE$(7)
              LOCATE 22, 25
              PRINT "INCORRECTO INTENTALO DE NUEVO"
            IF zx >= 2 THEN
                      mal = mal + 1
                      LOCATE 6, 20
                      PRINT "MALOS"; mal
                      LOCATE 6, 50
                      PRINT "BUENOS "; bue
                      LOCATE 7, 30
                      PRINT " S O L U C I O N "
                      LOCATE 22, 25
                      PRINT SPACE$(29)

                      LOCATE 10, 30
                   IF x1 = 0 THEN
                      PRINT "("; b; ")("; c; ") = "; x
                   ELSE
                      PRINT "("; -b; ")("; -c; ") = "; x
                   END IF
            END IF
       END IF
LOOP UNTIL zx = 2

END SUB

SUB ejercicio1
  inicio = TIMER
 
 
  DO
            n1 = 1
        LOCATE 16, 30
        PRINT SPACE$(36)
  
 LOOP UNTIL (n1 <= 2) AND (n1 > 0)
       
        n = 10 ^ n1
  WHILE (a$ <> "n") AND (a$ <> "N")
 




   DO
        CALL aleatorios(b, n)
        CALL aleatorios(c, n)
   LOOP UNTIL b > 0 AND c > 0
        zx = 0
           
            LOCATE 18, 18
            PRINT "Realiza la siguiente operaci¢n"
            LOCATE 20, 30
            PRINT SPACE$(8)
     CALL aleatorios(a, 10)
         
     SELECT CASE a
      CASE IS < 5
            LOCATE 20, 30
            PRINT b; "+"; c; " =    "
           x = b + c
      CASE IS >= 5
            LOCATE 20, 30
            PRINT -b; "-"; c; " =    "
           x = -b - c
      END SELECT
 DO
            zx = zx + 1
            CALL filtro("-1234567890", 20, 40 + 2 * n1, cad$(1))'cadena long max.
            z = VAL(cad$(1))
        IF z = x THEN
              bue = bue + 1
              LOCATE 20, 40 + 2 * n1
              PRINT SPACE$(7)
              LOCATE 16, 20
              PRINT "MALOS"; mal
              LOCATE 16, 50
              PRINT "BUENOS "; bue
              LOCATE 22, 25
              PRINT SPACE$(29)
              LOCATE 22, 32
              PRINT "MUY BIEN"
              zx = 2
        ELSE
              LOCATE 20, 40 + 2 * n1
              PRINT SPACE$(5)
              LOCATE 22, 25
              PRINT "INCORRECTO INTENTALO DE NUEVO"

            IF zx >= 2 THEN
                      mal = mal + 1
                      LOCATE 16, 20
                      PRINT "MALOS"; mal
                      LOCATE 16, 50
                      PRINT "BUENOS "; bue
                      LOCATE 15, 30
                      PRINT " S O L U C I O N "
                      LOCATE 22, 25
                      PRINT SPACE$(29)
                      LOCATE 20, 30
                 IF x >= 0 THEN
                      PRINT b; "+"; c; " = "; x
                 ELSE
                      PRINT -b; -c; " = "; x
                 END IF
            END IF
         
       END IF

LOOP UNTIL zx = 2
   CALL tiemp(inicio)
   LOCATE 23, 6
   PRINT "¨DESEAS CONTINUAR S/N?"
   LOCATE 18, 18
   PRINT SPACE$(41)
   a$ = INPUT$(1)
  
   LOCATE 15, 30
   PRINT SPACE$(17)
   LOCATE 22, 32
   PRINT SPACE$(8)
   LOCATE 23, 6
   PRINT SPACE$(23)


  WEND

END SUB

SUB ejercicio3
 inicio = TIMER
  DO
       n1 = 1
        LOCATE 16, 30
        PRINT SPACE$(36)
 
 LOOP UNTIL (n1 <= 2) AND (n1 > 0)
      
        n = 10 ^ n1
  WHILE (a$ <> "n") AND (a$ <> "N")
  DO
        CALL aleatorios(b, n)
        CALL aleatorios(c, n)
  LOOP UNTIL b > 0 AND c > 0 AND b <> c
        zx = 0
        x = -b + c
            LOCATE 18, 18
            PRINT "Realiza la siguiente operaci¢n"
            LOCATE 20, 30
            PRINT SPACE$(8)
            LOCATE 20, 30
          
     CALL aleatorios(a, 10)
         az = a
     SELECT CASE az
      CASE IS < 5
            LOCATE 20, 30
            PRINT -b; "+"; c; " =    "
           x = -b + c
      CASE IS >= 5
            LOCATE 20, 30
            PRINT b; -c; " =    "
           x = b - c
      END SELECT
 DO
        zx = zx + 1
        no = no + 1
            CALL filtro("-1234567890", 20, 40 + 2 * n1, cad$(1))'cadena long max.
            z = VAL(cad$(1))
          
        IF z = x THEN
              bue = bue + 1
              LOCATE 20, 40 + 2 * n1
              PRINT SPACE$(7)
              LOCATE 16, 20
              PRINT "MALOS"; mal
              LOCATE 16, 50
              PRINT "BUENOS "; bue
              LOCATE 22, 25
              PRINT SPACE$(29)
              LOCATE 22, 32
              PRINT "MUY BIEN"
              zx = 2
        ELSE
              LOCATE 20, 40 + 2 * n1
              PRINT SPACE$(5)
              LOCATE 20, 42
              PRINT SPACE$(25)
              LOCATE 22, 25
              PRINT "INCORRECTO INTENTALO DE NUEVO"

            IF zx >= 2 THEN
                      mal = mal + 1
                      LOCATE 16, 20
                      PRINT "MALOS"; mal
                      LOCATE 16, 50
                      PRINT "BUENOS "; bue
                      LOCATE 15, 30
                      PRINT " S O L U C I O N "
                      LOCATE 22, 25
                      PRINT SPACE$(50)
                IF x = -b + c THEN
                      LOCATE 20, 30
                      PRINT "-"; b; "+"; c; "= "; x
                ELSE
                      LOCATE 20, 30
                      PRINT b; -c; "= "; x
                END IF
            END IF
        
       END IF

LOOP UNTIL zx = 2
   CALL tiemp(inicio)
   LOCATE 23, 6
   PRINT "¨DESEAS CONTINUAR S/N?"
   LOCATE 18, 18
   PRINT SPACE$(49)
   a$ = INPUT$(1)
   LOCATE 22, 32
   PRINT SPACE$(8)
   LOCATE 15, 30
   PRINT SPACE$(17)
   LOCATE 23, 6
   PRINT SPACE$(23)
 WEND
END SUB

SUB ejercicio4
inicio = TIMER
  WHILE (a$ <> "n") AND (a$ <> "N")

        CALL coeficientes(b, c, d, e, f, g, h, i, j, 10)
        zx = 0
        x = b + c * d
            LOCATE 18, 18
            PRINT "Realiza la siguiente operaci¢n"
            LOCATE 20, 30
            PRINT SPACE$(8)
            LOCATE 20, 30
            PRINT b; "+"; c; "x"; d; "= "
            
 DO
        zx = zx + 1
        no = no + 1
             
            CALL filtro("-1234567890", 20, 43, cad$(1))   'cadena long max.
            z = VAL(cad$(1))
        IF z = x THEN
              bue = bue + 1
              LOCATE 16, 20
              PRINT "MALOS"; mal
              LOCATE 16, 50
              PRINT "BUENOS "; bue
              LOCATE 22, 25
              PRINT SPACE$(29)
              LOCATE 22, 32
              PRINT "MUY BIEN"
              zx = 2
        ELSE
              LOCATE 20, 43
              PRINT SPACE$(5)
              LOCATE 22, 25
              PRINT "INCORRECTO INTENTALO DE NUEVO"

            IF zx >= 2 THEN
                      mal = mal + 1
                      LOCATE 16, 20
                      PRINT "MALOS"; mal
                      LOCATE 16, 50
                      PRINT "BUENOS "; bue
                      LOCATE 15, 30
                      PRINT " S O L U C I O N "
                      LOCATE 22, 25
                      PRINT SPACE$(29)
                      LOCATE 20, 30
                      PRINT b; "+"; c; "x"; d; "="; x

            END IF
        
       END IF

LOOP UNTIL zx = 2
CALL tiemp(inicio)
   LOCATE 23, 6
   PRINT "¨DESEAS CONTINUAR S/N?"
   LOCATE 18, 18
   PRINT SPACE$(41)
   a$ = INPUT$(1)
   LOCATE 15, 30
   PRINT SPACE$(17)
   LOCATE 22, 25
   PRINT SPACE$(29)
   LOCATE 23, 6
   PRINT SPACE$(23)
   LOCATE 20, 43
   PRINT SPACE$(5)


  WEND
END SUB

SUB ejercicio5
  inicio = TIMER
  DO
        n1 = 1
        LOCATE 16, 30
        PRINT SPACE$(36)

 LOOP UNTIL (n1 <= 2) AND (n1 > 0)
     
        n = 10 ^ n1
  WHILE (a$ <> "n") AND (a$ <> "N")
  DO
        CALL aleatorios(b, n)
        CALL aleatorios(c, n)
  LOOP UNTIL b > 0 AND c > 0 AND b <> c
        zx = 0
        x = (-b) * (c)
            LOCATE 18, 18
            PRINT "Realiza la siguiente operaci¢n"
            LOCATE 20, 30
            PRINT SPACE$(8)
            LOCATE 20, 30
     CALL aleatorios(a, 10)

     SELECT CASE a

      CASE IS < 5
            LOCATE 20, 30
            PRINT "("; -b; ")("; c; ") = "
      CASE IS >= 5
            LOCATE 20, 30
            PRINT "("; b; ")("; -c; ") =  "
           
      END SELECT
       x = (-b) * (c)

 DO
              LOCATE 20, 43
              PRINT SPACE$(9)

        zx = zx + 1
        no = no + 1
            CALL filtro("-1234567890", 20, 43, cad$(1)) 'cadena long max.
            z = VAL(cad$(1))
        IF z = x THEN
              bue = bue + 1
              LOCATE 16, 20
              PRINT "MALOS"; mal
              LOCATE 16, 50
              PRINT "BUENOS "; bue
              LOCATE 22, 25
              PRINT SPACE$(29)
              LOCATE 22, 32
              PRINT "MUY BIEN"
              zx = 2
        ELSE
              LOCATE 20, 45
              PRINT SPACE$(7)
              LOCATE 22, 25
              PRINT "INCORRECTO INTENTALO DE NUEVO"

            IF zx >= 2 THEN
                      mal = mal + 1
                      LOCATE 16, 20
                      PRINT "MALOS"; mal
                      LOCATE 16, 50
                      PRINT "BUENOS "; bue
                      LOCATE 15, 30
                      PRINT " S O L U C I O N "
                      LOCATE 22, 25
                      PRINT SPACE$(50)
     SELECT CASE a

      CASE IS < 5
            LOCATE 20, 30
            PRINT "("; -b; ")("; c; ") = "; x
      CASE IS >= 5
            LOCATE 20, 30
            PRINT "("; b; ")("; -c; ") = "; x
          
      END SELECT

            END IF
       
       END IF

LOOP UNTIL zx = 2
   CALL tiemp(inicio)
   LOCATE 23, 6
   PRINT "¨DESEAS CONTINUAR S/N?"
   LOCATE 18, 18
   PRINT SPACE$(49)
   a$ = INPUT$(1)
   LOCATE 22, 32
   PRINT SPACE$(8)
   LOCATE 15, 30
   PRINT SPACE$(17)
   LOCATE 23, 6
   PRINT SPACE$(23)
 WEND

END SUB

SUB ejercicio7
  inicio = TIMER
  n1 = 1
  DO
            n1 = 1
        LOCATE 16, 30
        PRINT SPACE$(32)
 LOOP UNTIL (n1 <= 2) AND (n1 > 0)
        n = 10 ^ n1
  WHILE (a$ <> "n") AND (a$ <> "N")
 DO
        CALL aleatorios(b, n)
        CALL aleatorios(c, n)
  LOOP UNTIL b > 0 AND c > 0
        zx = 0
            LOCATE 18, 18
            PRINT "Realiza la siguiente operaci¢n"
            LOCATE 20, 30
            PRINT SPACE$(24)
                CALL aleatorios(a, 10)
SELECT CASE a
      CASE IS < 5
            LOCATE 20, 30
            PRINT "("; b; ")("; c; ") = "
           x = b * c
           x1 = 0
      CASE IS >= 5
            LOCATE 20, 30
            PRINT "("; -b; ")("; -c; ") =    "
           x = b * c
           x1 = 1
      END SELECT
 DO
            zx = zx + 1
            CALL filtro("-1234567890", 20, 41 + 2 * n1, cad$(1))'cadena long max.
            z = VAL(cad$(1))
        IF x = z THEN
              bue = bue + 1
              LOCATE 20, 40 + 2 * n1
              PRINT SPACE$(7)
              LOCATE 16, 20
              PRINT "MALOS"; mal
              LOCATE 16, 50
              PRINT "BUENOS "; bue
              LOCATE 22, 25
              PRINT SPACE$(29)
              LOCATE 22, 32
              PRINT "MUY BIEN"
              zx = 2
        ELSE
              LOCATE 20, 40 + 2 * n1
              PRINT SPACE$(5)
              LOCATE 22, 25
              PRINT "INCORRECTO INTENTALO DE NUEVO"
            IF zx >= 2 THEN
                      mal = mal + 1
                      LOCATE 16, 20
                      PRINT "MALOS"; mal
                      LOCATE 16, 50
                      PRINT "BUENOS "; bue
                      LOCATE 15, 30
                      PRINT " S O L U C I O N "
                      LOCATE 22, 25
                      PRINT SPACE$(29)
                      LOCATE 20, 30
                   IF x1 = 0 THEN
                      PRINT "("; b; ")("; c; ") = "; x
                   ELSE
                      PRINT "("; -b; ")("; -c; ") = "; x
                   END IF
            END IF
       END IF
LOOP UNTIL zx = 2
   CALL tiemp(inicio)
   LOCATE 23, 6
   PRINT "¨DESEAS CONTINUAR S/N?"
   LOCATE 18, 18
   PRINT SPACE$(41)
   a$ = INPUT$(1)
   LOCATE 15, 30
   PRINT SPACE$(17)
   LOCATE 22, 32
   PRINT SPACE$(8)
   LOCATE 23, 6
   PRINT SPACE$(23)
  WEND
END SUB

SUB espera (tiempo)
   IF tiempo >= 56 THEN
      CLS
      LOCATE 10, 20
      PRINT "exceso de tiempo de espera"
      LOCATE 12, 20
      PRINT "asigne tiempo menor a 56 segundos"
      INPUT AA
   ELSE
      a$ = TIME$
      T$ = MID$(a$, 7)
      T = VAL(T$)
      t1 = T + tiempo
         IF t1 >= 60 THEN
            t1 = t1 - 60
         END IF
    DO
      TI$ = TIME$
      TE$ = MID$(TI$, 7)
      TE = VAL(TE$)
    LOOP UNTIL t1 = TE
   END IF



END SUB

SUB fecha
  v$ = DATE$

  n4 = INSTR(v$, "-")           'n4 es la posicion de el signo de resta
  v = LEN(v$)                   'v es la longitud de la cadena

  cadi$ = LEFT$(v$, n4 - 1)      'miembro izquierdo

      cadd$ = RIGHT$(v$, v - n4)  'miembro derecho

      n5 = INSTR(cadd$, "-")         'n5 posicion del signo - en la cadena derecha
      v1 = LEN(cadd$)                'longitud de la cadena

  cadc$ = LEFT$(cadd$, n5 - 1)   'miembro izquierdo

  cadd$ = RIGHT$(cadd$, v1 - n5) 'miembro derecho

  a$ = cadi$
  cadi$ = cadc$
  cadc$ = a$
   
        xa = VAL(cadc$)


    SELECT CASE xa
       CASE 1
        LOCATE 2, 5
        PRINT "M‚xico D. F. "; cadi$; " enero de "; cadd$
       CASE 2
        LOCATE 2, 5
        PRINT "M‚xico D. F. "; cadi$; " febrero de "; cadd$
       CASE 3
        LOCATE 2, 5
        PRINT "M‚xico D. F. "; cadi$; " marzo de "; cadd$
       CASE 4
        LOCATE 2, 5
        PRINT "M‚xico D. F. "; cadi$; " abril de "; cadd$
       CASE 5
        LOCATE 2, 5
        PRINT "M‚xico D. F. "; cadi$; " mayo de "; cadd$
       CASE 6
        LOCATE 2, 5
        PRINT "M‚xico D. F. "; cadi$; " junio de "; cadd$
       CASE 7
        LOCATE 2, 5
        PRINT "M‚xico D. F. "; cadi$; " julio de "; cadd$
       CASE 8
        LOCATE 2, 5
        PRINT "M‚xico D. F. "; cadi$; " agosto de "; cadd$
       CASE 9
        LOCATE 2, 5
        PRINT "M‚xico D. F. "; cadi$; " septiembre de "; cadd$
       CASE 10
        LOCATE 2, 5
        PRINT "M‚xico D. F. "; cadi$; " octubre de "; cadd$
       CASE 11
        LOCATE 2, 5
        PRINT "M‚xico D. F. "; cadi$; " noviembre de "; cadd$
       CASE 12
        LOCATE 2, 5
        PRINT "M‚xico D. F. "; cadi$; " diciembre de "; cadd$
     END SELECT



END SUB

DEFINT A-D
'
'recibe la ecuacion dada.
SUB filtro (acep$, reng, colu, cade$)
       cad$(i) = ""
       pp = 0
       col = colu

DO
   LOCATE reng, colu, 1, 7, 10          'hace visible el cursor
   conta = 0
      IF LEN(cad$(i)) <= 20 THEN        'pregunta el tama¤o de la cadena
   
      DO                             'si es menor de 20 entra y procesa.
        a$ = INKEY$                 'Este ciclo hace que no corra el
        LOOP WHILE a$ = ""             'programa hasta que se oprima una tecla.
        ax = ASC(a$)                   'se asigna en a el valor de un ASCII
        b$ = MID$(a$, 2)               'cadena para codigos ASCII extendidos
        IF INSTR(acep$, a$) <> 0 OR ax = 8 OR ax = 0 THEN
        SELECT CASE ax
          CASE 0        'Teclas de codigo extendido
                   SELECT CASE b$
                CASE "K"         ' Mueve el cursor a la izquierda
                      IF colu - 1 >= 20 THEN  'con las flechitas, dentro del
                         LOCATE reng, colu - 1, 1, 7, 10'espacio permitido
                         colu = colu - 1
                         LOCATE reng, colu, 1, 7, 10
                         conta = 0
                      ELSE
                         BEEP
                      END IF
              
                 CASE "M"             'Mueve el cursor a la derecha
                      IF colu <= LEN(cad$(i)) + 19 THEN  'con las flechitas,
                         LOCATE reng - 1, colu + 1 'dentro del espacio
                         colu = colu + 1             'permitido
                         LOCATE reng, colu, 1, 7, 10
                         conta = 0
                      ELSE
                         BEEP
                      END IF
              
                 CASE "S"       'SUPRIME UN CARACTER
                      IF colu >= 20 AND colu < 20 + LEN(cad$(i)) THEN
                         LOCATE reng, colu      'pregunta si el caracter esta en
                         cada$ = LEFT$(cad$(i), colu - 20)  'el espacio permitido
                         cadb$ = RIGHT$(cad$(i), 19 + LEN(cad$(i)) - colu)
                         cad$(i) = cada$ + cadb$
                         LOCATE reng, 20           'se separa la cadena original en
                         PRINT SPACE$(20)          'dos partes y luego se juntan
                         LOCATE reng, 20           ' y se imprimen en el mismo renglon
                         PRINT cad$(i)
                         LOCATE reng, colu, 1
                      ELSE
                         BEEP
                      END IF
              
                 CASE "R"        'INSERTAR UN CARACTER
                      IF LEN(cad$(i)) < 20 THEN      'pregunta si hay espacio en la cadena
                         conta = conta + 1           'abre un espacio donde coloca el cursor
                         LOCATE reng, colu, 1, 0, 9        'creando dos cadenas inserta el caracter
                         cada$ = LEFT$(cad$(i), colu - 20)    ' y suma las cadenas
                         cadb$ = RIGHT$(cad$(i), 20 + LEN(cad$(i)) - colu)
                         DO
                           aux$ = INKEY$
                         LOOP UNTIL INSTR(acep1$, aux$) <> 0
                      ELSE                    ' si la longitud de ladena es = 20 no inserta
                         BEEP                 ' e indica falta de espacio con un beep.
                      END IF
              
                 CASE "G"          'coloca cursor al inicio de la cadena
                      IF colu - 1 >= 20 THEN
                         LOCATE reng, 20, 1, 7, 10
                         colu = 20
                         conta = 0
                      ELSE
                         BEEP
                      END IF
              
                 CASE "O"  ' Coloca el cursor al final de la cadena
                      IF colu <= LEN(cad$(i)) + 19 THEN
                         LOCATE reng, LEN(cad$(i)) + 20
                         colu = LEN(cad$(i)) + 20
                         conta = 0
                      ELSE
                         BEEP
                      END IF
                     END SELECT
'error de rujtina de tragadato
                 CASE 8  ' borrado con back space
                   IF colu - 1 >= col THEN         'pregunta si la cadena tiene algun
                      IF colu >= 20 + LEN(cad$(i)) THEN   'caracter
                         LOCATE reng, colu - 1     '      'regresa el cursor e imprime un
                         PRINT " "                        'espacio en blanco.
                       IF colu - 1 <= 43 THEN
                          BEEP
                       ELSE
                         cad$(i) = LEFT$(cad$(i), LEN(cad$(i)) - 1) 'valida la cadena y disminuye
                       END IF
                         colu = colu - 1                            'su longitud en uno, igual que
                         LOCATE reng, colu, 1                 ' valida la posici¢n del cursor
                      END IF
                   ELSE
                      BEEP
                   END IF
       CASE ELSE       'para el caso de las teclas de edici¢n(n£meros,etc.
                IF LEN(cad$(i)) < 20 OR colu < 40 THEN   'pregunta la long. de la cadena
           SELECT CASE a                          'y posici¢n del cursor
             
                CASE 39, a TO 62 ' 58 NUMEROS Y DEMAS CARACTERES
                       LOCATE reng, colu, 1
                       PRINT a$;

                       IF conta > 0 THEN           'pregunta si se oprimio una tecla
                          cad$(i) = cada$ + a$ + cadb$  'especial para modificar la cadena
                          LOCATE reng, colu - LEN(cada$), 1
                          PRINT SPACE$(20)
                          LOCATE reng, colu - LEN(cada$), 1
                          PRINT cad$(i)
                          LOCATE reng, colu, 1, 7, 10
                          colu = colu - 1
                          conta = 0                 'y posicion del cursor
                       ELSE
                           IF colu < 20 + LEN(cad$(i)) THEN
                             cad$(i) = LEFT$(cad$(i), colu - 20) + a$ + RIGHT$(cad$(i), 19 + LEN(cad$(i)) - colu)
                           ELSE
                             cad$(i) = cad$(i) + a$     'incrementa la cadena en un caracter
                             conta = 0
                           END IF
                       END IF
                       colu = colu + 1
                  
                CASE 88, 89, 120, 121    '73, 105  I,i
                       a$ = LCASE$(a$)        'convierte a minusculas
                       LOCATE reng, colu, 1
                       PRINT a$;
                       IF conta > 0 THEN
                          cad$(i) = cada$ + a$ + cadb$
                          LOCATE reng, colu - LEN(cada$), 1
                          PRINT SPACE$(20)
                          LOCATE reng, colu - LEN(cada$), 1
                          PRINT cad$(i)
                          LOCATE reng, colu, 1, 7, 10
                          conta = 0
                       ELSE
                          IF colu < 20 + LEN(cad$(i)) THEN
                             cad$(i) = LEFT$(cad$(i), colu - 20) + a$ + RIGHT$(cad$(i), 19 + LEN(cad$(i)) - colu)
                             conta = 0
                          ELSE
                             cad$(i) = cad$(i) + a$
                             conta = 0
                          END IF
                       END IF
                       colu = colu + 1
                  
                     CASE 13
                       IF LEN(cad$(i)) = 0 THEN
                          BEEP
                       END IF
               
                  END SELECT
             
                ELSE
                  BEEP
                END IF
      
         END SELECT
   
      END IF

   ELSE
    BEEP
   END IF
      cade$ = cad$(i)

LOOP UNTIL a$ = CHR$(13) AND cad$(i) <> ""
 '     LOCATE 18, 50
  '    PRINT SPACE$(20)
END SUB

DEFSNG A-D
SUB iguales
 DO
   CLS
   TEX$(0) = "OPERACIONES CON SIGNOS IGUALES"
   TEX$(1) = "1) SUMA "
   TEX$(2) = "2) PRODUCTO"
   TEX$(3) = "5) REGRESAR AL MENU ANTERIOR"
   TEX$(10) = "ELIGE UNA OPCION: "
   CALL menu(TEX$(), 3, a$, "125")

SELECT CASE a$
        CASE "1"
           CALL positivos
        CASE "2"
          
           CALL ppositivos
        CASE "5"
           q% = 1
    END SELECT
 LOOP UNTIL q%
END SUB

SUB marco2

  FOR i = 1 TO 25
  LOCATE i, 1
    PRINT "²";
  NEXT i

  FOR i = 1 TO 80
  LOCATE 1, i
    PRINT "²";
  NEXT i

  FOR i = 1 TO 25
  LOCATE i, 80
    PRINT "²";
  NEXT i


  FOR i = 1 TO 80
  LOCATE 25, i
    PRINT "²";
  NEXT i

END SUB

SUB marco4
DEFINT A-Z

'  Crea el efecto de un borde flasheante en la pantalla


    a$ = "*    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    "
    WHILE INKEY$ <> "": WEND 'Clear keyboard buffer

    WHILE INKEY$ = ""
        FOR a = 1 TO 5
            LOCATE 1, 1                    'Imprime horizontalmente los asteriscos
            PRINT MID$(a$, a, 80);
            LOCATE 22, 1
            PRINT MID$(a$, 6 - a, 80);

            FOR b = 2 TO 21                'Imprime Verticalmente los astersicos
                c = (a + b) MOD 5
                IF c = 1 THEN
                    LOCATE b, 80
                    PRINT "*";
                    LOCATE 23 - b, 1
                    PRINT "*";
                ELSE
                    LOCATE b, 80
                    PRINT " ";
                    LOCATE 23 - b, 1
                    PRINT " ";
                END IF
            NEXT b
        NEXT a
    WEND

END SUB

DEFSNG E-Z
SUB menu (texto$(), nop, a$, teclas$)
CLS
  CALL pintura
 CALL marco2
 'posicion de la cabeza del menu
   CONST rm = 4, cm = 25
   CONST r = 9, c = 22
   CONST ro = 18, co = 40

    KEY OFF
    LOCATE rm, cm
    PRINT texto$(0)
      FOR i = 1 TO nop
         LOCATE r + i - 1, c
         PRINT texto$(i)
         PRINT
      NEXT i
      LOCATE ro, co
      PRINT texto$(10);
      PRINT nombre$
        LOCATE 18, 57, 1, 0, 7

       DO
      
       a$ = INKEY$
         a$ = UCASE$(a$)
       LOOP UNTIL INSTR(teclas$, a$) AND a$ <> ""
    PRINT a$
     FOR i = 1 TO 100
     NEXT i

END SUB

DEFSNG A-D
SUB menu5 (axz)


  v$ = DATE$

  n4 = INSTR(v$, "-")
  v = LEN(v$)
  cadi$ = LEFT$(v$, n4 - 1)

  cadd$ = RIGHT$(v$, v - n4)

  n5 = INSTR(cadd$, "-")
  v1 = LEN(cadd$)

  cadc$ = LEFT$(cadd$, n5 - 1)

  cadd$ = RIGHT$(cadd$, v1 - n5)
  xdia = VAL(cadc$)
  ymes = VAL(cadi$)
  zano = VAL(cadd$)

   xfec = xdia + ymes * 30 + (zano - 2012) * 366

   xfin = 50 - xfec   ' para permitir la licencia solo cambiar el a¤o 2008
   axz = xfin
                      ' el valor en resta es de 201, x  ¤ que se suman el
                     ' de dias de licencia


END SUB

SUB miscelanea
bue = 0
mal = 0

    CLS
    inicio = TIMER

WHILE (a$ <> "n") AND (a$ <> "N")
     CALL aleatorios(a, 10)
  
     SELECT CASE a

       CASE 1, 2
         CALL ejer1

       CASE 5, 6, 7
         CALL ejer3
      
       CASE 3, 4
         CALL ejer7

       CASE 8, 9, 0
         CALL ejer5
  
     END SELECT
        CALL tiemp(inicio)
        LOCATE 23, 40
        PRINT "EXAMEN"
        LOCATE 23, 6
        PRINT "¨DESEAS CONTINUAR S/N?"
        LOCATE 23, 29
        a$ = INPUT$(1)

WEND

END SUB

SUB momento (a$)
   LOCATE 22, 40
   PRINT " Oprime cualquier tecla para continuar"
DO
   a$ = INKEY$
LOOP WHILE a$ = ""
END SUB

SUB pintura
        SCREEN 2
        CLS
        SCREEN 0
        COLOR 2, 1
        CLS
        SCREEN 0
        COLOR 15, 1
END SUB

SUB pintura2

   SCREEN 1, 0'Gr ficos, paleta 0
   COLOR 1, 7  'Fondo azul obscuro, color

END SUB

SUB pintura3
   SCREEN 1, 0'Gr ficos, paleta 0
   COLOR 6, 3 'Fondo azul obscuro, color


END SUB

SUB positivos
  DO
        CALL coeficientes(b, c, d, e, f, g, h, i, j, 10)
   LOOP UNTIL b > 0 AND c > 0
        zx = 0
        x = b + c
            
    CALL pintura
    CALL marco2
    LOCATE 2, 6
    PRINT "REGLA 1"
    LOCATE 4, 6
    PRINT "Cuando sumas dos n£meros positivos el resultado es un n£mero"
    LOCATE 5, 6
    PRINT "positivo que se obtiene por la suma de los dos n£meros"
    LOCATE 6, 30
    PRINT b; "+"; c; "= "; x
    LOCATE 8, 6
    PRINT "Cuando sumas dos n£meros negativos el resultado es un n£mero"
    LOCATE 9, 6
    PRINT "negativo, que se obtiene por la suma de los dos n£meros. "
    LOCATE 10, 30
    PRINT -b; -c; " = "; -x
    CALL espera(1)
    CALL ejercicio1
END SUB

SUB ppositivos
           
    CALL pintura
    CALL marco2
    LOCATE 4, 6
    PRINT "REGLA 2"
    LOCATE 6, 6
    PRINT "Cuando multiplicas dos n£meros de igual signo el resultado es un n£mero"
    LOCATE 8, 6
    PRINT "positivo y se multiplican los dos n£meros."
    LOCATE 10, 6
    PRINT "Ejemplo:"
    DO
        CALL coeficientes(b, c, d, e, f, g, h, i, j, 10)
    LOOP UNTIL b > 0 AND c > 0
        x = b * c
            LOCATE 10, 30
            PRINT "("; b; ")"; "("; c; ") = "; x
            LOCATE 12, 30
            PRINT "("; -b; ")"; "("; -c; ") = "; x
    CALL espera(1)
    CALL ejercicio7


END SUB

SUB producto

    CLS
    inicio = TIMER

WHILE (a$ <> "n") AND (a$ <> "N")
     CALL aleatorios(a, 10)
   
     SELECT CASE a

       CASE 1, 2, 3, 4, 5
         CALL ejer7

       CASE 6, 7, 8, 9, 0
         CALL ejer5
   
     END SELECT
        CALL tiemp(inicio)
        LOCATE 23, 6
        PRINT "¨DESEAS CONTINUAR S/N?"
        LOCATE 23, 29
        a$ = INPUT$(1)

WEND

END SUB

SUB suma

    CLS
    inicio = TIMER

WHILE (a$ <> "n") AND (a$ <> "N")
     CALL aleatorios(a, 10)
    
     SELECT CASE a

       CASE 1, 2, 3, 4, 5
         CALL ejer1

       CASE 6, 7, 8, 9, 0
         CALL ejer3
    
     END SELECT

        CALL tiemp(inicio)
        LOCATE 23, 6
        PRINT "¨DESEAS CONTINUAR S/N?"
        LOCATE 23, 29
        a$ = INPUT$(1)

WEND

END SUB

SUB tiemp (inicio)
        final = TIMER - inicio
        horas = final / 3600
        hora = INT(horas)
        minutos = (horas - hora) * 60
        minuto = INT(minutos)
        aseg = (minutos - minuto) * 60
        aseg = aseg * 100
        aseg = INT(aseg)
        aseg = aseg / 100
     
        LOCATE 23, 60
                                                                    
        PRINT hora; ":"; minuto; ":"; aseg
       
END SUB

