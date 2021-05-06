/**
 * 
 */
package cl.api.jcvergara.mapper;

import cl.api.jcvergara.entity.TareaEntity;
import cl.api.jcvergara.vo.TareaDTO;

/**
 * @author JCV
 *
 */
public class MantenedorMapper implements  EntityMapper<TareaDTO, TareaEntity> {

	@Override
	public TareaEntity toEntity(TareaDTO dto) {
		TareaEntity toEntity = new TareaEntity();
//		toEntity.setIdentificar(dto.getIdentificador());
		toEntity.setDescripcion(dto.getDescripcion());
		toEntity.setFecha_creacion(dto.getFecha_creacion());
		toEntity.setVigente(dto.getVigente());
		return toEntity;
	}

	@Override
	public TareaDTO toDto(TareaEntity entity) {
		return null;
	}

}
