module proyecto.cronometro.cronometro {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	requires java.xml.bind;

    opens proyecto.cronometro.cronometro to javafx.fxml;
    opens proyecto.cronometro.cronometro.model.Entity to javafx.base;
    opens proyecto.cronometro.cronometro.model.Conection to java.xml.bind;
    exports proyecto.cronometro.cronometro;
}
