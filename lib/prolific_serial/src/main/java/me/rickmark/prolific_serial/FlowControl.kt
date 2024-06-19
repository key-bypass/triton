package me.rickmark.prolific_serial

enum class FlowControl {
    OFF,
    RTSCTS,
    RFRCTS,
    DTRDSR,
    RTSCTSDTRDSR,
    XONXOFF
}