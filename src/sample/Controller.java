package sample;

import basededatos.BaseDeDatos;
import basededatos.Proveedor;
import com.google.firebase.database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utilidades.Constantes;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

public class Controller implements Initializable {

    BaseDeDatos miBase;


    @FXML
    Button btAgregarProv;
    @FXML
    Button btModificara;
    @FXML
    Button btBorraProv;
    @FXML
    TableView<Proveedor> tablaProv;

    @FXML
    TableColumn cifCol;
    @FXML
    TableColumn razCol;
    @FXML
    TableColumn regNotCol;
    @FXML
    TableColumn cogCol;
    @FXML
    TableColumn importCol;
    @FXML
    TableColumn fecHomCol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actualizarListaProveedor();
        btAgregarProv.setText("Agregar Proveedor");
        btModificara.setText("Editar Proveedor");
        btBorraProv.setText("Borrar Proveedor");
    }
    @FXML
    public void agregarProv(){
        comprobarBaseDatos();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("proveedor_detalle.fxml"));
        try {
            GridPane root = loader.load();
            Stage nuStage = new Stage();
            Scene scene = new Scene(root,550,360);
            ProveedorDetalleController controller = loader.getController();

            //si el proveedor es nulo debo poner un action message
            controller.miInit(miBase,true,this);
            nuStage.setScene(scene);
            nuStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editarProveedor(){
        comprobarBaseDatos();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("proveedor_detalle.fxml"));
        try {
            GridPane root = loader.load();
            Stage nuStage = new Stage();
            Scene scene = new Scene(root,550,360);
            ProveedorDetalleController controller = loader.getController();
            Proveedor proveedor = tablaProv.getSelectionModel().getSelectedItem();
            //si el proveedor es nulo debo poner un action message
            controller.miInit(miBase,proveedor,false,this);
            nuStage.setScene(scene);
            nuStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void borrarProveedor(){
        comprobarBaseDatos();
        Proveedor proveedor = tablaProv.getSelectionModel().getSelectedItem();
        miBase.borraProveedorSF(proveedor);
        actualizarListaProveedor();
    }

    @FXML
    public void actualizarListaProveedor()  {
        comprobarBaseDatos();
        tablaProv.setEditable(true);
        tablaProv.setPrefWidth(800);
        final ObservableList<Proveedor> list = FXCollections.observableArrayList();
        cifCol.setCellValueFactory(new PropertyValueFactory<Proveedor,String>(Constantes.PROVEEDOR_CIF));
        razCol.setCellValueFactory(new PropertyValueFactory<Proveedor,String>(Constantes.PROVEEDOR_RAZON_SOCIAL));
        regNotCol.setCellValueFactory(new PropertyValueFactory<Proveedor,String>(Constantes.PROVEEDOR_REGION_NOTARIAL));
        cogCol.setCellValueFactory(new PropertyValueFactory<Proveedor,String>(Constantes.PROVEEDOR_SEGURO_RESPONSABILIDAD));
        importCol.setCellValueFactory(new PropertyValueFactory<Proveedor,String>(Constantes.PROVEEDOR_IMPORTE_SEGURO_RESPONSABLIDAD));
        fecHomCol.setCellValueFactory(new PropertyValueFactory<Proveedor,String>(Constantes.PROVEEDOR_FECHA_HOMOLOGACION));

        ArrayList<Proveedor> listaInterna = miBase.leerProveedorSF();

        list.addAll(listaInterna);
        tablaProv.setItems(list);


    }


    public void comprobarBaseDatos(){
        if(miBase==null){
            miBase = new BaseDeDatos();
        }
    }
}
