#
# llama.cpp
#
SUMMARY = "llama.cpp"
DESCRIPTION = "LLM inference in C/C++"

# revision to use
SRCREV = "053b3f9aae63151732eccf6b7408c6418ba8746e"
PR = "r0"

S = "${WORKDIR}/git"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=1539dadbedb60aa18519febfeab70632"

SRC_URI = "\
    git://github.com/ggml-org/llama.cpp.git;protocol=https;branch=master \
"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
"

SOLIBS = ".so"
SOLIBSDEV = ".so"
FILES:${PN} = "\
    ${libdir}/lib*${SOLIBS} \
    ${bindir}/* \
"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-elf"
