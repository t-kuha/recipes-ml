# PyTorch

SUMMARY = "PyTorch"
DESCRIPTION = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"

# Version to use
SRCREV = "1eba9b3aa3c43f86f4a2c807ac8e12c4a7767340"
PV = "2.6.0"
PR = "r0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=931408ebbfa3b49f31a563bce9755581"

# "gitsm" not working?
SRC_URI = " \
    git://github.com/pytorch/pytorch.git;protocol=https;branch=release/2.6 \
    file://0001-Fix-compilation-error.patch \
"

RDEPENDS:${PN} += "protobuf gflags glog "
DEPENDS += " \
    protobuf-native \
    sleef-native \
    coreutils-native \
    protobuf \
    python3-native \
    python3-pip-native \
"

inherit cmake python3native

EXTRA_OECMAKE = "\
    -DGLIBCXX_USE_CXX11_ABI=1 \
    -DUSE_CUDA=OFF \
    -DUSE_XNNPACK=OFF \
    -DUSE_XPU=OFF \
    -DUSE_FBGEMM=OFF \
    -DUSE_NUMA=OFF \
    -DUSE_MPI=OFF \
    -DUSE_NCCL=OFF \
    -DUSE_MAGMA=OFF \
    -DBUILD_TEST=0 \
    -DBUILD_PYTHON=0 \
    -DPYTHON_EXECUTABLE=${PYTHON} \
    -DNATIVE_BUILD_DIR=${STAGING_DIR_NATIVE}/usr \
    -DCAFFE2_CUSTOM_PROTOC_EXECUTABLE=${STAGING_DIR_NATIVE}/usr/bin/protoc \
    -DBUILD_CUSTOM_PROTOBUF=OFF \
    -DCMAKE_PREFIX_PATH=${STAGING_DIR_TARGET}/usr \
"

do_configure[network] = "1"
do_configure:prepend(){
    # alternative to "gitsm"
    pushd ${S} > /dev/null
    git submodule sync
    git submodule update --init --recursive 
    popd > /dev/null

    # install required modules
    pip3 install pyyaml typing_extensions
}

FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${prefix}/share/cpuinfo/* \
    ${prefix}/share/ATen/* \
    ${prefix}/share/ATen/Declarations.yaml \
    ${prefix}/proto/* \
    ${libdir}/* \
    ${bindir}/* \
"
