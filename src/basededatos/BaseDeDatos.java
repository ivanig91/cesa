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

    public void getProv() throws IOException, InterruptedException {
       HttpClient client = HttpClient.newHttpClient();
       HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://cesafct.firebaseio.com/Proveedor.json")).build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.statusCode());
        //System.out.println(response.body());

        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(response.body());
        JsonObject jsonObject = jsonTree.getAsJsonObject();


    }

    public FirebaseDatabase getDataBase() {
        return dataBase;
    }


    public DatabaseReference getTablaProveedor() {
        tablaProveedor = dataBase.getReference(Constantes.TABLA_PROVEEDOR);
        return tablaProveedor;
    }
    public void agregaDatosFS(Proveedor proveedor){
        DocumentReference docRef = dbStore.collection(Constantes.TABLA_PROVEEDOR).document();
        Map<String,Object> data = new HashMap<>();

        data.put("cifProveedor",proveedor.getCifProveedor());
        data.put("fecHomologacion",proveedor.getFecHomologacion());
        data.put("razProveedor",proveedor.getFecHomologacion());
        data.put("regNotarial",proveedor.getRegNotarial());
        data.put("segImporte",proveedor.getSegImporte());
        data.put("segResponsabilidad",proveedor.getSegResponsabilidad());

        ApiFuture<WriteResult> result = docRef.set(data);

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
            String segREsponsabilidad;
            Proveedor proveedor = new Proveedor();

            for(QueryDocumentSnapshot document : documents){
                proveedor.setCifProveedor(document.getString(Constantes.PROVEEDOR_CIF));
                proveedor.setFecHomologacion(document.getString(Constantes.PROVEEDOR_FECHA_HOMOLOGACION));
                proveedor.setRazProveedor(document.getString(Constantes.PROVEEDOR_RAZON_SOCIAL));
                proveedor.setRegNotarial(document.getString(Constantes.PROVEEDOR_REGION_NOTARIAL));
                proveedor.setSegImporte(document.getString(Constantes.PROVEEDOR_IMPORTE_SEGURO_RESPONSABLIDAD));
                proveedor.setSegResponsabilidad(document.getString(Constantes.PROVEEDOR_SEGURO_RESPONSABILIDAD));

                listaProvSF.add(proveedor);


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return  listaProvSF;
    }



    public void setMiListaProv(ArrayList<Proveedor> miListaProv) {
        this.miListaProv = miListaProv;
    }

    public void insertarProveedor(Proveedor proveedor){

        DatabaseReference ref = tablaProveedor.child(proveedor.getCifProveedor());
        ref.setValueAsync(proveedor);
    }


    public void borrarProveedor(Proveedor proveedor){
        DatabaseReference ref = tablaProveedor.child(proveedor.getCifProveedor());
        ref.setValueAsync(null);
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
