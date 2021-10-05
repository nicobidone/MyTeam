package com.example.domain.entity

enum class Position { GK, DC, DL, DR, DMC, MC, ML, MR, AMC, AMR, AML, ST }

fun String.toPosition() = when (this) {
    Position.GK.toString() -> Position.GK
    Position.DC.toString() -> Position.DC
    Position.DL.toString() -> Position.DL
    Position.DR.toString() -> Position.DR
    Position.DMC.toString() -> Position.DMC
    Position.MC.toString() -> Position.MC
    Position.ML.toString() -> Position.ML
    Position.MR.toString() -> Position.MR
    Position.AMC.toString() -> Position.AMC
    Position.AMR.toString() -> Position.AMR
    Position.AML.toString() -> Position.AML
    else -> Position.ST
}
