cmake_minimum_required(VERSION 3.4.1)

find_library( log-lib
              log )

add_library( wh-live
             SHARED
             pushnative.c )

target_link_libraries( wh-live
                       ${log-lib} )