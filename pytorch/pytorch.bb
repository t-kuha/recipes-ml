#
# PyTorch
#

SUMMARY = "PyTorch"
DESCRIPTION = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"

# Version to use
PV = "1.10.0"
PR = "r0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=91a5dfdaccf53b27488cb3a639e986d5"

SRC_URI = " \
    gitsm://github.com/pytorch/pytorch.git;protocol=https;nobranch=1;tag=v${PV} \
    file://0001-Fix-compile-error.patch \
    file://0002-Pass-env.-var.-to-CMake.patch \
"

RDEPENDS_${PN} += "protobuf gflags glog python3-numpy "
DEPENDS += " \
    python3-native \
    python3-pip-native \
    python3-numpy \
    protobuf-native \
    sleef-native \
    coreutils-native \
"

inherit cmake setuptools3

do_configure_prepend(){
    # install required modules
    pip3 install pyyaml typing_extensions
}

do_configure() {
        export _PYTHON_SYSCONFIGDATA_NAME="_sysconfigdata"
        export _GLIBCXX_USE_CXX11_ABI=1
        export NATIVE_BUILD_DIR=${STAGING_DIR_NATIVE}/usr
        export CAFFE2_CUSTOM_PROTOC_EXECUTABLE=${STAGING_DIR_NATIVE}/usr/bin/protoc
        export USE_DISTRIBUTED=OFF
        export USE_XNNPACK=OFF
        export PYTHON_EXECUTABLE=${PYTHON}
        export LDSHARED="${CC} -shared"
        # CMAKE_BUILD_TYPE=Release
    distutils3_do_configure
}

do_compile() {
        export _PYTHON_SYSCONFIGDATA_NAME="_sysconfigdata"
        export _GLIBCXX_USE_CXX11_ABI=1
        export NATIVE_BUILD_DIR=${STAGING_DIR_NATIVE}/usr
        export CAFFE2_CUSTOM_PROTOC_EXECUTABLE=${STAGING_DIR_NATIVE}/usr/bin/protoc
        export USE_DISTRIBUTED=OFF
        export USE_XNNPACK=OFF
        export PYTHON_EXECUTABLE=${PYTHON}
        export CMAKE_BUILD_PARALLEL_LEVEL="2"
    distutils3_do_compile
}
