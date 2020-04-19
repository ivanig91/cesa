package basededatos;

public class Proveedor {
    private String cifProveedor;
    private String fecHomologacion;
    private String razProveedor;
    private String regNotarial;
    private String segImporte;
    private String segResponsabilidad;

    public Proveedor(String cifProveedor, String fecHomologacion, String razProveedor, String regNotarial, String segImporte, String segResponsabilidad) {
        this.cifProveedor = cifProveedor;
        this.fecHomologacion = fecHomologacion;
        this.razProveedor = razProveedor;
        this.regNotarial = regNotarial;
        this.segImporte = segImporte;
        this.segResponsabilidad = segResponsabilidad;
    }

    public String getCifProveedor() {
        return cifProveedor;
    }

    public void setCifProveedor(String cifProveedor) {
        this.cifProveedor = cifProveedor;
    }

    public String getFecHomologacion() {
        return fecHomologacion;
    }

    public void setFecHomologacion(String fecHomologacion) {
        this.fecHomologacion = fecHomologacion;
    }

    public String getRazProveedor() {
        return razProveedor;
    }

    public void setRazProveedor(String razProveedor) {
        this.razProveedor = razProveedor;
    }

    public String getRegNotarial() {
        return regNotarial;
    }

    public void setRegNotarial(String regNotarial) {
        this.regNotarial = regNotarial;
    }

    public String getSegImporte() {
        return segImporte;
    }

    public void setSegImporte(String segImporte) {
        this.segImporte = segImporte;
    }

    public String getSegResponsabilidad() {
        return segResponsabilidad;
    }

    public void setSegResponsabilidad(String segResponsabilidad) {
        this.segResponsabilidad = segResponsabilidad;
    }
}
