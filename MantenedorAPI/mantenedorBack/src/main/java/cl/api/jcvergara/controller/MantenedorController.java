/**
 * 
 */
package cl.api.jcvergara.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.api.jcvergara.helper.MantenedorHelper;
import cl.api.jcvergara.service.MantenedorService;

/**
 * @author JCV
 *
 */
@RestController
@CrossOrigin
public class MantenedorController {
	
	/**  LOGGER *. */
	final static Logger LOGGER = LoggerFactory.getLogger(MantenedorHelper.class);
	
	/** APPLICATION_JSON_UTF8_VALUE **/
	public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";
	
	/** The service. */
	@Autowired
	MantenedorService service;
	
	/**
	 * confirmaRecargas
	 * @param datos
	 * @return
	 */
	@RequestMapping(value = "agregarDatos", method = RequestMethod.POST)
	public JSONObject agregarDatos(@RequestBody String jsonEntrada) {
		return service.agregaDatos(jsonEntrada);
	}
	
	/**
	 * obtenerDatos
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "obtenerDatos", method = RequestMethod.GET)
	public JSONObject obtenerDatos() {
		return service.obtieneListaDatos();
	}
	
	/**
	 * actualizarDatos
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "actualizarDatos", method = RequestMethod.POST)
	public JSONObject actualizarDatos(@RequestBody String jsonEntrada) {
		return service.actualizaDatos(jsonEntrada);
	}
	
	/**
	 * eliminarDatos
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "eliminarDatos", method = RequestMethod.GET)
	public JSONObject eliminarDatos(HttpServletRequest request) {
		return service.eliminaDatos(request);
	}

}
