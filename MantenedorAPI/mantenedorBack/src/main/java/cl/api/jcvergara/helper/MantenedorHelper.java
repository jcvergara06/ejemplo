/**
 * 
 */
package cl.api.jcvergara.helper;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import cl.api.jcvergara.entity.TareaEntity;
import cl.api.jcvergara.util.Constantes;
import cl.api.jcvergara.vo.DatosEntradaVO;
import cl.api.jcvergara.vo.DatosResponse;
import cl.api.jcvergara.vo.DatosResponseVO;
import cl.api.jcvergara.vo.DatosSalidaObDatosVO;
import cl.api.jcvergara.vo.DatosSalidaVO;
import cl.api.jcvergara.vo.EstadoResponseVO;

/**
 * @author JCV
 *
 */
@Component
public class MantenedorHelper {

	/**  LOGGER *. */
	final static Logger LOGGER = LoggerFactory.getLogger(MantenedorHelper.class);
	
	/**
	 * 
	 * @param jsonEntrada
	 * @return
	 * @throws Exception
	 */
	public DatosEntradaVO capturaDatos(String jsonEntrada) throws Exception {
		Gson gson = new Gson();
		DatosEntradaVO datosEntrada = new DatosEntradaVO();
		try {
			datosEntrada = gson.fromJson(jsonEntrada, DatosEntradaVO.class);
		} catch (Exception e) {
			throw new Exception("Error al capturar datos de la transaccion");
		}
		return datosEntrada;
	}
	
	/**
	 * respExito
	 * @return
	 */
	public JSONObject respExito() {
		String respuesta = Constantes.sCOD_EMPTY;
		JSONObject jsonSalida = null;
		Gson gson = new Gson();
		DatosSalidaVO response = new DatosSalidaVO();
		EstadoResponseVO estado = new EstadoResponseVO();
		try {
			estado.setCodigoEstado(Constantes.sCOD_ESTADO);
			estado.setGlosaEstado(Constantes.sCOD_GLOSA_ESTADO);
			DatosResponse datos = new DatosResponse();
			datos.setCode(Constantes.sCOD_EXITO);
			datos.setDescription(Constantes.sMSJE_RESP_EXITO);
			response.setEstado(estado);
			response.setDatos(datos);
			respuesta = gson.toJson(response);
			JSONParser parser = new JSONParser(); 
			jsonSalida = (JSONObject) parser.parse(respuesta);
		} catch (Exception e) {
			LOGGER.error("Error al armar json salida exito: " + e);
		}
		return jsonSalida;
	}
	
	/**
	 * 
	 * @param respBD
	 * @return
	 */
	public JSONObject respExitoObDatos(List<TareaEntity> respBD) {
		JSONObject jsonSalida = null;
		Gson gson = new Gson();
		DatosSalidaObDatosVO response = new DatosSalidaObDatosVO();
		EstadoResponseVO estado = new EstadoResponseVO();
		String respuesta = Constantes.sCOD_EMPTY;
		try {
			estado.setCodigoEstado(Constantes.sCOD_ESTADO);
			estado.setGlosaEstado(Constantes.sCOD_GLOSA_ESTADO);
			DatosResponseVO datos = null;
			List<DatosResponseVO> listaSalida =  new java.util.ArrayList<DatosResponseVO>();
			for (TareaEntity listaTareas : respBD) {
				datos = new DatosResponseVO();
				datos.setIdentificador(String.valueOf(listaTareas.getIdentificador()));
				datos.setDescripcion(listaTareas.getDescripcion());
				datos.setFechaCreacion(splitFecha(listaTareas.getFecha_creacion().toString()));
				datos.setVigente(String.valueOf(listaTareas.getVigente()));
				listaSalida.add(datos);
			}
			response.setCode(Constantes.sCOD_EXITO);
			response.setDescription(Constantes.sMSJE_RESP_EXITO);
			response.setEstado(estado);
			response.setDatos(listaSalida);
			
			respuesta = gson.toJson(response);
			JSONParser parser = new JSONParser(); 
			jsonSalida = (JSONObject) parser.parse(respuesta);
		} catch (Exception e) {
			LOGGER.error("Error al armar json salida exito: " + e);
		}
		return jsonSalida;
	}
	
	/**
	 * 
	 * @param error
	 * @return
	 */
	public JSONObject respFallo(String error) {
		JSONObject jsonSalida = null;
		Gson gson = new Gson();
		DatosSalidaVO response = new DatosSalidaVO();
		EstadoResponseVO estado = new EstadoResponseVO();
		String respuesta = Constantes.sCOD_EMPTY;
		try {
			estado.setCodigoEstado(Constantes.sCOD_ESTADO);
			estado.setGlosaEstado(Constantes.sCOD_GLOSA_ESTADO);
			DatosResponse datos = new DatosResponse();
			datos.setCode(Constantes.sCOD_FALLO);
			if(null != error && !error.isEmpty()) {
				datos.setDescription(error);
			}else {
				datos.setDescription(Constantes.sMSJE_RESP_FALLO);
			}
			response.setEstado(estado);
			response.setDatos(datos);
			
			respuesta = gson.toJson(response);
			JSONParser parser = new JSONParser(); 
			jsonSalida = (JSONObject) parser.parse(respuesta);
		} catch (Exception e) {
			LOGGER.error("Error al armar json salida fallida: " + e);
		}
		return jsonSalida;
	}
	
	/**
	 * splitFecha
	 * @param fechaBd
	 * @return
	 */
	public String splitFecha(String fechaBd) {
		String fechaSalida = Constantes.sCOD_EMPTY;
		try {
			if(null != fechaBd && !fechaBd.isEmpty()) {
				String[] parts = fechaBd.split(" ");
				fechaSalida = parts[0];
			}
		} catch (Exception e) {
			LOGGER.error("Se produjo un error al realizar el split: " + e);
		}
		return fechaSalida;
	}
	
}
