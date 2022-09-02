
rootProject.name = "kraft"

include(
    ":core",
    ":semanticui",

    // Addons
    ":addons:chartjs",
    ":addons:konva",
    ":addons:marked",
    ":addons:nxcompile",
    ":addons:prismjs",
    ":addons:signaturepad",
    ":addons:sourcemappedstacktrace",

    // Examples
    ":examples:addons",
    ":examples:hello-world",
    ":examples:fomanticui",
)
