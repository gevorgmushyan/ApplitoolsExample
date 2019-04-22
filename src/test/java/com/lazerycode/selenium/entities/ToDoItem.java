package com.lazerycode.selenium.entities;

import lombok.Data;

import java.util.Random;

import static com.lazerycode.selenium.helpers.DateHelper.getCurrentYear;

@Data
public class ToDoItem {
    private Integer id;
    private String title;
    private String date;
    private Priority priority;
    private Status status;

    private static int count = 0;

    private ToDoItem() {}

    public ToDoItem(String title, String date, Priority priority) {
        setTitle(title);
        setDate(date);
        setPriority(priority);
        setStatus(Status.TODO);
    }

    public static ToDoItem getToDoItem() {
        ToDoItem item = new ToDoItem();
        Priority[] priorities = Priority.values();

        item.id = null;
        item.title = "task" + ++count;
        item.date = getCurrentYear("dd/MM/yyyy");
        item.priority = priorities[new Random().nextInt(priorities.length)];
        item.status = Status.TODO;

        return item;
    }
}
