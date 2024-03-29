package de.peekandpoke.kraft.semanticui

import kotlinx.html.Tag
import kotlinx.html.visitTag

@PublishedApi
internal fun <T : Tag> T.visitNoInline(block: T.() -> Unit) {
    visitTag(block)
}

@Suppress("unused")
internal object Fn {
    // #
    const val f0001: String = "a"
    const val f0002: String = "b"
    const val f0003: String = "c"
    const val f0004: String = "d"
    const val f0005: String = "e"
    const val f0006: String = "f"
    const val f0007: String = "g"
    const val f0008: String = "h"
    const val f0009: String = "i"
    const val f0010: String = "j"
    const val f0011: String = "k"
    const val f0012: String = "l"
    const val f0013: String = "m"
    const val f0014: String = "n"
    const val f0015: String = "o"
    const val f0016: String = "p"
    const val f0017: String = "q"
    const val f0018: String = "r"
    const val f0019: String = "s"
    const val f0020: String = "t"

    // a#
    const val f0021: String = "aa"
    const val f0022: String = "ab"
    const val f0023: String = "ac"
    const val f0024: String = "ad"
    const val f0025: String = "ae"
    const val f0026: String = "af"
    const val f0027: String = "ag"
    const val f0028: String = "ah"
    const val f0029: String = "ai"
    const val f0030: String = "aj"
    const val f0031: String = "ak"
    const val f0032: String = "al"
    const val f0033: String = "am"
    const val f0034: String = "an"
    const val f0035: String = "ao"
    const val f0036: String = "ap"
    const val f0037: String = "aq"
    const val f0038: String = "ar"
    const val f0039: String = "as"
    const val f0040: String = "at"

    // b#
    const val f0041: String = "ba"
    const val f0042: String = "bb"
    const val f0043: String = "bc"
    const val f0044: String = "bd"
    const val f0045: String = "be"
    const val f0046: String = "bf"
    const val f0047: String = "bg"
    const val f0048: String = "bh"
    const val f0049: String = "bi"
    const val f0050: String = "bj"
    const val f0051: String = "bk"
    const val f0052: String = "bl"
    const val f0053: String = "bm"
    const val f0054: String = "bn"
    const val f0055: String = "bo"
    const val f0056: String = "bp"
    const val f0057: String = "bq"
    const val f0058: String = "br"
    const val f0059: String = "bs"
    const val f0060: String = "bt"

    // c#
    const val f0061: String = "ca"
    const val f0062: String = "cb"
    const val f0063: String = "cc"
    const val f0064: String = "cd"
    const val f0065: String = "ce"
    const val f0066: String = "cf"
    const val f0067: String = "cg"
    const val f0068: String = "ch"
    const val f0069: String = "ci"
    const val f0070: String = "cj"
    const val f0071: String = "ck"
    const val f0072: String = "cl"
    const val f0073: String = "cm"
    const val f0074: String = "cn"
    const val f0075: String = "co"
    const val f0076: String = "cp"
    const val f0077: String = "cq"
    const val f0078: String = "cr"
    const val f0079: String = "cs"
    const val f0080: String = "ct"

    // d#
    const val f0081: String = "da"
    const val f0082: String = "db"
    const val f0083: String = "dc"
    const val f0084: String = "dd"
    const val f0085: String = "de"
    const val f0086: String = "df"
    const val f0087: String = "dg"
    const val f0088: String = "dh"
    const val f0089: String = "di"
    const val f0090: String = "dj"
    const val f0091: String = "dk"
    const val f0092: String = "dl"
    const val f0093: String = "dm"
    const val f0094: String = "dn"
    const val f0095: String = "do"
    const val f0096: String = "dp"
    const val f0097: String = "dq"
    const val f0098: String = "dr"
    const val f0099: String = "ds"
    const val f0100: String = "dt"

    // e#
    const val f0101: String = "ea"
    const val f0102: String = "eb"
    const val f0103: String = "ec"
    const val f0104: String = "ed"
    const val f0105: String = "ee"
    const val f0106: String = "ef"
    const val f0107: String = "eg"
    const val f0108: String = "eh"
    const val f0109: String = "ei"
    const val f0110: String = "ej"
    const val f0111: String = "ek"
    const val f0112: String = "el"
    const val f0113: String = "em"
    const val f0114: String = "en"
    const val f0115: String = "eo"
    const val f0116: String = "ep"
    const val f0117: String = "eq"
    const val f0118: String = "er"
    const val f0119: String = "es"
    const val f0120: String = "et"

