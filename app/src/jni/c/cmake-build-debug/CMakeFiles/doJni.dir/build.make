# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.15

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /home/zhc/bin/clion-2019.3.3/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /home/zhc/bin/clion-2019.3.3/bin/cmake/linux/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/zhc/code/some-tools/app/src/jni/c

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/zhc/code/some-tools/app/src/jni/c/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/doJni.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/doJni.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/doJni.dir/flags.make

CMakeFiles/doJni.dir/codecs/doJNI.c.o: CMakeFiles/doJni.dir/flags.make
CMakeFiles/doJni.dir/codecs/doJNI.c.o: ../codecs/doJNI.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/zhc/code/some-tools/app/src/jni/c/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/doJni.dir/codecs/doJNI.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/doJni.dir/codecs/doJNI.c.o   -c /home/zhc/code/some-tools/app/src/jni/c/codecs/doJNI.c

CMakeFiles/doJni.dir/codecs/doJNI.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/doJni.dir/codecs/doJNI.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/zhc/code/some-tools/app/src/jni/c/codecs/doJNI.c > CMakeFiles/doJni.dir/codecs/doJNI.c.i

CMakeFiles/doJni.dir/codecs/doJNI.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/doJni.dir/codecs/doJNI.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/zhc/code/some-tools/app/src/jni/c/codecs/doJNI.c -o CMakeFiles/doJni.dir/codecs/doJNI.c.s

# Object files for target doJni
doJni_OBJECTS = \
"CMakeFiles/doJni.dir/codecs/doJNI.c.o"

# External object files for target doJni
doJni_EXTERNAL_OBJECTS =

doJni: CMakeFiles/doJni.dir/codecs/doJNI.c.o
doJni: CMakeFiles/doJni.dir/build.make
doJni: CMakeFiles/doJni.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/zhc/code/some-tools/app/src/jni/c/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable doJni"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/doJni.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/doJni.dir/build: doJni

.PHONY : CMakeFiles/doJni.dir/build

CMakeFiles/doJni.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/doJni.dir/cmake_clean.cmake
.PHONY : CMakeFiles/doJni.dir/clean

CMakeFiles/doJni.dir/depend:
	cd /home/zhc/code/some-tools/app/src/jni/c/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/zhc/code/some-tools/app/src/jni/c /home/zhc/code/some-tools/app/src/jni/c /home/zhc/code/some-tools/app/src/jni/c/cmake-build-debug /home/zhc/code/some-tools/app/src/jni/c/cmake-build-debug /home/zhc/code/some-tools/app/src/jni/c/cmake-build-debug/CMakeFiles/doJni.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/doJni.dir/depend

