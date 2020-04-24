package basededatos;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.*;
import com.google.firestore.v1.Write;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import utilidades.Constantes;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.Supplier;


public class BaseDeDatos {
    private FirebaseDatabase dataBase;
    private DatabaseReference tablaProveedor;
    Firestore dbStore;
    int cont =0;
    public  ArrayList<Proveedor> miListaProv = new ArrayList<>();


    public BaseDeDatos(){
        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream(Constantes.SERVICE_ACCOUNT);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(Constantes.DATABASE_URL)
                    .build();

            FirebaseApp.initializeApp(options);
            dbStore = FirestoreClient.getFirestore();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataBase  = FirebaseDatabase.getInstance();
        tablaProveedor = dataBase.getReference(Constantes.TABLA_PROVEEDOR);



    }




    public void insertarProveedorFS(Proveedor proveedor){
        DocumentReference docRef = dbStore.collection(Constantes.TABLA_PROVEEDOR).document();
        Map<String,Object> data = new HashMap<>();

        data.put("cifProveedor",proveedor.getCifProveedor());
        data.put("fecHomologacion",proveedor.getFecHomologacion());
        data.put("razProveedor",proveedor.getRazProveedor());
        data.put("regNotarial",proveedor.getRegNotarial());
        data.put("segImporte",proveedor.getSegImporte());
        data.put("segResponsabilidad",proveedor.getSegResponsabilidad());

        ApiFuture<WriteResult> result = docRef.set(data);

    }
    public void borraProveedorSF(Proveedor proveedor){
        String numDoc = getDocumentoFS(proveedor.getCifProveedor());
        ApiFuture<WriteResult> registro = dbStore.collection(Constantes.TABLA_PROVEEDOR).document(numDoc).delete();

    }

    public boolean actualizarProveedorSF(Proveedor proveedor){
            boolean flip = false;
            String numDoc = getDocumentoFS(proveedor.getCifProveedor());

            if(numDoc!=null){
                flip = true;
                DocumentReference registro = dbStore.collection(Constantes.TABLA_PROVEEDOR).document(numDoc);
                System.out.println("clase base de datos: esto ocurre"+registro.getId());
                Map<String,Object> data = new HashMap<>();

                data.put("cifProveedor",proveedor.getCifProveedor());
                data.put("fecHomologacion",proveedor.getFecHomologacion());
                data.put("razProveedor",proveedor.getRazProveedor());
                data.put("regNotarial",proveedor.getRegNotarial());
                data.put("segImporte",proveedor.getSegImporte());
                data.put("segResponsabilidad",proveedor.getSegResponsabilidad());

                ApiFuture<WriteResult> result = registro.update(data);
            }
        return flip;
    }

    public ArrayList<Proveedor> leerProveedorSF(){
        ArrayList<Proveedor> listaProvSF = new ArrayList<>();
        ApiFuture<QuerySnapshot> query = dbStore.collection(Constantes.TABLA_PROVEEDOR).get();
        try {
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            String cifProveedor;
            String fecHomologacion;
            String razProveedor;
            String regNotarial;
            String segImporte;
            String segResponsabilidad;
            Proveedor proveedor;

            for(QueryDocumentSnapshot document : documents){
                cifProveedor = document.getString(Constantes.PROVEEDOR_CIF);
                fecHomologacion = document.getString(Constantes.PROVEEDOR_FECHA_HOMOLOGACION);
                razProveedor = document.getString(Constantes.PROVEEDOR_RAZON_SOCIAL);
                regNotarial = document.getString(Constantes.PROVEEDOR_REGION_NOTARIAL);
                segImporte = document.getString(Constantes.PROVEEDOR_IMPORTE_SEGURO_RESPONSABLIDAD);
                segResponsabilidad = document.getString(Constantes.PROVEEDOR_SEGURO_RESPONSABILIDAD);
                proveedor = new Proveedor(cifProveedor,fecHomologacion,razProveedor,regNotarial,segImporte,segResponsabilidad);
                listaProvSF.add(proveedor);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return  listaProvSF;
    }
    public String getDocumentoFS(String cifProveedor){
        ApiFuture<QuerySnapshot> query = dbStore.collection(Constantes.TABLA_PROVEEDOR).get();
        String numDoc = null;
        try {
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                if (document.getString(Constantes.PROVEEDOR_CIF).equals(cifProveedor)) {
                    numDoc = document.getId();
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return  numDoc;
    }

    public FirebaseDatabase getDataBase() {
        return dataBase;
    }


    public DatabaseReference getTablaProveedor() {
        tablaProveedor = dataBase.getReference(Constantes.TABLA_PROVEEDOR);
        return tablaProveedor;
    }

    public void setMiListaProv(ArrayList<Proveedor> miListaProv) {
        this.miListaProv = miListaProv;
    }



}
