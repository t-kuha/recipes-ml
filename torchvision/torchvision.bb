#
# torchvision
#

SUMMARY = "torchvision"
DESCRIPTION = "Datasets, Transforms and Models specific to Computer Vision"

# Version to use
PV = "0.8.1"
PR = "r0"

S = "${WORKDIR}/git/"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=bd7749a3307486a4d4bfefbc81c8b796"

SRC_URI = " \
    git://github.com/pytorch/vision.git;protocol=https;nobranch=1;tag=v${PV} \
    file://0001_fix-build.patch \
"

DEPENDS += "libtorch protobuf jpeg libpng "
DEPENDS += " \
    protobuf-native \
"

inherit cmake 

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DWITH_CUDA=OFF \
    -DTorch_DIR=${DEPEND_DIR}/usr/share/cmake/Torch \
"

INSANE_SKIP_${PN}-dev += "dev-elf"
