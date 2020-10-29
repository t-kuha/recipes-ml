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

LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=acf4d595f99e159bf31797aa872aef57"

SRC_URI = " \
    git://github.com/pytorch/pytorch.git;protocol=https;nobranch=1;tag=v${PV} \
    file://TryRunResults.cmake \
"

DEPENDS += "openblas protobuf gflags glog "
DEPENDS += " \
    protobuf-native \
    python3-native \
    python3-pyyaml-native \
    sleef-native"

inherit cmake 

EXTRA_OECMAKE = "\
    -DGLIBCXX_USE_CXX11_ABI=1 \
    -C${WORKDIR}/TryRunResults.cmake \
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
    -DBUILD_CUSTOM_PROTOBUF=OFF \
    -DPYTHON_EXECUTABLE=python3 \
    -DNATIVE_BUILD_DIR=${STAGING_DIR_NATIVE}/usr \
    -DCAFFE2_CUSTOM_PROTOC_EXECUTABLE=${STAGING_DIR_NATIVE}/usr/bin/protoc \
    -DCMAKE_CXX_IMPLICIT_INCLUDE_DIRECTORIES=${STAGING_INCDIR} \
    -DCMAKE_CROSSCOMPILING=ON \
"

do_configure_prepend(){
    cd ${S}
    git submodule sync
    git submodule update --init --recursive

    # Use sed - we cannot "git patch" before fetching submodules
    find . -type f -name "Configure.cmake" | xargs sed -i -e s/"set(ORG_CMAKE_C_FLAGS\ CMAKE_C_FLAGS)"/"set(ORG_CMAKE_C_FLAGS\ \$\{CMAKE_C_FLAGS\})"/g
    find . -type f -name "CMakeLists.txt" | xargs sed -i -e s/"set(CMAKE_C_FLAGS\ ORG_CMAKE_C_FLAGS)"/"set(CMAKE_C_FLAGS \$\{ORG_CMAKE_C_FLAGS\})"/g
    find . -type f -name "CMakeLists.txt" | xargs sed -i -e s/"string(CONCAT\ CMAKE_C_FLAGS\ \${SLEEF_C_FLAGS})"/"string(CONCAT\ CMAKE_C_FLAGS\ \${CMAKE_C_FLAGS}\ \"\ \" \${SLEEF_C_FLAGS})"/g
}
