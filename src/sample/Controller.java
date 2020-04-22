package sample;

import basededatos.BaseDeDatos;
import basededatos.Proveedor;
import com.google.firebase.database.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Controller {
    Proveedor proveedor;
    ArrayList<Proveedor> listaProveedores = new ArrayList<>();
    BaseDeDatos miBase;
    JLabel nuevo = new JLabel();


    @FXML
    public Label label;

    @FXML
    public Button btCarga;
    @FXML
    public Button btInsertar;

    @FXML
    public void insertarProveedor(){
        comprobarBaseDatos();
        Proveedor proveedor = new Proveedor("2","2","2","2","2","2");
        miBase.agregaDatosFS(proveedor);

    }
    public void comprobarBaseDatos(){
        if(miBase==null){
            miBase = new BaseDeDatos();
        }
    }





    @FXML
    public void mostrarProveedores()  {
       comprobarBaseDatos();
       label.setText("hola");
       for(Proveedor proveedor: miBase.leerProveedorSF()){
           label.setText(proveedor.getRazProveedor());
       }


    }

}
