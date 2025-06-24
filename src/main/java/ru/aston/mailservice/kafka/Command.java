package ru.aston.mailservice.kafka;

enum Command {
    CREATE("create"),
    DELETE("remove");

    private final String description;

    Command(String description) {
        this.description = description;
    }

    public boolean is(String name) {
        return description.equals(name);
    }
}
