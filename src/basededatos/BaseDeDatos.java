package basededatos;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BaseDeDatos {

    public BaseDeDatos(){
        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream("service-account-file.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://cesafct.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void insertarProveedor(){
        final FirebaseDatabase dataBase  = FirebaseDatabase.getInstance();
        Proveedor proveedor = new Proveedor("a","a","a","a","a","a");
        DatabaseReference tablaProveedor = dataBase.getReference("Proveedor");
        DatabaseReference ref = tablaProveedor.child(proveedor.getCifProveedor());
        ref.setValueAsync(proveedor);
    }
}
