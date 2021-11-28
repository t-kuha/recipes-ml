#
# sleef (Exclusively for native build / does not support cross compilation)
#

SUMMARY = "sleef"
DESCRIPTION = "SIMD Library for Evaluating Elementary Functions, vectorized libm and DFT"

# Version to use
PV = "3.5.1"
PR = "r0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
LICENSE = "BSL-1.0"

LIC_FILES_CHKSUM = "file://${S}/LICENSE.txt;md5=e4224ccaecb14d942c71d31bef20d78c  "

# SRCREV = "7f523de651585fe25cade462efccca647dcc8d02"
SRC_URI = " \
    git://github.com/shibatch/sleef.git;protocol=https;tag=${PV};nobranch=1 \
"

inherit cmake 

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DBUILD_SHARED_LIBS=OFF \
    -DBUILD_GNUABI_LIBS=OFF \
    -DBUILD_DFT=OFF \
    -DBUILD_TESTS=OFF \
"

do_install_append () {
    # Only command binaries are needed
    rm -r ${D}/*
    install -d ${D}/${bindir}
    install -m 755 ${B}/bin/* ${D}/${bindir}
}

# Enable native build
BBCLASSEXTEND = "native nativesdk"
