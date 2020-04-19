package sample;

import basededatos.BaseDeDatos;
import basededatos.Proveedor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilidades.Constantes;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        BaseDeDatos miBase = new BaseDeDatos();
        //
        //miBase.insertarProveedor();
        //
        //El metodo insertaProveedor acutalmente inserta un proveedor que con una a en todos los campos
        // inserta correctamente el proveedor en firebase
        Proveedor proveedor = new Proveedor("a","a","a","a","a","a");
        miBase.modificarProveedor(proveedor, Constantes.PROVEEDOR_RAZON_SOCIAL,"Proveedor cesa");

    }


    public static void main(String[] args) {
        launch(args);
    }
}
