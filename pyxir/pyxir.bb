
#
# PyXIR
#

SUMMARY = "PyXIR"
DESCRIPTION = "Neural Network Intermediate Representation (IR) for deep learning"

# Version to use
SRCREV = "8ce8a385a155f3ffdd84ce61501ca870cfb4a905"
PV = "0.3.5"
PR = "r0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=9ecce103fa3ee74c5a0cda57238285a6"

SRC_URI = " \
    gitsm://github.com/Xilinx/pyxir.git;protocol=https;branch=master \
    file://0001-Add-support-for-cross-compilation-in-Yocto.patch \
"

RDEPENDS:${PN} += "glog xir vart python3"
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
