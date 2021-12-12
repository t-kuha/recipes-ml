
#
# PyXIR
#

SUMMARY = "PyXIR"
DESCRIPTION = "Neural Network Intermediate Representation (IR) for deep learning"

# Version to use
PV = "0.3.2"
PR = "r0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=9ecce103fa3ee74c5a0cda57238285a6"

SRC_URI = " \
    gitsm://github.com/Xilinx/pyxir.git;protocol=https;nobranch=1;tag=v${PV} \
    file://0001-Add-support-for-cross-compilation-in-Yocto.patch \
"

RDEPENDS_${PN} += "glog xir vart python3"
DEPENDS += " \
    glog \
    xir \
    vart \
    python3-native \
"

inherit cmake setuptools3

# NOT "--use_vai_rt_dpuczdx8g"
DISTUTILS_BUILD_ARGS += "--use_vart_edge_dpu "
DISTUTILS_INSTALL_ARGS += "--use_vart_edge_dpu "
