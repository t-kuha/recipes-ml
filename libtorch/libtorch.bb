#
# libtorch
#

SUMMARY = "libtorch"
DESCRIPTION = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"

# Version to use
PV = "1.10.1"
PR = "r0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=91a5dfdaccf53b27488cb3a639e986d5"

SRC_URI = " \
    gitsm://github.com/pytorch/pytorch.git;protocol=https;nobranch=1;tag=v${PV} \
"

DEPENDS += "protobuf gflags glog "
DEPENDS += " \
    protobuf-native \
    sleef-native \
    coreutils-native \
"

inherit cmake 

EXTRA_OECMAKE = "\
    -DGLIBCXX_USE_CXX11_ABI=1 \
    -DCMAKE_BUILD_TYPE=Release \
    -DBUILD_PYTHON=OFF \
    -DUSE_CUDA=OFF \
    -DUSE_NCCL=OFF \
    -DUSE_MPI=OFF \
    -DUSE_FBGEMM=OFF \
    -DUSE_NUMA=OFF \
    -DUSE_MKLDNN=OFF \
    -DUSE_METAL=OFF \
    -DUSE_GLOO=OFF \
    -DUSE_DISTRIBUTED=OFF \
    -DUSE_TENSORPIPE=OFF \
    -DUSE_XNNPACK=OFF \
    -DBUILD_CUSTOM_PROTOBUF=OFF \
    -DPYTHON_EXECUTABLE=/usr/bin/python3 \
    -DNATIVE_BUILD_DIR=${STAGING_DIR_NATIVE}/usr \
    -DCAFFE2_CUSTOM_PROTOC_EXECUTABLE=${STAGING_DIR_NATIVE}/usr/bin/protoc \
    -DCMAKE_CXX_IMPLICIT_INCLUDE_DIRECTORIES=${STAGING_INCDIR} \
    -DCMAKE_CROSSCOMPILING=ON \
"

do_configure_prepend(){
    cd ${S}/third_party/cpuinfo
    sed -i -e s/"|aarch64)"/"|aarch64|arm)"/g CMakeLists.txt
    sed -i -e s/"\^armv\[5-8\]"/"\^armv\[5-8\]|arm"/g CMakeLists.txt
	sed -i -e s/"|arm64)"/"|arm64|arm)"/g CMakeLists.txt
}

do_install_append(){
    rm -r ${D}${includedir}/pybind11
}

INSANE_SKIP_${PN}-dev += "dev-elf "

FILES_${PN} += " \
    ${datadir}/* \
"
