#
# oneDNN
#

SUMMARY = "oneDNN"
DESCRIPTION = "oneAPI Deep Neural Network Library (oneDNN)"

# oneDNN version to use
PV = "2.6"
PR = "r0"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=b48e3de3bfd47c27882a0d85b20823f5"

S = "${WORKDIR}/git"

SRC_URI = "\
    git://github.com/oneapi-src/oneDNN.git;tag=v${PV};nobranch=1 \
"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
"
