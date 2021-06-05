#
# oneDNN
#

SUMMARY = "oneDNN"
DESCRIPTION = "oneAPI Deep Neural Network Library (oneDNN)"

# oneDNN version to use
PV = "2.2.3"
PR = "r0"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=8e17c0f9656ebaf0c380d9b22707c846"

S = "${WORKDIR}/git/"

SRC_URI = "\
    git://github.com/oneapi-src/oneDNN.git;tag=v${PV};nobranch=1 \
"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
"
