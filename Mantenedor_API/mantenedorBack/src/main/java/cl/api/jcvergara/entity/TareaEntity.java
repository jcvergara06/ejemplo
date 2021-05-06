/**
 * 
 */
package cl.api.jcvergara.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author JCV
 *
 */
@Entity
@Table (name = TareaEntity.TABLE_NAME)
public class TareaEntity {
	
	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME= "tarea";
	
	@Id
	@Column
	private int identificador;

	@Column
	private String descripcion;

	@Column
	private Date fecha_creacion;

	@Column
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
