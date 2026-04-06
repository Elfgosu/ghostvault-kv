//
// Created by thilo on 24.03.2026.
//

#ifndef GHOSTVAULT_KV_APPEND_LOG_H
#define GHOSTVAULT_KV_APPEND_LOG_H

#include <stdint.h>
#include <stdio.h>

/**
 * Append log structure
 *
 * @field file append log file on disk
 */
typedef struct {
    FILE* file;
} append_log;

/**
 * Opens the append log file.
 *
 * @param log pointer to log
 * @param path path to log file
 * @return 1 if successful, 0 if not
 */
int log_open(append_log* log, const char* path);

/**
 * Appends an entry to the log.
 *
 * @param log pointer to append log
 * @param key key of new entry
 * @param key_len length of key
 * @param value value of new entry
 * @param value_len length of value
 * @return the offset of the new entry in the append log
 */
off_t log_append(append_log* log, const char* key, uint32_t key_len,
                    const char* value, uint32_t value_len);

/**
 * Reads the value at the given offset.
 *
 * @param log pointer to append log
 * @param offset offset from the beginning of append log
 * @param value_out read value at given offset
 * @param value_len length of value
 * @return 1 if successful
 */
int log_read_value(append_log* log, off_t offset,
                   char** value_out, uint32_t* value_len);

/**
 * Replays the log and calls the callback function for every entry.
 *
 * @param log pointer to append log
 * @param callback function to call for every entry of log
 */
void log_replay(append_log* log, void (*callback)(void*, const char*, uint32_t, off_t), void* ctx);

/**
 * Closes the append log.
 *
 * @param log pointer to append log
 */
void log_close(append_log* log);

#endif //GHOSTVAULT_KV_APPEND_LOG_H