cmake_minimum_required(VERSION 3.4.1)

find_library( log-lib
              log )

set(static_lib_DIR ${CMAKE_SOURCE_DIR}/libs)

add_library( faac
             STATIC
             IMPORTED )
set_target_properties( faac
                       PROPERTIES IMPORTED_LOCATION
                       ${static_lib_DIR}/libfaac.a )

add_library( rtmp
             STATIC
             IMPORTED )
set_target_properties( rtmp
                       PROPERTIES IMPORTED_LOCATION
                       ${static_lib_DIR}/librtmp.a )

add_library( x264
             STATIC
             IMPORTED )
set_target_properties( x264
                       PROPERTIES IMPORTED_LOCATION
                       ${static_lib_DIR}/libx264.a )

include_directories( ${CMAKE_SOURCE_DIR}/include/faac
                     ${CMAKE_SOURCE_DIR}/include/queue
                     ${CMAKE_SOURCE_DIR}/include/rtmp
                     ${CMAKE_SOURCE_DIR}/include/x264 )

add_library( wh-live
             SHARED
             pushnative.c
             queue.c )

target_link_libraries( wh-live
                       faac
                       rtmp
                       x264
                       ${log-lib} )