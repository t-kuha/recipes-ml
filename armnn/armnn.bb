#
# armnn
#

SUMMARY = "ARM NN"
DESCRIPTION = "Arm NN ML Software."

# Version to use
SRCREV = "2431f762c03ca4092e7970eb312280ddc24a7629"
PV = "24.02"
PR = "r0"

S = "${WORKDIR}/git"


LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=3e14a924c16f7d828b8335a59da64074"

SRC_URI = " \
    git://github.com/ARM-software/armnn;protocol=https;branch=branches/armnn_24_02 \
"

DEPENDS += "boost arm-computelibrary"

inherit cmake 

# https://github.com/STMicroelectronics/meta-st-stm32mpu-ai/blob/master/recipes-frameworks/armnn/armnn_20.05.bb
EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DARMCOMPUTENEON=ON \
    -DSHARED_BOOST=ON \
    -DCMAKE_CXX_FLAGS='-Wno-array-bounds -Wno-maybe-uninitialized' \
    -DCMAKE_SYSROOT=${PKG_CONFIG_SYSROOT_DIR} \
"
