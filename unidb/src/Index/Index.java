package Index;

import java.util.ArrayList;

public interface Index<K,V>{
    void insertOne(K k,V v);
    void deleteOne(K k, V v);
    ArrayList<V> search(K k);
}
