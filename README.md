Используемые технологии

Java

Spring Boot 2.4.0

Spring Web

Spring JDBC 5.3.3

PostgreSQL 17

Docker

Gradle 6.3 (Gradle Wrapper)

JUnit 5

AssertJ

Окружение и Gradle

В репозитории используется Gradle 6.3 через Gradle Wrapper.

Для корректной работы проекта в IntelliJ IDEA используется:

Distribution: Wrapper
Gradle JVM: Azul Zulu JDK 13

Настройка находится в:

File → Settings → Build, Execution, Deployment → Build Tools → Gradle

В модуле spring-jdbc для запуска тестов JUnit 5 добавлена настройка:

test {
    useJUnitPlatform()
}

Без неё Gradle не обнаруживает тесты JUnit 5 при запуске через --tests.

Задача 1. REST API для управления контактами

Модуль:

spring-rest

Реализован REST API для управления контактами.

Контакт содержит поля:

id

firstName

lastName

phone

email

Для временного хранения используется:

Map<Long, Contact>

Идентификатор нового контакта генерируется автоматически.

Эндпоинты

Метод

URL

Описание

GET

/contacts

Получить список всех контактов

GET

/contact/{contactId}

Получить контакт по ID

POST

/contacts

Создать новый контакт

PUT

/contacts/{contactId}

Обновить существующий контакт

При запросе несуществующего контакта возвращается HTTP 404 Not Found.

Запуск REST API

Запустить класс:

product.star.account.manager.AccountManagerMain

После запуска приложение доступно по адресу:

http://localhost:8080

Примеры запросов

Получить все контакты:

Invoke-RestMethod -Method Get -Uri "http://localhost:8080/contacts"

Создать контакт:

Invoke-RestMethod -Method Post `
  -Uri "http://localhost:8080/contacts" `
  -ContentType "application/json" `
  -Body '{"firstName":"David","lastName":"Petrov","phone":"+77001234567","email":"david@example.com"}'

Получить контакт по ID:

Invoke-RestMethod -Method Get -Uri "http://localhost:8080/contact/1"

Обновить контакт:

Invoke-RestMethod -Method Put `
  -Uri "http://localhost:8080/contacts/1" `
  -ContentType "application/json" `
  -Body '{"firstName":"David","lastName":"Petrov","phone":"+77009999999","email":"newdavid@example.com"}'

Проверка 404:

Invoke-RestMethod -Method Get -Uri "http://localhost:8080/contact/999"

Задача 2. ContactDao и PostgreSQL

Модуль:

spring-jdbc

Реализован ContactDao для работы с PostgreSQL через Spring JDBC.

Для выполнения SQL-запросов используется:

NamedParameterJdbcTemplate

Для преобразования строк результата SQL в объекты Contact используется:

RowMapper<Contact>

Реализованные методы

getAllContacts()
getContact(long contactId)
addContact(Contact contact)
updatePhoneNumber(long contactId, String phoneNumber)
updateEmail(long contactId, String email)
deleteContact(long contactId)

При добавлении контакта PostgreSQL автоматически генерирует ID.

Для получения созданного ID используется запрос вида:

INSERT INTO CONTACT (NAME, SURNAME, EMAIL, PHONE_NUMBER)
VALUES (:name, :surname, :email, :phone)
RETURNING ID;

PostgreSQL

Для задания используется PostgreSQL 17 в Docker.

Создание контейнера:

docker run --name productstar-postgres `
  -e POSTGRES_USER=postgres `
  -e POSTGRES_PASSWORD=postgres `
  -e POSTGRES_DB=productstar `
  -p 5433:5432 `
  -d postgres:17

Проверить контейнер:

docker ps

Параметры подключения находятся в:

spring-jdbc/src/main/resources/jdbc.properties

Используемая конфигурация:

jdbc.url=jdbc:postgresql://localhost:5433/productstar
jdbc.username=postgres
jdbc.password=postgres
jdbc.initialSize=5
jdbc.maxActive=10
jdbc.driverClassName=org.postgresql.Driver

Тестовая таблица

Перед запуском тестов файл contact.sql пересоздаёт таблицу CONTACT и добавляет тестовые записи.

Структура таблицы:

CREATE TABLE CONTACT (
    ID serial primary key,
    NAME varchar(255),
    SURNAME varchar(255),
    EMAIL varchar(255),
    PHONE_NUMBER varchar(255)
);

Тестирование

Реализация проверяется тестами:

ContactDaoTests

Проверяются:

добавление контакта;

получение контакта по ID;

получение списка всех контактов;

обновление номера телефона;

обновление email;

удаление контакта.

Результат выполнения тестов:

BUILD SUCCESSFUL