package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args){
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
//        app.get("/users", ctx -> ctx.result("GET /users"));
//        app.post("/users", ctx -> ctx.result("POST /users"));
//        app.get("/hello", ctx -> {
//            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
//            ctx.result("Hello, " + name + "!");
//        });
//        app.get("/courses/{id}", ctx -> {
//            ctx.result("Course ID: " + ctx.pathParam("id"));
//        });
//        app.get("/users/{id}", ctx -> {
//            ctx.result("User ID: " + ctx.pathParam(("id")));
//        });
//
//        app.get("/courses/{courseId}/lessons/{id}", ctx -> {
//            var courseId = ctx.pathParam("courseId");
//            var lessonId = ctx.pathParam("id");
//            ctx.result("Course ID: " + courseId + " Lesson ID: " + lessonId);
//        });
//
//        app.get("users/{id}/post/{postId}", ctx -> {
//            var userId = ctx.pathParam("id");
//            var postId = ctx.pathParam("postId");
//            ctx.result("user ID: " + userId + " post ID: " + postId);
//        });
//
//        app.get("/", ctx -> ctx.render("index.jte"));

//        app.get("/courses/{id}", ctx -> {
//            var id = ctx.pathParam("id");
//            var course = new Course("Java", "Java Developer");
//            var page = new CoursePage(course);
//            ctx.render("courses/show.jte", model("page", page));
//        });

        app.get("/courses", ctx -> {
            var courses = List.of(new Course("Java", "Java Developer"),
                    new Course("Javascript", "Javascript Developer"),
                    new Course("Python", "Python Developer"));
                    var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParam("id");
            var courses = List.of(new Course("Java", "Java Developer"),
                    new Course("Javascript", "Javascript Developer"),
                    new Course("Python", "Python Developer"));
            var page = new CoursePage(courses.get(Integer.parseInt(id ) - 1));
            ctx.render("courses/show.jte", model("page", page));
        });


        app.start(7070);
    }
}
