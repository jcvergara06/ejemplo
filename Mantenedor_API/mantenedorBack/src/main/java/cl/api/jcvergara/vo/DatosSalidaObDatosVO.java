/**
 * 
 */
package cl.api.jcvergara.vo;

import java.util.List;

/**
 * @author JCV
 *
 */
public class DatosSalidaObDatosVO {
	
	
	/** The code. */
	private String code;
	
	/** The description. */
	private String description;
	
	/** The estado. */
	private EstadoResponseVO estado;
	
	/** The datos. */
	private List<DatosResponseVO> datos;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the estado
	 */
	public EstadoResponseVO getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoResponseVO estado) {
		this.estado = estado;
	}

	/**
	 * @return the datos
	 */
	public List<DatosResponseVO> getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(List<DatosResponseVO> datos) {
		this.datos = datos;
	}

}
