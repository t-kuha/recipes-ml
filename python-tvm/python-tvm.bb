#
# TVM (Python module)
#

SUMMARY = "TVM Python"
DESCRIPTION = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"

# Version to use
SRCREV = "97c5de63ba6baa148fdf0c545ded2cd01d7838d1"
PV = "0.13.0"
PR = "r0"

S = "${WORKDIR}/git/python"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=8ebd32e765578e101abe54f083786a38"

SRC_URI = " \
    gitsm://github.com/apache/tvm.git;protocol=https;branch=v0.13.0 \
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
