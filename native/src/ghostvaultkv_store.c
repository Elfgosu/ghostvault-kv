//
// Created by thilo on 24.03.2026.
//

#include "ghostvaultkv_store.h"

static void replay_callback(void* ctx, const char* key, uint32_t key_len, off_t offset) {

    hashmap* map = (hashmap*)ctx;
    hashmap_put(map, key, key_len, offset);
}

int kvstore_open(kvstore* store, const char* path) {
    hashmap_init(&store->index, 1024);
    log_open(&store->log, path);

    log_replay(&store->log, replay_callback, &store->index);

    return 1;
}

void kvstore_put(kvstore* store, const char* key, uint32_t key_len,
                 const char* value, uint32_t value_len) {

    off_t offset = log_append(&store->log, key, key_len, value, value_len);
    hashmap_put(&store->index, key, key_len, offset);
}

int kvstore_get(kvstore* store, const char* key, uint32_t key_len,
                char** value_out, uint32_t* value_len) {

    off_t offset;
    if (!hashmap_get(&store->index, key, key_len, &offset)) {
        return 0;
    }

	if (value_len == 0) {
    	return 0; // treated as deleted
	}

    return log_read_value(&store->log, offset, value_out, value_len);
}

void kvstore_close(kvstore* store) {
    hashmap_free(&store->index);
    log_close(&store->log);
}