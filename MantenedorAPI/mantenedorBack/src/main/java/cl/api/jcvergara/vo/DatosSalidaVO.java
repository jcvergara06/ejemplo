/**
 * 
 */
package cl.api.jcvergara.vo;

/**
 * @author JCV
 *
 */
public class DatosSalidaVO {

	/** The estado. */
	private EstadoResponseVO estado;
	
	/** The datos. */
	private DatosResponse datos;

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
	public DatosResponse getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DatosResponse datos) {
		this.datos = datos;
	}
}
