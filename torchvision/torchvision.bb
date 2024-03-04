#
# torchvision
#

SUMMARY = "torchvision"
DESCRIPTION = "Datasets, Transforms and Models specific to Computer Vision"

# Version to use
SRCREV = "4fd856bfbcf59a4da3a91f0e12515c7ef0709777"
PV = "0.17.1"
PR = "r0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=bd7749a3307486a4d4bfefbc81c8b796"

SRC_URI = " \
    git://github.com/pytorch/vision.git;protocol=https;branch=release/0.17 \
    file://0001-Add-support-for-Yocto-build.patch \
"

RDEPENDS:${PN} += "pytorch jpeg libpng ffmpeg "
DEPENDS += " \
    python3-native \
    protobuf-native \
    python3-numpy \
    python3-pillow \
    pytorch \
    jpeg \
    libpng \
    ffmpeg \
"

inherit setuptools3
