package basededatos;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import utilidades.Constantes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public void insertarProveedor(){
        FirebaseDatabase dataBase  = FirebaseDatabase.getInstance();
        Proveedor proveedor = new Proveedor("a","a","a","a","a","a");
        DatabaseReference tablaProveedor = dataBase.getReference(Constantes.TABLA_PROVEEDOR);
        DatabaseReference ref = tablaProveedor.child(proveedor.getCifProveedor());
        ref.setValueAsync(proveedor);
    }

    public void modificarProveedor(Proveedor proveedor,String constanteModificacion ,String stringModificacion){

        FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
        DatabaseReference tablaProveedor = dataBase.getReference(Constantes.TABLA_PROVEEDOR);
        DatabaseReference ref = tablaProveedor.child(proveedor.getCifProveedor());
        Map<String, Object> modificacion = new HashMap<>();
        modificacion.put(constanteModificacion,stringModificacion);
        ref.updateChildrenAsync(modificacion);
    }

}
