
rootProject.name = "kraft"

include(
    ":core",
    ":semanticui",

    // Addons
    ":addons:browserdetect",
    ":addons:chartjs",
    ":addons:konva",
    ":addons:marked",
    ":addons:nxcompile",
    ":addons:pdfjs",
    ":addons:prismjs",
    ":addons:signaturepad",
    ":addons:sourcemappedstacktrace",

    // Examples
    ":examples:addons",
    ":examples:hello-world",
    ":examples:fomanticui",
)
