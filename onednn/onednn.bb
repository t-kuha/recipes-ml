#
# oneDNN
#

SUMMARY = "oneDNN"
DESCRIPTION = "oneAPI Deep Neural Network Library (oneDNN)"

# oneDNN version to use
SRCREV = "04b180b9a58a78cf1a1cd2329671a5060c2be8de"
PV = "3.2.1"
PR = "r0"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=3b64000f6e7d52516017622a37a94ce9"

S = "${WORKDIR}/git"

SRC_URI = "\
    git://github.com/oneapi-src/oneDNN.git;branch=rls-v3.2 \
"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
"
