package ghostvaultkv.api;

import java.nio.charset.StandardCharsets;
import ghostvaultkv.internal.NativeGhostvaultKV;

/**
 * Implementation of basic Ghostvault KV API.
 *
 * @author thilo
 *
 * @version 21.03.26
 */
public class GhostvaultKVStoreImpl implements GhostvaultKVStore {
    private final NativeGhostvaultKV nativeStore;
    private final long handle;

    public GhostvaultKVStoreImpl(String path) {
        this.nativeStore = new NativeGhostvaultKV();
        this.handle = nativeStore.open(path);
    }

    @Override
    public void put(String key, String value) {
        validateKey(key);
        validateValue(value);

        nativeStore.put(handle,
                key.getBytes(StandardCharsets.UTF_8),
                value.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String get(String key) {
        validateKey(key);

        byte[] result = nativeStore.get(handle,
                key.getBytes(StandardCharsets.UTF_8));

        if (result == null) {
            throw new RuntimeException("Key not found: " + key);
        }

        return new String(result, StandardCharsets.UTF_8);
    }

    @Override
    public void delete(String key) {
        validateKey(key);

        nativeStore.delete(handle,
                key.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void close() {
        nativeStore.close(handle);
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
