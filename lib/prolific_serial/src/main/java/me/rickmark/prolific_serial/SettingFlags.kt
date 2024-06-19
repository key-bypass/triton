package me.rickmark.prolific_serial

enum class SettingFlags(val value: UByte) {
    RI_ON(0U),
    DCD_ON(1U),
    DSR_ON(2U),
    CTS_ON(128U),
}
