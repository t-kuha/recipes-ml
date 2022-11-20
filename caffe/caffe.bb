#
# Caffe
#

SUMMARY = "Caffe"
DESCRIPTION = "Caffe: a fast open framework for deep learning."

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=91d560803ea3d191c457b12834553991"

# Caffe version to use
PV = "1.0"
PR = "r0"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "\
    git://github.com/BVLC/caffe.git;protocol=https;tag=${PV} \
    file://0001-Update-Makefile.config.patch  \
    file://0002-Use-CPU-as-solver-mode.patch \
    file://0003-Fix-test-when-CPU-only-mode.patch \
    file://0004-Remove-Python-from-build-target.patch \
    file://0005-Remove-snappy.patch \
    file://0006-Disable-hdf5.patch \
    file://0007-Update-for-OpenCV-4.patch \
    file://0008-Update-for-newer-Protobuf.patch \
"

S = "${WORKDIR}/git"

# Dependencies
RDEPENDS:${PN} = "protobuf boost gflags glog opencv leveldb lmdb openblas "
DEPENDS += "protobuf boost gflags glog opencv leveldb lmdb openblas protobuf-native "

do_unpack:append(){
    # Copy Makefile.config.example as Makefile.config
    import os
    import shutil
    
    s = d.getVar('S')
    shutil.copy(os.path.join(s, 'Makefile.config.example'), os.path.join(s, 'Makefile.config'))
}

do_compile () {
    oe_runmake distribute
}

do_install () {
    rm -rf ${D}
    mkdir -p ${D}/usr
    cp -R ${S}/distribute/* ${D}/usr
}

FILES:${PN} += " \
    ${prefix}/proto/* \
"
