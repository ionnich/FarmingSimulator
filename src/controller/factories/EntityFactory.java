package controller.factories;

public interface EntityFactory<T, S>{
    T create(S material);
}
