/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.bean;

/**
 *
 * @author ruben
 */
public class BPunto_Desechos {

    String idpunto;
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
    BGeometry bGeometry;

    public String getIdpunto() {
        return idpunto;
    }

    public void setIdpunto(String idpunto) {
        this.idpunto = idpunto;
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

 

    public BGeometry getbGeometry() {
        return bGeometry;
    }

    public void setbGeometry(BGeometry bGeometry) {
        this.bGeometry = bGeometry;
    }
    
}
