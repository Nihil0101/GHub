
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studijosktu;

import java.util.Arrays;
import java.util.*;

public class MapKTUOA<K, V> implements MapADT<K, V>, MapADTx<K, V> {

    public static final int DEFAULT_INITIAL_CAPACITY = 32;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public static final HashType DEFAULT_HASH_TYPE = HashType.DIVISION;
    private K baseKey;       // Bazinis objektas skirtas naujų kūrimui
    private V baseObj;       // Bazinis objektas skirtas naujų kūrimui
    // Maišos lentelė
    ArrayList<Integer> id = new ArrayList<>();
    Entry<K, V>[] table;

    Entry<K, V> salinimui = new Entry<>(null, null);
    // Lentelėje esančių raktas-reikšmė porų kiekis
    protected int size = 0;
    // Apkrovimo faktorius
    protected float loadFactor;
    // Maišos metodas
    protected HashType ht;
    protected int tusti = 0;
    //--------------------------------------------------------------------------
    //  Maišos lentelės įvertinimo parametrai
    //--------------------------------------------------------------------------

    // Permaišymų kiekis
    protected int rehashesCounter = 0;
    // Paskutinės patalpintos poros grandinėlės indeksas maišos lentelėje
    protected int lastUpdatedChain = 0;
    // Lentelės grandinėlių skaičius

    // Einamas poros indeksas maišos lentelėje
    protected int index = 0;

    /* Klasėje sukurti 4 perkloti konstruktoriai, nustatantys atskirus maišos
     * lentelės parametrus. Jei kuris nors parametras nėra nustatomas -
     * priskiriama standartinė reikšmė.
     */
    public MapKTUOA(K baseKey, V baseObj, int initialCapacity, float loadFactor, HashType ht) {
        this(initialCapacity, loadFactor, ht);
        this.baseKey = baseKey;     // fiksacija dėl naujų elementų kūrimo
        this.baseObj = baseObj;
    }

    public MapKTUOA(K baseKey, V baseObj, HashType ht) {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, ht);
        this.baseKey = baseKey;     // fiksacija dėl naujų elementų kūrimo
        this.baseObj = baseObj;
    }

    public MapKTUOA() {
        this(DEFAULT_HASH_TYPE);
    }

    public MapKTUOA(HashType ht) {
        this(DEFAULT_INITIAL_CAPACITY, ht);
    }

    public MapKTUOA(int initialCapacity, HashType ht) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR, ht);
    }

    public MapKTUOA(float loadFactor, HashType ht) {
        this(DEFAULT_INITIAL_CAPACITY, loadFactor, ht);
    }

    public MapKTUOA(int initialCapacity, float loadFactor, HashType ht) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }

        if ((loadFactor <= 0.0) || (loadFactor > 1.0)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }

        this.table = new Entry[initialCapacity];
        this.loadFactor = loadFactor;
        this.ht = ht;
    }

    /**
     * Grąžina laisvą vietą atvaizdyje
     * @param key - raktas
     * @return elemento vietą atvaizdyje
     */
    private int findPosition(K key) {
        int index = hash(key, ht); //indeksas lentelej
        int index0 = index; //pagalbinis
        int i = 0; //šitas bus naudojamas kilimui kvadratu

        for (int j = 0; j < table.length; j++) {
            if (table[index] == null || table[index].key.equals(key)) {
                return index; //grazina vieta kur galima deti
            }
            i++; //nes perėjo didėja
            index  = (index0 + i * i) % table.length; //nauja vieta kad tiltpu i lentele
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Grąžinamas atvaizdyje esančių porų kiekis.
     *
     * @return Grąžinamas atvaizdyje esančių porų kiekis.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Išvalomas atvaizdis.
     */
    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
        index = 0;
        lastUpdatedChain = 0;

        rehashesCounter = 0;

    }

    /**
     * Patikrinama ar pora egzistuoja atvaizdyje.
     *
     * @param key raktas.
     * @return Patikrinama ar pora egzistuoja atvaizdyje.
     */
    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    /**
     * Atvaizdis papildomas nauja pora.
     *
     * @param key raktas,
     * @param value reikšmė.
     * @return Atvaizdis papildomas nauja pora.
     */
    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException( "Key or value is null in put(Key key, Value value)" );
        }
        int index = findPosition( key );
        if (index == -1) {
            rehash( table[ 0 ] );
            index = findPosition( key );
        }
        table[ index ] = new Entry<>( key, value );
        size++;
        if (size > table.length * loadFactor) {
            rehash( table[ index ] );
        }
        return value;
    }

    /**
     * Ieško tam tikros reikšmės MapKTU
     * @param value - reikšmė
     * @return
     */
    public boolean containsValue(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Value is null in containsValue");
        }
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (table[i].value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Permaišymas
     *
     * @param node
     */
    private void rehash(Entry<K, V> node) {
        MapKTUOA mapKTU
                = new MapKTUOA(table.length * 2, loadFactor, ht);
        for (int i = 0; i < table.length; i++) {
            if (table[ i ] != null) {
                mapKTU.put( table[ i ].key, table[ i ].value );
            }
        }
            table = mapKTU.table;
            rehashesCounter++;
    }

    /**
     * Grąžinama atvaizdžio poros reikšmė.
     *
     * @return Grąžinama atvaizdžio poros reikšmė.
     *
     * @param key raktas.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in get(Key key)");
        }
        index = hash(key, ht);
        return (table[index] != null) ? table[index].value : null;
    }

    /**
     * Maišos funkcijos skaičiavimas: pagal rakto maišos kodą apskaičiuojamas
     * atvaizdžio poros indeksas maišos lentelės masyve
     *
     * @param key
     * @param hashType
     * @return
     */
    private int hash(K key, HashType hashType) {
        int h = key.hashCode();
        switch (hashType) {
            case DIVISION:
                return Math.abs(h) % table.length;
            case MULTIPLICATION:
                double k = (Math.sqrt(5) - 1) / 2;
                return (int) (((k * Math.abs(h)) % 1) * table.length);
            case JCF7:
                h ^= (h >>> 20) ^ (h >>> 12);
                h = h ^ (h >>> 7) ^ (h >>> 4);
                return h & (table.length - 1);
            case JCF8:
                h = h ^ (h >>> 16);
                return h & (table.length - 1);
            default:
                return Math.abs(h) % table.length;
        }
    }

    /**
     * Pora pašalinama iš atvaizdžio.
     *
     * @param key Pora pašalinama iš atvaizdžio.
     * @return key raktas.
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in remove(Key key)");
        }
        System.out.println(id.size());
        index = hash(key, ht);

        table[index] = salinimui;

        System.out.println(id.size());
        return null;
    }

    @Override
    public String[][] toArray() {
        String[][] result = new String[table.length][];
        int count = 0;
        for (Entry<K, V> n : table) {

        }
        return result;
    }

    @Override
    public V put(String dataString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void println() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void println(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[][] getModelList(String delimiter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxChainSize() {
        return 1;
    }

    @Override
    public int getRehashesCounter() {
        return  rehashesCounter;
    }

    @Override
    public int getTableCapacity() {
        return table.length;
    }

    @Override
    public int getLastUpdatedChain() {
        return 0;
    }

    @Override
    public int getChainsCounter() {
        return 0;
    }

    class Entry<Key, Value> {

        Key key;
        Value value;

        Entry() {

        }

        public Entry(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
