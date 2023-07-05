# 
# snappy
# 

SUMMARY = "Snappy"
DESCRIPTION = "A fast compressor/decompressor"

S = "${WORKDIR}/git"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=f62f3080324a97b3159a7a7e61812d0c"

# Snappy version to use
SRCREV = "dc05e026488865bc69313a68bcc03ef2e4ea8e83"
PV = "1.1.10"
PR = "r0"

SRC_URI = "gitsm://github.com/google/snappy.git;protocol=https;branch=main"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DBUILD_SHARED_LIBS=ON \
    -DSNAPPY_BUILD_TESTS=OFF \
    -DSNAPPY_BUILD_BENCHMARKS=OFF \
"
