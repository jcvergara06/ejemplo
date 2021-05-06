/**
 * 
 */
package cl.api.jcvergara.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.api.jcvergara.entity.TareaEntity;

/**
 * @author JCV
 *
 */
@Repository
public interface TareaRepository extends JpaRepository<TareaEntity, Long> {
	
	@Query(value="SELECT SYSDATE() AS FECHA", nativeQuery=true)
	String fecha();
	
	/**
	 * obtieneDatos
	 * @return
	 */
	@Modifying
    @Query(value = "SELECT * FROM TAREA", nativeQuery = true)
	List <TareaEntity> obtieneDatos();
	
	/**
	 * 
	 * @param descripcion
	 * @param fecha
	 * @param vigente
	 * @param id
	 */
    @Modifying
    @Transactional
    @Query(value = "UPDATE TAREA SET DESCRIPCION = :desc, FECHA_CREACION = :fecha, VIGENTE = :vigente WHERE IDENTIFICADOR = :id", nativeQuery = true)
    void update(@Param("desc") String descripcion, @Param("fecha") Date fecha, @Param("vigente") Boolean vigente, @Param("id") int id);

    /**
     * 
     * @param id
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TAREA WHERE IDENTIFICADOR = :id", nativeQuery = true)
    void eliminar(@Param("id") String id);
}
