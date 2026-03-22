package ghostvaultkv.api;

/**
 * Ghostvault KV Interface for basic storage API.
 *
 * @author thilo
 *
 * @version 21.03.26
 */
public interface GhostvaultKVStore {
    void put(String key, String value);
    String get(String key);
    void delete(String key);
    void close();
}
