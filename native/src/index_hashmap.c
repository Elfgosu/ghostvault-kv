//
// Created by thilo on 24.03.2026.
//

#include "index_hashmap.h"
#include <stdlib.h>
#include <string.h>

// Simple implementation of djb2 hashing algorithm
static uint32_t hash(const char* key, uint32_t len) {
    uint32_t h = 5381;
    for (uint32_t i = 0; i < len; i++)
        h = ((h << 5) + h) + key[i];
    return h;
}

void hashmap_init(hashmap* map, const uint32_t capacity) {
    map->capacity = capacity;
    map->entries = calloc(capacity, sizeof(hashmap_entry));
}

void hashmap_put(hashmap* map, const char* key, uint32_t key_len, off_t offset) {
    uint32_t idx = hash(key, key_len) % map->capacity;

    while (map->entries[idx].occupied) {
        if (map->entries[idx].key_len == key_len &&
            memcmp(map->entries[idx].key, key, key_len) == 0) {
            map->entries[idx].offset = offset;
            return;
        }
        idx = (idx + 1) % map->capacity;
    }

    map->entries[idx].key = malloc(key_len);
    memcpy(map->entries[idx].key, key, key_len);
    map->entries[idx].key_len = key_len;
    map->entries[idx].offset = offset;
    map->entries[idx].occupied = 1;
}

int hashmap_get(hashmap* map, const char* key, uint32_t key_len, off_t* offset) {
    uint32_t idx = hash(key, key_len) % map->capacity;

    while (map->entries[idx].occupied) {
        if (map->entries[idx].key_len == key_len &&
            memcmp(map->entries[idx].key, key, key_len) == 0) {
            *offset = map->entries[idx].offset;
            return 1;
        }
        idx = (idx + 1) % map->capacity;
    }
    return 0;
}

void hashmap_free(hashmap* map) {
    for (uint32_t i = 0; i < map->capacity; i++) {
        if (map->entries[i].occupied) {
            free(map->entries[i].key);
        }
    }
    free(map->entries);
}