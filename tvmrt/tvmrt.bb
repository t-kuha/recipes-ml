#
# TVM runtime
#

SUMMARY = "TVM runtime"
DESCRIPTION = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"

# Version to use
PV = "0.11.1"
PR = "r0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=6ec4db95cc43836f5e2ff1d6edaa2284"

SRC_URI = " \
    gitsm://github.com/apache/tvm;protocol=https;nobranch=1;tag=v${PV} \
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
    -DUSE_LIBBACKTRACE=OFF \
    -DCMAKE_BUILD_TYPE=Release \
"

do_configure:prepend(){
    echo set\(USE_LLVM OFF\) >> ${S}/config.cmake
    echo set\(USE_VITIS_AI ON\) >> ${S}/config.cmake
}

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
