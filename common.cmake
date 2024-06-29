set(CMAKE_C_STANDARD 99)

option(IS_RELEASE "Whether this is a release build" OFF)
option(MAVEN_VERSION "The version of the maven project")

find_package(Java 1.8 REQUIRED)

include_directories(
        src/include
        "$ENV{JAVA_HOME}/include"
        "$ENV{JAVA_HOME}/include/linux"
        build/jni)

add_compile_options(-Werror -fPIC -D "MVN_VERSION=${MAVEN_VERSION}")
if(IS_RELEASE)
    add_compile_options(-O2 -flto)
else()
    add_compile_options(-g3 -Og)
endif()

add_link_options(-z noexecstack -fvisibility=hidden)
