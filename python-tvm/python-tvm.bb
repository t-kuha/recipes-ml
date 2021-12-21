#
# TVM (Python module)
#

SUMMARY = "TVM Python"
DESCRIPTION = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"

# Version to use
PV = "0.8.0"
PR = "r0"

S = "${WORKDIR}/git/python"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=f30f7a1bcf7728eb568504d96cee7b09"

SRC_URI = " \
    gitsm://github.com/apache/tvm;protocol=https;nobranch=1;tag=v${PV} \
    file://0002-Add-support-for-Yocto-build-2.patch \
"

RDEPENDS_${PN} += "\
    tvmrt \
    python3 \
    python3-attrs \
    python3-decorator \
    python3-numpy \
    python3-psutil \
    python3-tornado \
    python3-h5py \
    python3-cython \
    python3-pillow \
"

DEPENDS += " \
    tvmrt \
    python3-native \
    python3-cython-native \
"

inherit setuptools3
