# uni_jornal_system

## How to run

1. Using Apache Maven 3.9.6

```
mvn clean install
```

```
mvn spring-boot:run
```

2. Docker with MySQL

```
docker run -d -p 3306:3306 --name mysql-docker-container -e MYSQL_ROOT_PASSWORD=your_password -e MYSQL_DATABASE=app_name -e MYSQL_USER=user_name -e MYSQL_PASSWORD=your_password mysql/mysql-server:latest
```

## Task technical requirements

Journal as a Table

![](https://github.com/Teasotea/uni_journal_system/blob/main/repo_img/table_anonim.jpg)

* register & login;
* 1st column fixed: always serial number;
* 2nd column fixed: LastName FirstName - moderator can choose of the available users;
* add/remove columns - header of the column always date & activity;
* add/remove rows - moderator can manage rows by adding new participants;

Technology stack: Java, Spring, MySQL, HTML/CSS, Docker
Special thanks to my project collaborator — Copilot
