/*
 * To change this template choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.bean;

/**
 *
 * @author ruben
 */
public class BPunto_delincuencia {

    String idpunto;
    String tipo;
    String idusuario;
    String usuario;
    String fecha;
    String hora;
    String descripcion;
    String direc_ref;
    String de_conocimiento;
    String img;
    String reg_de;
    boolean estado;
    BGeometry geometry;

    public String getIdpunto() {
        return idpunto;
    }

    public void setIdpunto(String idpunto) {
        this.idpunto = idpunto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDirec_ref() {
        return direc_ref;
    }

    public void setDirec_ref(String direc_ref) {
        this.direc_ref = direc_ref;
    }

    public String getDe_conocimiento() {
        return de_conocimiento;
    }

    public void setDe_conocimiento(String de_conocimiento) {
        this.de_conocimiento = de_conocimiento;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getReg_de() {
        return reg_de;
    }

    public void setReg_de(String reg_de) {
        this.reg_de = reg_de;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public BGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(BGeometry geometry) {
        this.geometry = geometry;
        
    }

    public void print() {

        System.out.println("PUNTO DE DELINCUENCIA");
        System.out.println("*********************************************");
        System.out.println("idpunto : " + this.getIdpunto());
        System.out.println("Tipo :" + this.getTipo());
        System.out.println("idUsuario :" + this.getIdusuario());
        System.out.println("Usuario :" + this.getUsuario());

        System.out.println("fecha :" + this.getFecha());
        System.out.println("Hora :" + this.getHora());
        System.out.println("descripcion :" + this.getDescripcion());
        System.out.println("direc_ref :" + this.getDirec_ref());

        System.out.println("de_conocimiento :" + this.getDe_conocimiento());
        System.out.println("Iimg :" + this.getImg());
        System.out.println("reg_de :" + this.getReg_de());
        System.out.println("Estado :" + this.isEstado());
        System.out.println("GEOMETRY");
        System.out.println("Latitud :" + this.getGeometry().getLatitud());
        System.out.println("Longitud :" + this.getGeometry().getLongitud());
        System.out.println("*********************************************");

    }
}
