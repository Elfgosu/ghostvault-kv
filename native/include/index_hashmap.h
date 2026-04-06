//
// Created by thilo on 24.03.2026.
//

#ifndef GHOSTVAULT_KV_INDEX_HASHMAP_H
#define GHOSTVAULT_KV_INDEX_HASHMAP_H

#include <stdint.h>
#include <stdio.h>

/**
 * Hashmap entry.
 *
 * @field key key associated with value at offset
 * @field key_len length of key
 * @field offset offset of value in storage
 * @field occupied 1 if occupied, 0 if not
 */
typedef struct {
    char* key;
    uint32_t key_len;
    off_t offset;
    int occupied;
} hashmap_entry;

/**
 * Hashmap index for storage.
 *
 * @field entries entries of hashmap associating key and offset in storage
 * @field capacity capacity of the hashmap
 */
typedef struct {
    hashmap_entry* entries;
    uint32_t capacity;
} hashmap;

/**
 * Creates hashmap for certain capacity and zero-ing all entries.
 *
 * @param map pointer to hashmap
 * @param capacity the capacity of the hashmap
 */
void hashmap_init(hashmap* map, uint32_t capacity);

/**
 * Inserts a key/offset entry in the hashmap with linear probing.
 *
 * @param map pointer to hashmap
 * @param key key of data in storage
 * @param key_len length of the data key
 * @param offset offset of key in storage
 */
void hashmap_put(hashmap* map, const char* key, uint32_t key_len, off_t offset);

/**
 * Returns the offset for a key in storage if found in the hashmap with linear probing.
 *
 * @param map pointer to hashmap
 * @param key key of data in storage
 * @param key_len length of the data key
 * @param offset offset of key in storage
 * @return 1 if successful, 0 if not
 */
int hashmap_get(hashmap* map, const char* key, uint32_t key_len, off_t* offset);

/**
 * Frees the key as well as hashmap entries memory allocation.
 *
 * @param map pointer to hashmap
 */
void hashmap_free(hashmap* map);

#endif //GHOSTVAULT_KV_INDEX_HASHMAP_H