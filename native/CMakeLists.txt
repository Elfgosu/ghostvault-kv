cmake_minimum_required(VERSION 4.1)
project(ghostvault_kv C)

set(CMAKE_C_STANDARD 11)

find_package(JNI REQUIRED)

include_directories(
        include
        ${JNI_INCLUDE_DIRS}
)

add_library(ghostvault_kv SHARED
        src/append_log.c
        src/ghostvaultkv_store.c
        src/index_hashmap.c
        src/jni_bridge.c)

set_target_properties(ghostvault_kv PROPERTIES
    RUNTIME_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}"
)