    // f#
    const val f0121: String = "fa"
    const val f0122: String = "fb"
    const val f0123: String = "fc"
    const val f0124: String = "fd"
    const val f0125: String = "fe"
    const val f0126: String = "ff"
    const val f0127: String = "fg"
    const val f0128: String = "fh"
    const val f0129: String = "fi"
    const val f0130: String = "fj"
    const val f0131: String = "fk"
    const val f0132: String = "fl"
    const val f0133: String = "fm"
    const val f0134: String = "fn"
    const val f0135: String = "fo"
    const val f0136: String = "fp"
    const val f0137: String = "fq"
    const val f0138: String = "fr"
    const val f0139: String = "fs"
    const val f0140: String = "ft"

    // g#
    const val f0141: String = "ga"
    const val f0142: String = "gb"
    const val f0143: String = "gc"
    const val f0144: String = "gd"
    const val f0145: String = "ge"
    const val f0146: String = "gf"
    const val f0147: String = "gg"
    const val f0148: String = "gh"
    const val f0149: String = "gi"
    const val f0150: String = "gj"
    const val f0151: String = "gk"
    const val f0152: String = "gl"
    const val f0153: String = "gm"
    const val f0154: String = "gn"
    const val f0155: String = "go"
    const val f0156: String = "gp"
    const val f0157: String = "gq"
    const val f0158: String = "gr"
    const val f0159: String = "gs"
    const val f0160: String = "gt"

    // h#
    const val f0161: String = "ha"
    const val f0162: String = "hb"
    const val f0163: String = "hc"
    const val f0164: String = "hd"
    const val f0165: String = "he"
    const val f0166: String = "hf"
    const val f0167: String = "hg"
    const val f0168: String = "hh"
    const val f0169: String = "hi"
    const val f0170: String = "hj"
    const val f0171: String = "hk"
    const val f0172: String = "hl"
    const val f0173: String = "hm"
    const val f0174: String = "hn"
    const val f0175: String = "ho"
    const val f0176: String = "hp"
    const val f0177: String = "hq"
    const val f0178: String = "hr"
    const val f0179: String = "hs"
    const val f0180: String = "ht"

    // i#
    const val f0181: String = "ia"
    const val f0182: String = "ib"
    const val f0183: String = "ic"
    const val f0184: String = "id"
    const val f0185: String = "ie"
    const val f0186: String = "if"
    const val f0187: String = "ig"
    const val f0188: String = "ih"
    const val f0189: String = "ii"
    const val f0190: String = "ij"
    const val f0191: String = "ik"
    const val f0192: String = "il"
    const val f0193: String = "im"
    const val f0194: String = "in"
    const val f0195: String = "io"
    const val f0196: String = "ip"
    const val f0197: String = "iq"
    const val f0198: String = "ir"
    const val f0199: String = "is"
    const val f0200: String = "it"

    // j#
    const val f0201: String = "ja"
    const val f0202: String = "jb"
    const val f0203: String = "jc"
    const val f0204: String = "jd"
    const val f0205: String = "je"
    const val f0206: String = "jf"
    const val f0207: String = "jg"
    const val f0208: String = "jh"
    const val f0209: String = "ji"
    const val f0210: String = "jj"
    const val f0211: String = "jk"
    const val f0212: String = "jl"
    const val f0213: String = "jm"
    const val f0214: String = "jn"
    const val f0215: String = "jo"
    const val f0216: String = "jp"
    const val f0217: String = "jq"
    const val f0218: String = "jr"
    const val f0219: String = "js"
    const val f0220: String = "jt"

    // k#
    const val f0221: String = "ka"
    const val f0222: String = "kb"
    const val f0223: String = "kc"
    const val f0224: String = "kd"
    const val f0225: String = "ke"
    const val f0226: String = "kf"
    const val f0227: String = "kg"
    const val f0228: String = "kh"
    const val f0229: String = "ki"
    const val f0230: String = "kj"
    const val f0231: String = "kk"
    const val f0232: String = "kl"
    const val f0233: String = "km"
    const val f0234: String = "kn"
    const val f0235: String = "ko"
    const val f0236: String = "kp"
    const val f0237: String = "kq"
    const val f0238: String = "kr"
    const val f0239: String = "ks"
    const val f0240: String = "kt"

    // l#
    const val f0241: String = "la"
    const val f0242: String = "lb"
    const val f0243: String = "lc"
    const val f0244: String = "ld"
    const val f0245: String = "le"
    const val f0246: String = "lf"
    const val f0247: String = "lg"
    const val f0248: String = "lh"
    const val f0249: String = "li"
    const val f0250: String = "lj"
    const val f0251: String = "lk"
    const val f0252: String = "ll"
    const val f0253: String = "lm"
    const val f0254: String = "ln"
    const val f0255: String = "lo"
    const val f0256: String = "lp"
    const val f0257: String = "lq"
    const val f0258: String = "lr"
    const val f0259: String = "ls"
    const val f0260: String = "lt"

