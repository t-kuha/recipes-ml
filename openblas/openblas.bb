#
# OpenBLAS
# 
SUMMARY = "OpenBLAS"
DESCRIPTION = "OpenBLAS is an optimized BLAS library based on GotoBLAS2 1.13 BSD version."

S = "${WORKDIR}/git"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=5adf4792c949a00013ce25d476a2abc0"

# Version to use
SRCREV = "394a9fbafe9010b76a2615c562204277a956eb52"
PV = "0.3.23"
PR = "r0"

SRC_URI = "\
    git://github.com/xianyi/OpenBLAS.git;protocol=https;branch=develop \
"

inherit cmake pkgconfig

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DNOFORTRAN=1 \
    -DBUILD_SHARED_LIBS=1 \
"
