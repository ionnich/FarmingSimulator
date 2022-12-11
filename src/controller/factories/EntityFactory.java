package controller.factories;

/**
 * The EntityFactory interface is used to create entities of type T from a material of type S.
 *
 * @param <T> the type parameter
 * @param <S> the type parameter
 */
public interface EntityFactory<T, S>{

    /**
     * Creates an entity.
     *
     * @param material the material
     * @return the created entity
     */
    T create(S material);
}