    // m#
    const val f0261: String = "ma"
    const val f0262: String = "mb"
    const val f0263: String = "mc"
    const val f0264: String = "md"
    const val f0265: String = "me"
    const val f0266: String = "mf"
    const val f0267: String = "mg"
    const val f0268: String = "mh"
    const val f0269: String = "mi"
    const val f0270: String = "mj"
    const val f0271: String = "mk"
    const val f0272: String = "ml"
    const val f0273: String = "mm"
    const val f0274: String = "mn"
    const val f0275: String = "mo"
    const val f0276: String = "mp"
    const val f0277: String = "mq"
    const val f0278: String = "mr"
    const val f0279: String = "ms"
    const val f0280: String = "mt"

    // n#
    const val f0281: String = "na"
    const val f0282: String = "nb"
    const val f0283: String = "nc"
    const val f0284: String = "nd"
    const val f0285: String = "ne"
    const val f0286: String = "nf"
    const val f0287: String = "ng"
    const val f0288: String = "nh"
    const val f0289: String = "ni"
    const val f0290: String = "nj"
    const val f0291: String = "nk"
    const val f0292: String = "nl"
    const val f0293: String = "nm"
    const val f0294: String = "nn"
    const val f0295: String = "no"
    const val f0296: String = "np"
    const val f0297: String = "nq"
    const val f0298: String = "nr"
    const val f0299: String = "ns"
    const val f0300: String = "nt"

    // A#
    const val f9001: String = "Aa"
    const val f9002: String = "Ab"
    const val f9003: String = "Ac"
    const val f9004: String = "Ad"
    const val f9005: String = "Ae"
    const val f9006: String = "Af"
    const val f9007: String = "Ag"
    const val f9008: String = "Ah"
    const val f9009: String = "Ai"
    const val f9010: String = "Aj"
    const val f9011: String = "Ak"
    const val f9012: String = "Al"
    const val f9013: String = "Am"
    const val f9014: String = "An"
    const val f9015: String = "Ao"
    const val f9016: String = "Ap"
    const val f9017: String = "Aq"
    const val f9018: String = "Ar"
    const val f9019: String = "As"
    const val f9020: String = "At"

    // B#
    const val f9021: String = "Ba"
    const val f9022: String = "Bb"
    const val f9023: String = "Bc"
    const val f9024: String = "Bd"
    const val f9025: String = "Be"
    const val f9026: String = "Bf"
    const val f9027: String = "Bg"
    const val f9028: String = "Bh"
    const val f9029: String = "Bi"
    const val f9030: String = "Bj"
    const val f9031: String = "Bk"
    const val f9032: String = "Bl"
    const val f9033: String = "Bm"
    const val f9034: String = "Bn"
    const val f9035: String = "Bo"
    const val f9036: String = "Bp"
    const val f9037: String = "Bq"
    const val f9038: String = "Br"
    const val f9039: String = "Bs"
    const val f9040: String = "Bt"

    // C#
    const val f9041: String = "Ca"
    const val f9042: String = "Cb"
    const val f9043: String = "Cc"
    const val f9044: String = "Cd"
    const val f9045: String = "Ce"
    const val f9046: String = "Cf"
    const val f9047: String = "Cg"
    const val f9048: String = "Ch"
    const val f9049: String = "Ci"
    const val f9050: String = "Cj"
    const val f9051: String = "Ck"
    const val f9052: String = "Cl"
    const val f9053: String = "Cm"
    const val f9054: String = "Cn"
    const val f9055: String = "Co"
    const val f9056: String = "Cp"
    const val f9057: String = "Cq"
    const val f9058: String = "Cr"
    const val f9059: String = "Cs"
    const val f9060: String = "Ct"

    // D#
    const val f9061: String = "Da"
    const val f9062: String = "Db"
    const val f9063: String = "Dc"
    const val f9064: String = "Dd"
    const val f9065: String = "De"
    const val f9066: String = "Df"
    const val f9067: String = "Dg"
    const val f9068: String = "Dh"
    const val f9069: String = "Di"
    const val f9070: String = "Dj"
    const val f9071: String = "Dk"
    const val f9072: String = "Dl"
    const val f9073: String = "Dm"
    const val f9074: String = "Dn"
    const val f9075: String = "Do"
    const val f9076: String = "Dp"
    const val f9077: String = "Dq"
    const val f9078: String = "Dr"
    const val f9079: String = "Ds"
    const val f9080: String = "Dt"
}
