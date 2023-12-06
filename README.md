Тестовое задание

The API should allow:

creation of a user account (deletion and other CRUD operations not requested). User properties are name, email, password
and date of creation;
addition, viewing (including a method to get a random quote), modification, and deletion of quotes (real deletion from
the database, not just via an archive flag). Quote properties include content, date of creation / update, link to user
who posted it, link to votes;
voting on quotes (either upvote or downvote);
view of the top and worse 10 quotes, the details of each quote, and ideally a graph of the evolution of the votes over
time.
Not requested: frontend part, authentication mechanism, and account rights management.

- скачать образ с помощью команды: docker pull tatiby/quoters

- запустить контейнер на основе образа с помощью команды: docker run -d -p 8081:8081 tatiby/quoters

Либо:

- Скачать или склонировать репозиторий приложения в программу IntelliJ IDEA

- Упаковать проект в Maven

- В терминале в корне приложения выполнить команду docker build ./

- Упаковать образ в контейнер командой docker-compose build

- Запустить контейнер командой docker-compose up