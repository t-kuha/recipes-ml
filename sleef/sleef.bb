#
# sleef (Exclusively for native build / does not support cross compilation)
#

SUMMARY = "sleef"
DESCRIPTION = "SIMD Library for Evaluating Elementary Functions, vectorized libm and DFT"

# Version to use
SRCREV = "cc4b0213f2f57a2f7e8f6355758dc40973ae9998"
PV = "3.5.1"
PR = "r0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LICENSE = "BSL-1.0"

LIC_FILES_CHKSUM = "file://${S}/LICENSE.txt;md5=e4224ccaecb14d942c71d31bef20d78c  "

SRC_URI = " \
    git://github.com/shibatch/sleef.git;protocol=https;branch=master \
"

inherit cmake 

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DBUILD_SHARED_LIBS=OFF \
    -DBUILD_GNUABI_LIBS=OFF \
    -DBUILD_DFT=OFF \
    -DBUILD_TESTS=OFF \
"

do_install:append () {
    # Only command binaries are needed
    rm -r ${D}/*
    install -d ${D}/${bindir}
    install -m 755 ${B}/bin/* ${D}/${bindir}
}

# Enable native build
BBCLASSEXTEND = "native nativesdk"
