# Grade Management System

![](https://github.com/Teasotea/uni_jornal_system/blob/master/repo_img/login.jpg)

## Entity Relationship Diagram (ERD)

![](https://github.com/Teasotea/uni_jornal_system/blob/master/repo_img/ER.jpg)

## How to use

1. Docker with MySQL

```
docker run -d -p 3306:3306 --name mysql-docker-container -e MYSQL_ROOT_PASSWORD=your_password -e MYSQL_DATABASE=app_name -e MYSQL_USER=user_name -e MYSQL_PASSWORD=your_password mysql/mysql-server:latest
```


2. Run an app with Maven (Apache Maven 3.9.6)

```
mvn clean install
```

```
mvn spring-boot:run
```

## Project Pictures

![](https://github.com/Teasotea/uni_jornal_system/blob/master/repo_img/students.jpg)

![](https://github.com/Teasotea/uni_jornal_system/blob/master/repo_img/activities.jpg)

![](https://github.com/Teasotea/uni_jornal_system/blob/master/repo_img/new_course.jpg)

![](https://github.com/Teasotea/uni_jornal_system/blob/master/repo_img/update_student.jpg)

![](https://github.com/Teasotea/uni_jornal_system/blob/master/repo_img/register.jpg)

## Project Description

Journal as a Table

![](https://github.com/Teasotea/uni_jornal_system/blob/master/repo_img/table_anonim.jpg)

* register & login;
* 1st column fixed: always serial number;
* 2nd column fixed: LastName FirstName - moderator can choose of the available users;
* add/remove columns - header of the column always date & activity;
* add/remove rows - moderator can manage rows by adding new participants;

Technology stack: Java, Spring, MySQL, HTML/CSS, Docker
Special thanks to my project collaborator — Copilot
