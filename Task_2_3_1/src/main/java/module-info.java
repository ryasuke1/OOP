module ru.nsu.khubanov.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens ru.nsu.khubanov.task_2_3_1 to javafx.fxml;
    exports ru.nsu.khubanov.task_2_3_1;
    exports ru.nsu.khubanov.task_2_3_1.controller;
    opens ru.nsu.khubanov.task_2_3_1.controller to javafx.fxml;
    exports ru.nsu.khubanov.task_2_3_1.model;
    opens ru.nsu.khubanov.task_2_3_1.model to javafx.fxml;
    exports ru.nsu.khubanov.task_2_3_1.model.food;
    opens ru.nsu.khubanov.task_2_3_1.model.food to javafx.fxml;
    exports ru.nsu.khubanov.task_2_3_1.logic;
    opens ru.nsu.khubanov.task_2_3_1.logic to javafx.fxml;
    exports ru.nsu.khubanov.task_2_3_1.util;
    opens ru.nsu.khubanov.task_2_3_1.util to javafx.fxml;
}