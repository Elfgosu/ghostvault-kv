//
// Created by thilo on 24.03.2026.
//

#ifndef GHOSTVAULT_KV_GHOSTVAULTKV_STORE_H
#define GHOSTVAULT_KV_GHOSTVAULTKV_STORE_H

#include "index_hashmap.h"
#include "append_log.h"

/**
 * Ghostvault kv store.
 *
 * @field index hashmap index for storage
 * @field log append log storage
 */
typedef struct {
    hashmap index;
    append_log log;
} kvstore;

/**
 * Opens the Ghostvault kv store and builds the hashmap.
 *
 * @param store pointer to ghostvault store
 * @param path pointer to append log file path
 * @return 1 if successful
 */
int kvstore_open(kvstore* store, const char* path);

/**
 * Puts a key/value pair into Ghostvault kv store.
 *
 * @param store pointer to ghostvault store
 * @param key key of entry to put in store
 * @param key_len key length
 * @param value value of entry to put in store
 * @param value_len value length
 */
void kvstore_put(kvstore* store, const char* key, uint32_t key_len,
                 const char* value, uint32_t value_len);

/**
 * Retrieves a value for a specific key from Ghostvault kv store.
 *
 * @param store pointer to ghostvault store
 * @param key key of entry to put in store
 * @param key_len key length
 * @param value_out retrieved value for key
 * @param value_len value length
 * @return 1 if successful, 0 if not
 */
int kvstore_get(kvstore* store, const char* key, uint32_t key_len,
                char** value_out, uint32_t* value_len);

/**
 * Closes the Ghostvault kv store down.
 *
 * @param store pointer to ghostvault store
 */
void kvstore_close(kvstore* store);

#endif //GHOSTVAULT_KV_GHOSTVAULTKV_STORE_H