# 
# LMDB
# 

SUMMARY = "Lightning Memory-Mapped Database"
DESCRIPTION = "Lightning Memory-Mapped Database"

S = "${WORKDIR}/git/"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LICENSE = "OpenLDAP"
LIC_FILES_CHKSUM = "file://${S}LICENSE;md5=153d07ef052c4a37a8fac23bc6031972"

# LMDB version to use
PV = "LMDB_0.9.29"
PR = "r0"

SRCREV = "8ad7be2510414b9506ec9f9e24f24d04d9b04a1a"
SRC_URI = "\
    git://github.com/LMDB/lmdb.git;protocol=https;nobranch=1 \
    file://0001-change-for-cross-compilation.patch \
    "

do_unpack_append(){
    # Move files to top directory
    import shutil
    import glob
    
    s = d.getVar('S')
    flist = glob.glob(s + "libraries/liblmdb/*")
    for f in flist:
        shutil.move(f, s)
}

do_compile () {
    oe_runmake
}

do_install () {
    oe_runmake install 'DESTDIR=${D}'
}

INSANE_SKIP_${PN}-dev += "dev-elf"
