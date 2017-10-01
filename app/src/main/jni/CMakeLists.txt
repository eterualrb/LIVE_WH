cmake_minimum_required(VERSION 3.4.1)

find_library( log-lib
              log )

set(x264_lib_DIR ${CMAKE_SOURCE_DIR}/libs)
add_library( x264
             STATIC
             IMPORTED )
set_target_properties( x264
                       PROPERTIES IMPORTED_LOCATION
                       ${x264_lib_DIR}/libx264.a )

include_directories(${CMAKE_SOURCE_DIR}/include)

add_library( wh-live
             SHARED
             pushnative.c )

target_link_libraries( wh-live
                       x264
                       ${log-lib} )