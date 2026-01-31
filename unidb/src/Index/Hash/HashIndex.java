package Index.Hash;

import Index.Index;

import java.util.ArrayList;

public class HashIndex<K, V> implements Index<K, V> {

    private final ArrayList<ArrayList<Pair<K, V>>> array = new ArrayList<>();

    public HashIndex(int size) {
        for (int i = 0; i < size; i++)
            array.add(new ArrayList<>());
    }

    @Override
    public void insertOne(K k, V v) {
        int index = hash(k);
        array.get(index).add(new Pair<>(k, v));
    }

    @Override
    public void deleteOne(K k, V v) {
        int index = hash(k);
        ArrayList<Pair<K, V>> bucket = array.get(index);

        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).k.equals(k) && bucket.get(i).v.equals(v)) {
                bucket.remove(i);
                return;
            }
        }
    }

    @Override
    public ArrayList<V> search(K k) {
        ArrayList<V> result = new ArrayList<>();
        int index = hash(k);
        ArrayList<Pair<K, V>> bucket = array.get(index);

        for (Pair<K, V> p : bucket) {
            if (p.k.equals(k))
                result.add(p.v);
        }
        return result;
    }

    int hash(K k) {
        if (k instanceof String){
            int hash = 0;
            for (int i = 0; i < ((String) k).length(); i++)
                hash += (i + 1) * ((String) k).charAt(i) % array.size();

            return hash % array.size();
        }
        else if (k instanceof Integer)
            return (Integer)k % array.size();
        else if (k instanceof Double hash){
            while (hash < array.size() * 100 && hash.intValue() != hash)
                hash *= 10;

            return hash.intValue() % array.size();
        }

        return -1;
    }
}

class Pair<K, V> {
    final K k;
    final V v;

    Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }
}
