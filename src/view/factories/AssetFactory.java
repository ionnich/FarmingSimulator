package view.factories;

public interface AssetFactory<T, S>{

    S fetch(T asset);
}
