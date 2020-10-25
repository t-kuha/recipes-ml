#
# armnn
#

SUMMARY = "ARM NN"
DESCRIPTION = "Arm NN ML Software."

# Version to use
PV = "20.08"
PR = "r0"

S = "${WORKDIR}/git/"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=3e14a924c16f7d828b8335a59da64074"

# The patch is based on https://review.mlplatform.org/c/ml/armnn/+/3906
SRC_URI = " \
    git://github.com/ARM-software/armnn;protocol=https;nobranch=1;tag=v${PV} \
    file://0001-Find-third-party-libs.patch \
    "

DEPENDS += "boost arm-computelibrary"

inherit cmake 

# https://github.com/STMicroelectronics/meta-st-stm32mpu-ai/blob/master/recipes-frameworks/armnn/armnn_20.05.bb
EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DARMCOMPUTENEON=ON \
    -DSHARED_BOOST=ON \
    -DCMAKE_CXX_IMPLICIT_INCLUDE_DIRECTORIES=${STAGING_INCDIR} \
"
