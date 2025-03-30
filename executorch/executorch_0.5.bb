# executorch

SUMMARY = "executorch"
DESCRIPTION = "On-device AI across mobile, embedded and edge for PyTorch"

# Version to use
SRCREV = "484a4ab5b717ce1f364c469509214d0ed0cdaf11"
PV = "0.5.0"
PR = "r0"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=21b445d32b017a5a4607858143cf7b27"

# The ExecuTorch repo must be cloned into a directory named exactly `executorch`
S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
# "gitsm" not working?
SRC_URI = " \
    git://github.com/pytorch/executorch.git;protocol=https;branch=release/0.5 \
    file://0001-Workaround.patch \
"

inherit cmake python3native

DEPENDS += " \
    zstd-native \
    python3-pip-native \
"

EXTRA_OECMAKE = "\
    -DBUCK2=buck2 \
    -DEXECUTORCH_BUILD_EXECUTOR_RUNNER=ON \
    -DEXECUTORCH_BUILD_HOST_TARGETS=OFF \
    -DFLATC_EXECUTABLE=flatc \
"

do_configure[network] = "1"
do_configure:prepend(){
    # alternative to "gitsm"
    pushd ${S} > /dev/null
    git submodule sync
    git submodule update --remote
    git submodule update --init --recursive
    popd > /dev/null

    # install required modules
    pip3 install pyyaml torch==2.6.0 # zstd

    # buck2
    wget -q "https://github.com/facebook/buck2/releases/download/2024-05-15/buck2-x86_64-unknown-linux-gnu.zst"
    zstd -f -d buck2-x86_64-unknown-linux-gnu.zst -o ${STAGING_DIR_NATIVE}/usr/bin/buck2
    chmod +x ${STAGING_DIR_NATIVE}/usr/bin/buck2

    # faltc
    wget -q https://github.com/google/flatbuffers/releases/download/v24.3.25/Linux.flatc.binary.g++-13.zip
    unzip Linux.flatc.binary.g++-13.zip
    cp flatc ${STAGING_DIR_NATIVE}/usr/bin/flatc
    ln -sf ${STAGING_DIR_NATIVE}/usr/bin/flatc ${S}/schema

    # workaround
    ln -sf ${S} "${WORKDIR}/executorch"
}

FILES:${PN} += " \
    ${prefix}/share/cpuinfo/* \
    ${libdir}/* \
    ${includedir}/* \
"
