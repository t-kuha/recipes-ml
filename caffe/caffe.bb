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

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "\
    git://github.com/BVLC/caffe.git;protocol=https;tag=${PV} \
    file://0001-Update-Makefile.config.patch  \
    file://0002-Use-CPU-as-solver-mode.patch \
    file://0003-Fix-test-when-CPU-only-mode.patch \
    file://0004-Remove-Python-from-build-target.patch \
    file://0005_disable-hdf5.patch \
    file://0006-Update-for-OpenCV-4.patch \
"

S = "${WORKDIR}/git"

# Dependencies
DEPENDS = "protobuf boost gflags glog opencv "
DEPENDS += "leveldb snappy lmdb openblas "
DEPENDS += "protobuf-native "

do_unpack_append(){
    # Copy Makefile.config.example as Makefile.config
    import os
    import shutil
    
    s = d.getVar('S')
    shutil.copy(os.path.join(s, 'Makefile.config.example'), os.path.join(s, 'Makefile.config'))
}

# do_configure_prepend(){
#     # Fix include path for OpenCV4
#     find ${S} -type f -name *.cpp | xargs sed -i -e s/"opencv2\/"/"opencv4\/opencv2\/"/g
# }
# CV_LOAD_IMAGE_GRAYSCALE and CV_LOAD_IMAGE_COLOR are not defined anymore and should be replaced by cv::IMREAD_COLOR and cv::IMREAD_GRAYSCALE
do_compile () {
    oe_runmake distribute
}

do_install () {
    rm -rf ${D}
    mkdir -p ${D}/usr
    cp -R ${S}/distribute/* ${D}/usr
}

FILES_${PN} += " \
    ${prefix}/proto/* \
"
