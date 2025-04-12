
# oneDNN
#   - ARM ComputeLibrary version must be >= v24.11.1
#

SUMMARY = "oneDNN"
DESCRIPTION = "oneAPI Deep Neural Network Library (oneDNN)"

# oneDNN version to use
SRCREV = "dfce0a11226353967a54a318d86daa588c28c668"
PV = "3.7.2"
PR = "r0"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=3b64000f6e7d52516017622a37a94ce9"

S = "${WORKDIR}/git"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = "\
    git://github.com/uxlfoundation/oneDNN.git;protocol=https;branch=rls-v3.7 \
"

DEPENDS += " arm-computelibrary"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DACL_ROOT_DIR=${PKG_CONFIG_SYSROOT_DIR}/usr \
    -DDNNL_AARCH64_USE_ACL=ON \
"
