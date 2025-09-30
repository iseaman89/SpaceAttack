package factories;

public interface IFactory<T, P> {
    T create(P param);
}
