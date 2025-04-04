# MVC-library-DMC

## Creació de l'usuari i la base de dades

```sql
CREATE USER 'dani'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON library.* TO 'dani'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS library;
USE library;

CREATE TABLE IF NOT EXISTS books (
    id_llibre INT PRIMARY KEY AUTO_INCREMENT,
    titol VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    editorial VARCHAR(255),
    datapublicacio DATE,
    tematica VARCHAR(255),
    isbn VARCHAR(13) UNIQUE NOT NULL
);
```

---

## Proves realitzades

### Login del usuari
**Usuari:** toni  
**Contrasenya:** !!  
![image](https://github.com/user-attachments/assets/ad0d93e7-25de-4788-b7a2-e3096f18ce4c)

---

## Inserció d'un nou llibre

- **Títol:** *Dragon Ball*
- **Autor:** *Sakura*
- **Editorial:** *Yatekomo*
- **Data Publicació:** *2025-11-03*
- **Temàtica:** *Anime*
- **ISBN:** *8450363872104*

![image](https://github.com/user-attachments/assets/dfd203aa-0c63-412f-af40-55d0fd5b4cc9)

---

## Confirmació a la base de dades

![image](https://github.com/user-attachments/assets/b21ada09-97b3-4095-97f9-51a2f62c147e)

---

## Cerca llibre per ID

![image](https://github.com/user-attachments/assets/21898264-0f91-4d61-95d1-25e7e46d9d07)

![image](https://github.com/user-attachments/assets/0fcf8bf8-6d13-4911-97e7-5172f1ff9082)

---

## Consulta la llista de llibres

![image](https://github.com/user-attachments/assets/9339cd5c-1624-4cf7-9d94-c9ea760ff0bc)

---

## Extra

**En tot moment l'usuari continua registrat i visible.**

![image](https://github.com/user-attachments/assets/1b195662-2301-4aa3-9085-8839e17708eb)

---

## Preguntes de reflexió

### Per què al servei estem utilitzant mètodes que no hem declarat explícitament al repositori? Com és possible?
Perquè l'interfície `CrudRepository` i `JpaRepository` inclou mètodes predefinits com `save()`, `findAll()`, `findById()`, etc., que *Spring Data JPA* implementa automàticament sense necessitat de definir-los manualment.

### El repositori pot elegir fer `extends` de les interfícies `PagingAndSortingRepository` o de `JpaRepository`. En què es diferencien aquestes dues amb la interfície `CrudRepository`?

- **`CrudRepository`**: Proporciona operacions bàsiques CRUD (*Create, Read, Update, Delete*).
- **`PagingAndSortingRepository`**: Afegeix funcionalitats per a paginar i ordenar resultats. Per exemple, el mètode `findAll()` amb paginació.
- **`JpaRepository`**: Estén de `PagingAndSortingRepository` i aporta funcionalitats addicionals específiques per a JPA.

### Què significa `Optional<Classe>` i per a què serveix?
`Optional<Classe>` és un contenidor que pot tenir un objecte o `null`. Serveix per evitar errors de `NullPointerException` i gestionar de forma segura casos ocasionats per `null`.

### Per què el controlador utilitza el servei i no la seva implementació?
El controlador utilitza el servei per mantenir la separació de responsabilitats i simplificar les proves. Això s'aconsegueix mitjançant la injecció de dependències.
