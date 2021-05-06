/**
 * 
 */
package cl.api.jcvergara.vo;

import java.util.Date;

/**
 * @author JCV
 *
 */
public class TareaDTO {

	/** identificar **/
	private int identificador;
	/** descripcion **/
	private String descripcion;
	/** fecha_creacion **/
	private Date fecha_creacion;
	/** vigente **/
	private Boolean vigente;
	
	/**
	 * @return the identificador
	 */
	public int getIdentificador() {
		return identificador;
	}
	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the fecha_creacion
	 */
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	/**
	 * @param fecha_creacion the fecha_creacion to set
	 */
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	/**
	 * @return the vigente
	 */
	public Boolean getVigente() {
		return vigente;
	}
	/**
	 * @param vigente the vigente to set
	 */
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
}
