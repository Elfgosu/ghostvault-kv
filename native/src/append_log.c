//
// Created by thilo on 24.03.2026.
//

#include "append_log.h"
#include <stdlib.h>

int log_open(append_log* log, const char* path) {
    log->file = fopen(path, "ab+");
    return log->file != NULL;
}

off_t log_append(append_log* log, const char* key, uint32_t key_len,
                    const char* value, uint32_t value_len) {

    fseeko(log->file, 0, SEEK_END);
    off_t offset = ftell(log->file);

    fwrite(&key_len, sizeof(uint32_t), 1, log->file);
    fwrite(&value_len, sizeof(uint32_t), 1, log->file);
    fwrite(key, 1, key_len, log->file);
    fwrite(value, 1, value_len, log->file);

    fflush(log->file);
    return offset;
}

int log_read_value(append_log* log, off_t offset,
                   char** value_out, uint32_t* value_len) {

    fseeko(log->file, offset, SEEK_SET);

    uint32_t key_len;
    fread(&key_len, sizeof(uint32_t), 1, log->file);
    fread(value_len, sizeof(uint32_t), 1, log->file);

    fseeko(log->file, (off_t)key_len, SEEK_CUR);

    *value_out = malloc(*value_len);
    fread(*value_out, 1, *value_len, log->file);

    return 1;
}

void log_replay(append_log* log, void (*callback)(void* ctx, const char*, uint32_t, off_t), void* ctx) {
    fseeko(log->file, 0, SEEK_SET);

    while (1) {
        off_t offset = ftello(log->file);

        uint32_t key_len, value_len;
        if (fread(&key_len, sizeof(uint32_t), 1, log->file) != 1) break;
        if (fread(&value_len, sizeof(uint32_t), 1, log->file) != 1) break;
        /**
         *TODO is just breaking the right thing to do? Maybe construct it so that offset is passed to log_replay so that we know where the problem occured.
         */

        char* key = malloc(key_len);
        fread(key, 1, key_len, log->file);

        fseeko(log->file, (off_t)value_len, SEEK_CUR);

        callback(ctx, key, key_len, offset);
        free(key);
    }
}

void log_close(append_log* log) {
    fclose(log->file);
}