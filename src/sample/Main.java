package sample;

import basededatos.BaseDeDatos;
import basededatos.FacturaProveedor;
import basededatos.Proveedor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilidades.Constantes;

import java.util.ArrayList;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        BaseDeDatos miBase = new BaseDeDatos();

        FacturaProveedor factura = new FacturaProveedor("a","a","a","a","a","a","a","a","a");
        //miBase.insertarFactura(factura);
        ArrayList<Proveedor> proveedores = miBase.listaProveedores();
        System.out.println(proveedores.size());


    }


    public static void main(String[] args) {
        launch(args);
    }
}
