# faf-oop

Tasks for OOP course

## Running labs

Requires maven, use `maven package -Dlab=<LAB_NUMBER>`, where `<LAB_NUMBER>` is 0, 1, 2, etc...

## Lab0

Created a class in Java that checks whether an integer is narcissistic.

```bash
mvn package -Dlab=0 && java -jar ./target/lab0.jar 
```

## Lab1

An interactive shell for managing students and university faculties.

```bash
mvn package -Dlab=1 && java -jar ./target/lab1.jar
```

Example enroll and graduate file are in `./resources/lab1`

Session state is stored in `.session`

Logs are stored in `lab1.log`


## Lab2

An interactive shell for monitoring file changes.

```bash
mvn package -Dlab=2 && java -jar ./target/lab2.jar
```

Monitors `./resources/lab2`

Stores state between restarts in `.lab2session`

Uses FileSystem events to catch changes in separate thread

---

FAF-221, Nejintev Nicolai
