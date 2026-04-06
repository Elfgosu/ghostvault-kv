//
// Created by thilo on 24.03.2026.
//

#include "ghostvaultkv_internal_NativeGhostvaultKV.h"
#include <jni.h>
#include <stdint.h>
#include <stdlib.h>
#include "ghostvaultkv_store.h"

static kvstore* from_handle(jlong handle) {
    return (kvstore*)(uintptr_t)handle;
}

static jlong to_handle(kvstore* store) {
    return (jlong)(uintptr_t)store;
}

JNIEXPORT jlong JNICALL
Java_kvstore_internal_NativeKVStore_open(JNIEnv* env, jobject obj, jstring path) {

    const char* c_path = (*env)->GetStringUTFChars(env, path, NULL);

    kvstore* store = malloc(sizeof(kvstore));
    if (!store) return 0;

    if (!kvstore_open(store, c_path)) {
        free(store);
        (*env)->ReleaseStringUTFChars(env, path, c_path);
        return 0;
    }

    (*env)->ReleaseStringUTFChars(env, path, c_path);
    return to_handle(store);
}

JNIEXPORT void JNICALL
Java_kvstore_internal_NativeKVStore_close(JNIEnv* env, jobject obj, jlong handle) {

    kvstore* store = from_handle(handle);
    if (!store) return;

    kvstore_close(store);
    free(store);
}

JNIEXPORT void JNICALL
Java_kvstore_internal_NativeKVStore_put(JNIEnv* env, jobject obj,
                                        jlong handle,
                                        jbyteArray key,
                                        jbyteArray value) {

    kvstore* store = from_handle(handle);
    if (!store) return;

    jsize key_len = (*env)->GetArrayLength(env, key);
    jsize value_len = (*env)->GetArrayLength(env, value);

    jbyte* key_bytes = (*env)->GetByteArrayElements(env, key, NULL);
    jbyte* value_bytes = (*env)->GetByteArrayElements(env, value, NULL);

    kvstore_put(store,
                (char*)key_bytes, key_len,
                (char*)value_bytes, value_len);

    (*env)->ReleaseByteArrayElements(env, key, key_bytes, JNI_ABORT);
    (*env)->ReleaseByteArrayElements(env, value, value_bytes, JNI_ABORT);
}

JNIEXPORT jbyteArray JNICALL
Java_kvstore_internal_NativeKVStore_get(JNIEnv* env, jobject obj,
                                        jlong handle,
                                        jbyteArray key) {

    kvstore* store = from_handle(handle);
    if (!store) return NULL;

    jsize key_len = (*env)->GetArrayLength(env, key);
    jbyte* key_bytes = (*env)->GetByteArrayElements(env, key, NULL);

    char* value = NULL;
    uint32_t value_len = 0;

    int found = kvstore_get(store,
                            (char*)key_bytes, key_len,
                            &value, &value_len);

    (*env)->ReleaseByteArrayElements(env, key, key_bytes, JNI_ABORT);

    if (!found) return NULL;

    jbyteArray result = (*env)->NewByteArray(env, value_len);
    (*env)->SetByteArrayRegion(env, result, 0, value_len, (jbyte*)value);

    free(value);
    return result;
}

JNIEXPORT void JNICALL
Java_kvstore_internal_NativeKVStore_delete(JNIEnv* env, jobject obj,
                                           jlong handle,
                                           jbyteArray key) {

    kvstore* store = from_handle(handle);
    if (!store) return;

    jsize key_len = (*env)->GetArrayLength(env, key);
    jbyte* key_bytes = (*env)->GetByteArrayElements(env, key, NULL);

    kvstore_put(store, (char*)key_bytes, key_len, NULL, 0);

    (*env)->ReleaseByteArrayElements(env, key, key_bytes, JNI_ABORT);
}