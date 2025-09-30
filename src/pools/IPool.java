package pools;

import java.util.List;

public interface IPool <T, P>{
    T get(P param);
    void deactivate(T obj);
    void deactivateAll();
    List<T> getPooledObj();
}
