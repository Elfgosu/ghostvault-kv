cmake_minimum_required(VERSION 4.1)
project(ghostvault_kv C)

set(CMAKE_C_STANDARD 11)

find_package(JNI REQUIRED)

include_directories(
        include
        ${JNI_INCLUDE_DIRS}
)

add_executable(ghostvault_kv
        include/append_log.h
        include/ghostvaultkv_internal_NativeGhostvaultKV.h
        include/ghostvaultkv_store.h
        include/index_hashmap.h
        src/append_log.c
        src/ghostvaultkv_store.c
        src/index_hashmap.c
        src/jni_bridge.c)
