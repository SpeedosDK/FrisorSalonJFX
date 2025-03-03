module sample.frisorsalonjfx {
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
    requires java.sql;
    requires java.desktop;

    opens sample.frisorsalonjfx to javafx.fxml;
    exports sample.frisorsalonjfx;
    exports sample.frisorsalonjfx.Database;
    opens sample.frisorsalonjfx.Database to javafx.fxml;
    exports sample.frisorsalonjfx.UIControllers;
    opens sample.frisorsalonjfx.UIControllers to javafx.fxml;
    exports sample.frisorsalonjfx.UseCases;
    opens sample.frisorsalonjfx.UseCases to javafx.fxml;
    exports sample.frisorsalonjfx.Model;
    opens sample.frisorsalonjfx.Model to javafx.fxml;

}