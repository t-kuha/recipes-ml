# oneDNN
#   - ARM ComputeLibrary version must be >= 52.4.0
#

SUMMARY = "oneDNN"
DESCRIPTION = "oneAPI Deep Neural Network Library (oneDNN)"

# oneDNN version to use
SRCREV = "f1d471933dc852f956fd05389f9313c7148783d5"
PV = "3.10.2"
PR = "r0"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=05fda7e0b3a0fe6749e8443316fc9a3f"

S = "${WORKDIR}/git"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = "\
    git://github.com/uxlfoundation/oneDNN.git;protocol=https;branch=rls-v3.10 \
"

DEPENDS += " arm-computelibrary"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DACL_ROOT_DIR=${PKG_CONFIG_SYSROOT_DIR}/usr \
    -DDNNL_AARCH64_USE_ACL=ON \
"
