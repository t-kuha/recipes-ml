# 
# leveldb
# 
SUMMARY = "LevelDB library"
DESCRIPTION = "LevelDB is a fast key-value storage library written at Google that provides an ordered mapping from string keys to string values."

S = "${WORKDIR}/git/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}LICENSE;md5=92d1b128950b11ba8495b64938fc164d"

# LevelDB version to use
PV = "1.22"
PR = "r0"

SRC_URI = "git://github.com/google/leveldb.git;protocol=https;tag=${PV}"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DLEVELDB_BUILD_TESTS=OFF \
    -DLEVELDB_BUILD_BENCHMARKS=OFF \
    -DBUILD_SHARED_LIBS=ON \
"
