
# Library Authentication API

Учебное REST API онлайн-библиотеки с регистрацией пользователей,
аутентификацией и разграничением доступа по ролям.

## Технологии

- Java 21
- Spring Boot 4.1.1
- Spring Security
- Spring Data JPA
- H2 Database (in-memory)
- Gradle
- JUnit 5 и MockMvc

## Возможности

- Регистрация пользователей
- Хеширование паролей с помощью BCrypt
- HTTP Basic Authentication
- Разграничение доступа по ролям USER и ADMIN
- Хранение пользователей в H2
- Интеграционное тестирование

## REST API

| Метод | Endpoint | Доступ |
|-------|----------|--------|
| POST | /register | Все |
| GET | /user/hello | USER, ADMIN |
| GET | /admin/hello | ADMIN |

## Регистрация

POST http://localhost:8080/register

JSON:
{
"username": "reader",
"password": "12345678"
}

При успешной регистрации возвращается HTTP 201 Created.
Пользователю автоматически назначается ROLE_USER.

## Запуск проекта

Windows:

    .\gradlew.bat bootRun

Приложение запускается на http://localhost:8080

## Тестовый администратор

Для создания тестового администратора необходимо задать
переменные окружения:

    SPRING_PROFILES_ACTIVE=dev
    DEMO_ADMIN_PASSWORD=<your-password>

При запуске создаётся пользователь librarian
с ролью ROLE_ADMIN.

Не используйте профиль dev для production.

## Тестирование

Запуск автоматических тестов:

    .\gradlew.bat clean test

Результаты:
- 6 тестов
- 0 ошибок
- 100% успешно

## Особенности

База данных H2 работает в оперативной памяти.
После остановки приложения зарегистрированные
пользователи удаляются.

Пароли хранятся в виде BCrypt-хешей.
