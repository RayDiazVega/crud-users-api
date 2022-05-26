# CRUD Users API

Aplicación web para la gestión de tareas

## Instrucciones de ejecución

Instalar previamente las siguientes herramientas:

```text
Java 11
Maven 3.8.5
Git 2.35.1
```

Clonar el repositorio con el comando:

```sh
git clone https://raydiazvega@bitbucket.org/raydiazvega/crud-users-api.git
```

En la carpeta del proyecto ejecutar los comandos:

```sh
mvn clean package
java -jar target\crud-users-api-0.0.1-SNAPSHOT.jar
```

## Operaciones

### Usuarios

|             ENDPOINT             |          POST           |                    GET                    |           PUT           |            DELETE             |
|:--------------------------------:|:-----------------------:|:-----------------------------------------:|:-----------------------:|:-----------------------------:|
|             `/users`             | Create user, return 200 |        Find all users, return 200         | Update user, return 200 |  Delete all user, return 200  |
|          `/users/{id}`           | Not allowed, return 400 |        Find user by id, return 200        | Not allowed, return 400 | Delete user by id, return 200 |
|      `/users/email/{email}`      | Not allowed, return 400 |      Find user by email, return 200       | Not allowed, return 400 |    Not allowed, return 400    |
| `/users/createdDate/{from}/{to}` | Not allowed, return 400 | Find users between date range, return 200 | Not allowed, return 400 |    Not allowed, return 400    |

### Tareas

|        ENDPOINT         |          POST           |                GET                |           PUT           |            DELETE             |
|:-----------------------:|:-----------------------:|:---------------------------------:|:-----------------------:|:-----------------------------:|
| `/users/{userId}/tasks` | Create task, return 200 | Find tasks by user id, return 200 | Not allowed, return 400 |    Not allowed, return 400    |
| `/users/tasks/{taskId}` | Not allowed, return 400 |    Find task by id, return 200    | Not allowed, return 400 | Delete task by id, return 200 |
|     `/users/tasks`      | Not allowed, return 400 |      Not allowed, return 400      | Update task, return 200 |    Not allowed, return 400    |

## Pruebas

Se preparó un conjunto de pruebas unitarias en la ruta `src/test` con una cobertura del **90%**,
se pueden ejecutar las pruebas en la raíz del proyecto con el comando:

```sh
mvn test
```

## Decisiones tomadas

### Arquitectura del proyecto

````sh
├───main
│   ├───java
│   │   └───com
│   │       └───colpatria
│   │           └───crudusersapi
│   │               ├───config
│   │               ├───exception
│   │               └───user
│   │                   ├───application
│   │                   ├───dto
│   │                   └───infrastructure
│   │                       ├───adapters  
│   │                       └───ports     
│   └───resources
│       ├───static
│       └───templates
└───test
    ├───java
    │   └───com
    │       └───colpatria
    │           └───crudusersapi
    │               ├───constant
    │               └───user
    │                   └───infrastructure
    │                       └───adapters  
    └───resources
````
