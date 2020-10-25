#
# libtorch
#

SUMMARY = "libtorch"
DESCRIPTION = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"

# Version to use
PV = "1.6.0"
PR = "r0"

S = "${WORKDIR}/git/"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=3e14a924c16f7d828b8335a59da64074"

SRC_URI = " \
    git://github.com/pytorch/pytorch.git;protocol=https;nobranch=1;tag=v${PV} \
"

DEPENDS += "openblas protobuf gflags glog"
DEPENDS += "protobuf-native "

inherit cmake 

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DBUILD_PYTHON=OFF \
    -DUSE_CUDA=OFF \
    -DUSE_NCCL=OFF \
    -DUSE_MPI=OFF \
    -DUSE_FBGEMM=OFF \
    -DUSE_NUMA=OFF \
    -DBLAS=OpenBLAS \
    -DUSE_MKLDNN=OFF \
    -DUSE_METAL=OFF \
    -DUSE_GLOO=OFF \
"

do_configure_prepend(){
    cd ${S}
    git submodule sync
    git submodule update --init --recursive
    # cd ${WORKDIR}
}
