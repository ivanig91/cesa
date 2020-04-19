package basededatos;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import org.apache.commons.logging.Log;
import utilidades.Constantes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseDeDatos {

    public BaseDeDatos(){
        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream(Constantes.SERVICE_ACCOUNT);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(Constantes.DATABASE_URL)
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void insertarProveedor(Proveedor proveedor){
        FirebaseDatabase dataBase  = FirebaseDatabase.getInstance();
        DatabaseReference tablaProveedor = dataBase.getReference(Constantes.TABLA_PROVEEDOR);
        DatabaseReference ref = tablaProveedor.child(proveedor.getCifProveedor());
        ref.setValueAsync(proveedor);
    }

    public void borrarProveedor(Proveedor proveedor){
        FirebaseDatabase dataBase  = FirebaseDatabase.getInstance();
        DatabaseReference tablaProveedor = dataBase.getReference(Constantes.TABLA_PROVEEDOR);
        DatabaseReference ref = tablaProveedor.child(proveedor.getCifProveedor());
        ref.setValueAsync(null);
    }
    public ArrayList<Proveedor> listaProveedores(){
        ArrayList<Proveedor> proveedoresLista = new ArrayList<>();
        FirebaseDatabase dataBase  = FirebaseDatabase.getInstance();
        DatabaseReference tablaProveedor = dataBase.getReference(Constantes.TABLA_PROVEEDOR);
        tablaProveedor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for(DataSnapshot child : snapshot.getChildren()){
                   Proveedor proveedor = child.getValue(Proveedor.class);
                    System.out.println("se esta haciendo");
                    System.out.println(proveedor.getCifProveedor());
                    proveedoresLista.add(proveedor);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        return proveedoresLista;
    }



    public void modificarProveedor(Proveedor proveedor,String constanteModificacion ,String stringModificacion){
        FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
        DatabaseReference tablaProveedor = dataBase.getReference(Constantes.TABLA_PROVEEDOR);
        DatabaseReference ref = tablaProveedor.child(proveedor.getCifProveedor());
        Map<String, Object> modificacion = new HashMap<>();
        modificacion.put(constanteModificacion,stringModificacion);
        ref.updateChildrenAsync(modificacion);
    }
    public void insertarFactura(FacturaProveedor facturaProveedor){
        FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
        DatabaseReference tablaFactura = dataBase.getReference(Constantes.TABLA_FACTURA);
        DatabaseReference ref = tablaFactura.child(facturaProveedor.getIdFactura());
        ref.setValueAsync(facturaProveedor);
    }

}
