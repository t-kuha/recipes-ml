#
# TVM runtime
#

SUMMARY = "TVM runtime"
DESCRIPTION = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"

# Version to use
SRCREV = "97c5de63ba6baa148fdf0c545ded2cd01d7838d1"
PV = "0.13.0"
PR = "r0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=8ebd32e765578e101abe54f083786a38"

SRC_URI = " \
    gitsm://github.com/apache/tvm.git;protocol=https;branch=v0.13.0 \
    file://0001-Add-support-for-Yocto-build.patch \
"

RDEPENDS:${PN} += "\
    pyxir \
"
DEPENDS += " \
    python3-native \
    pyxir \
"

inherit cmake 

EXTRA_OECMAKE = "\
    -DUSE_VITIS_AI=ON \
    -DUSE_LIBBACKTRACE=OFF \
    -DCMAKE_BUILD_TYPE=Release \
"

do_configure:prepend(){
    echo set\(USE_LLVM OFF\) >> ${S}/config.cmake
    echo set\(USE_VITIS_AI ON\) >> ${S}/config.cmake
}

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
