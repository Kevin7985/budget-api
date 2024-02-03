package ru.ist.error.exceptions;

public class Forbidden extends RuntimeException {
    public Forbidden() {
        super("Доступ к запрашиваемому ресурсу запрещён");
    }
}
