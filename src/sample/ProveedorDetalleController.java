package sample;

import basededatos.BaseDeDatos;
import basededatos.Proveedor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utilidades.Constantes;


public class ProveedorDetalleController {
    private BaseDeDatos miBase;
    private Proveedor proveedor;
    private boolean inserta;
    private Controller listaProveedor;

    @FXML
    Button btAccion;

    @FXML
    TextField cifProveedor;
    @FXML
    TextField razSocial;
    @FXML
    TextField regNotarial;
    @FXML
    TextField codSRC;
    @FXML
    TextField importeSRC;
    @FXML
    TextField fecHom;


    @FXML
    public void accionButton(){
        Proveedor proveedorMetodo = new Proveedor();
        proveedorMetodo.setCifProveedor(cifProveedor.getText());
        proveedorMetodo.setRazProveedor(razSocial.getText());
        proveedorMetodo.setRegNotarial(regNotarial.getText());
        proveedorMetodo.setSegResponsabilidad(codSRC.getText());
        proveedorMetodo.setSegImporte(importeSRC.getText());
        proveedorMetodo.setFecHomologacion(fecHom.getText());
        if(!inserta){
            miBase.actualizarProveedorSF(proveedorMetodo);
        }else{
            miBase.insertarProveedorFS(proveedorMetodo);
        }
        listaProveedor.actualizarListaProveedor();
    }

    public void miInit(BaseDeDatos bd ,Proveedor pr, boolean inserta,Controller listaProveedor){
        this.miBase = bd;
        if(proveedor!=null){
            this.proveedor = pr;
        }

        this.inserta = inserta;
        this.listaProveedor = listaProveedor;

        if(!inserta){
            cifProveedor.setText(pr.getCifProveedor());
            razSocial.setText(pr.getRazProveedor());
            regNotarial.setText(pr.getRegNotarial());
            codSRC.setText(pr.getSegResponsabilidad());
            importeSRC.setText(pr.getSegImporte());
            fecHom.setText(pr.getFecHomologacion());
            btAccion.setText(Constantes.ACTUALIZAR_PROVEEDOR);
        }
        cifProveedor.setEditable(false);

    }
    public void miInit(BaseDeDatos bd , boolean inserta,Controller listaProveedor){
        this.miBase = bd;
        this.inserta = inserta;
        this.listaProveedor = listaProveedor;
        btAccion.setText(Constantes.INSERTAR_PROVEEDOR);
        cifProveedor.setEditable(true);

    }


    public boolean isInserta() {
        return inserta;
    }

    public void setInserta(boolean inserta) {
        this.inserta = inserta;
    }

    public BaseDeDatos getMiBase() {
        return miBase;
    }

    public void setMiBase(BaseDeDatos miBase) {
        this.miBase = miBase;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
