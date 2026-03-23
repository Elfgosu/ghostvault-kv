package ghostvaultkv.api;

import ghostvaultkv.internal.NativeGhostvaultKVWrapper;

/**
 * Implementation of basic Ghostvault KV API.
 *
 * @author thilo
 *
 * @version 21.03.26
 */
public class GhostvaultKVStoreImpl implements GhostvaultKVStore {
    private final NativeGhostvaultKVWrapper wrapper;

    public GhostvaultKVStoreImpl(String path) {
        wrapper = new NativeGhostvaultKVWrapper(path);
    }

    @Override
    public void put(String key, String value) {
        validateKey(key);
        validateValue(value);

        wrapper.put(key, value);
    }

    @Override
    public String get(String key) {
        validateKey(key);

        String result = wrapper.get(key);

        if (result == null) {
            throw new RuntimeException("Key not found: " + key);
        }

        return result;
    }

    @Override
    public void delete(String key) {
        validateKey(key);

        wrapper.delete(key);
    }

    @Override
    public void close() {
        wrapper.close();
    }

    private void validateKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key must not be empty");
        }
    }

    private void validateValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null");
        }
    }
}
