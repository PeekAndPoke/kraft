
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
    ":addons:sourcemappedstacktrace",

    // Examples
    ":examples:addons",
    ":examples:hello-world",
    ":examples:semanticui",
)
