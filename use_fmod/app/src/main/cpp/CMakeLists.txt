cmake_minimum_required(VERSION 3.10.2)

project("fmod")
include_directories("include")
aux_source_directory(${CMAKE_SOURCE_DIR} SOURCES)
aux_source_directory("${CMAKE_SOURCE_DIR}/demo/playsound" PLAY_SOUND)
add_library(native-lib SHARED ${SOURCES} ${PLAY_SOUND})

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -L${CMAKE_SOURCE_DIR}/../jniLibs/${CMAKE_ANDROID_ARCH_ABI}")


target_link_libraries(
        native-lib
        log
        fmod
        fmodL
)

# 开启输出详细的编译和链接信息
set(CMAKE_VERBOSE_MAKEFILE on)
message("zcw before")
message(${SOURCES})
message(STATUS "zcw after")