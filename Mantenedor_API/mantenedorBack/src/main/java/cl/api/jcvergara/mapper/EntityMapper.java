package cl.api.jcvergara.mapper;
/**
 * The Interface EntityMapper.
 *
 * @param <D> the generic type
 * @param <E> the element type
 */
public interface EntityMapper<D, E> {

    /**
     * Covert method from given DTO to Entity.
     *
     * @param dto DTO type parameter.
     * @return E DTO type parameter.
     */
    E toEntity(D dto);

    /**
     * Covert method from given Entity to DTO.
     *
     * @param entity Entity type parameter.
     * @return E DTO object
     */
    D toDto(E entity);


}
