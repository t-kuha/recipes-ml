# 
# snappy
# 

SUMMARY = "Snappy"
DESCRIPTION = "A fast compressor/decompressor"

S = "${WORKDIR}/git"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=f62f3080324a97b3159a7a7e61812d0c"

# Snappy version to use
PV = "1.1.9"
PR = "r0"

SRC_URI = "gitsm://github.com/google/snappy.git;protocol=https;tag=${PV};nobranch=1"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://0001-Disable-always_inline.patch \
"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DBUILD_SHARED_LIBS=ON \
    -DSNAPPY_BUILD_TESTS=OFF \
"
