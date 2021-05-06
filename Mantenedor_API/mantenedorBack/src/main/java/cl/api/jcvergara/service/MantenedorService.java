/**
 * 
 */
package cl.api.jcvergara.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.api.jcvergara.entity.TareaEntity;
import cl.api.jcvergara.helper.MantenedorHelper;
import cl.api.jcvergara.mapper.MantenedorMapper;
import cl.api.jcvergara.repository.TareaRepository;
import cl.api.jcvergara.util.Constantes;
import cl.api.jcvergara.vo.DatosEntradaVO;
import cl.api.jcvergara.vo.TareaDTO;

/**
 * @author JCV
 *
 */
@Service
public class MantenedorService {
	
	/**  LOGGER *. */
	final static Logger LOGGER = LoggerFactory.getLogger(MantenedorService.class);
	
    /** The repository. */
    @Autowired
    TareaRepository bd;
    
    /** The helper. */
    @Autowired
    MantenedorHelper helper;
    
	/**
	 * 
	 * @param request
	 * @return
	 */
	public JSONObject obtieneListaDatos() {
		JSONObject jsonSalida = null;
		List<TareaEntity> respBD = null;
		try {
			respBD = bd.obtieneDatos();
			jsonSalida = helper.respExitoObDatos(respBD);
			LOGGER.info("JSON SALIDA: " + jsonSalida.toString());
		} catch (Exception e) {
			LOGGER.error("Error al obtener datos: " + e);
			jsonSalida = helper.respFallo(e.getMessage());
		}
		return jsonSalida;
	}
	
	/**
	 * 
	 * @param datos
	 * @return
	 */
	public JSONObject agregaDatos(String jsonEntrada) {
		JSONObject jsonSalida = null;
		DatosEntradaVO datosEntrada = null;
		TareaDTO dto = new TareaDTO();
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			datosEntrada = helper.capturaDatos(jsonEntrada);
			Date date = format.parse(datosEntrada.getFechaCreacion());
			dto.setDescripcion(datosEntrada.getDescripcion());
			dto.setFecha_creacion(date);
			dto.setVigente(Boolean.parseBoolean(datosEntrada.getVigente()));
			MantenedorMapper map = new MantenedorMapper();
			TareaEntity entity = map.toEntity(dto);
            List<TareaEntity> lista =  new java.util.ArrayList<TareaEntity>();
            lista.add(entity);
            bd.saveAll(lista);
            jsonSalida = helper.respExito();
		} catch (Exception e) {
			LOGGER.error("Error al insertar datos: " + e);
			jsonSalida = helper.respFallo(e.getMessage());
		}
		return jsonSalida;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public JSONObject actualizaDatos(String jsonEntrada) {
		JSONObject jsonSalida = null;
		DatosEntradaVO datosEntrada = null;
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			datosEntrada = helper.capturaDatos(jsonEntrada);
			Date date = format.parse(datosEntrada.getFechaCreacion());
			if(null != datosEntrada && null != datosEntrada.getIdentificador() && !String.valueOf(datosEntrada.getIdentificador()).isEmpty()) {
				bd.update(datosEntrada.getDescripcion(), date, Boolean.valueOf(datosEntrada.getVigente()), Integer.valueOf(datosEntrada.getIdentificador()));
				jsonSalida = helper.respExito();
			}else {
				jsonSalida = helper.respFallo(Constantes.sCOD_EMPTY);
			}
		} catch (Exception e) {
			LOGGER.error("Error al actualizar datos: " + e);
			jsonSalida = helper.respFallo(e.getMessage());
		}
		return jsonSalida;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public JSONObject eliminaDatos(HttpServletRequest request) {
		JSONObject jsonSalida = null;
		try {
			String identificador = request.getParameter("id");
			if(null != identificador && !identificador.isEmpty()) {
				bd.eliminar(identificador);
				jsonSalida = helper.respExito();
			}else {
				jsonSalida = helper.respFallo(Constantes.sCOD_EMPTY);
			}
		} catch (Exception e) {
			LOGGER.error("Error al eliminar datos: " + e);
			jsonSalida = helper.respFallo(e.getMessage());
		}
		return jsonSalida;
	}

}
