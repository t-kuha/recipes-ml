#
# TVM (Python module)
#

SUMMARY = "TVM Python"
DESCRIPTION = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"

# Version to use
PV = "0.11.1"
PR = "r0"

S = "${WORKDIR}/git/python"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=6ec4db95cc43836f5e2ff1d6edaa2284"

SRC_URI = " \
    gitsm://github.com/apache/tvm;protocol=https;nobranch=1;tag=v${PV} \
    file://0002-Add-support-for-Yocto-build-2.patch \
"

RDEPENDS:${PN} += "\
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

INSANE_SKIP:${PN} += "already-stripped"
