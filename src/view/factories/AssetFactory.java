package view.factories;

/**
 * The Asset Factory interface is used to assign assets for the game.
 *
 * @param <T> the type parameter
 * @param <S> the type parameter
 */
public interface AssetFactory<T, S>{

    /**
     * Fetch s.
     *
     * @param asset the asset
     * @return the s
     */
    S fetch(T asset);
}
