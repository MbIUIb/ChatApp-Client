module chat.local.javalocalchat {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires pgplib;

    opens chat.local.javalocalchat to javafx.fxml;
    exports chat.local.javalocalchat;
}