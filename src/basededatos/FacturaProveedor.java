package basededatos;

public class FacturaProveedor {
    String basImponible;
    String cifProveedor;
    String desFactura;
    String fecFactura;
    String fecVencimiento;
    String ivaImporte;
    String numFactura;
    String razProveedor;
    String totImporte;

    public FacturaProveedor(String basImponible, String cifProveedor, String desFactura, String fecFactura, String fecVencimiento, String ivaImporte, String numFactura, String razProveedor, String totImporte) {
        this.basImponible = basImponible;
        this.cifProveedor = cifProveedor;
        this.desFactura = desFactura;
        this.fecFactura = fecFactura;
        this.fecVencimiento = fecVencimiento;
        this.ivaImporte = ivaImporte;
        this.numFactura = numFactura;
        this.razProveedor = razProveedor;
        this.totImporte = totImporte;
    }

    public String getBasImponible() {
        return basImponible;
    }

    public void setBasImponible(String basImponible) {
        this.basImponible = basImponible;
    }

    public String getCifProveedor() {
        return cifProveedor;
    }

    public void setCifProveedor(String cifProveedor) {
        this.cifProveedor = cifProveedor;
    }

    public String getDesFactura() {
        return desFactura;
    }

    public void setDesFactura(String desFactura) {
        this.desFactura = desFactura;
    }

    public String getFecFactura() {
        return fecFactura;
    }

    public void setFecFactura(String fecFactura) {
        this.fecFactura = fecFactura;
    }

    public String getFecVencimiento() {
        return fecVencimiento;
    }

    public void setFecVencimiento(String fecVencimiento) {
        this.fecVencimiento = fecVencimiento;
    }

    public String getIvaImporte() {
        return ivaImporte;
    }

    public void setIvaImporte(String ivaImporte) {
        this.ivaImporte = ivaImporte;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public String getRazProveedor() {
        return razProveedor;
    }

    public void setRazProveedor(String razProveedor) {
        this.razProveedor = razProveedor;
    }

    public String getTotImporte() {
        return totImporte;
    }

    public void setTotImporte(String totImporte) {
        this.totImporte = totImporte;
    }
}
