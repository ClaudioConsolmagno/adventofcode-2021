package dev.claudio.adventofcode2021

import org.apache.commons.lang3.StringUtils
import java.math.BigInteger

fun main() {
    Day16().main()
}

private class Day16 {
    fun main() {
        tests()
        val input: List<String> = Support.readFileAsListString("day16-input.txt")
        val inputBinary = processInput(input[0])
        val bits = processor(inputBinary)
        println(bits)
        println(sumAllVersions(bits))
    }

    fun processInput(input: String) = input.toList()
        .map { BigInteger(it.toString(), 16).toString(2) }
        .map { StringUtils.leftPad(it, 4, '0') }
        .joinToString("")

    private fun tests() {
        if (sumAllVersions(processor(processInput("D2FE28"))) != 6) throw Error()
        if (sumAllVersions(processor(processInput("38006F45291200"))) != 9) throw Error()
        if (sumAllVersions(processor(processInput("EE00D40C823060"))) != 14) throw Error()
        if (sumAllVersions(processor(processInput("8A004A801A8002F478"))) != 16) throw Error()
        if (sumAllVersions(processor(processInput("620080001611562C8802118E34"))) != 12) throw Error()
        if (sumAllVersions(processor(processInput("C0015000016115A2E0802F182340"))) != 23) throw Error()
        if (sumAllVersions(processor(processInput("A0016C880162017C3686B18A3D4780"))) != 31) throw Error()

    }

    fun sumAllVersions(bits : BITS) : Int {
        if (bits is BITSLiteral) {
            return bits.getPacketVersion()
        } else if (bits is BITSOperator) {
            return bits.getPacketVersion() + bits.packets.sumOf { sumAllVersions(it) }
        } else {
            return 0
        }
    }

    fun processor(inputBinary: String) : BITS {
//        val version =  Integer.parseInt(inputBinary.substring(0, 3), 2)
        val typeId =  Integer.parseInt(inputBinary.substring(3, 6), 2)
        val packetsString: String = inputBinary.substring(6, inputBinary.length)
        if (typeId == 4) {
            var shift = 0
            var isLast: Boolean
            val res2 = mutableListOf<String>()
            do {
                res2.add(packetsString.substring(shift + 1, shift + 5))
                isLast = packetsString[shift] == '0'
                shift += 5
            } while (!isLast)
            return BITSLiteral(inputBinary.substring(0, shift + 6), res2.joinToString("").toLong(2))
        } else {
            var operatorLength = 7
            val subPackets = mutableListOf<BITS>()
            val lengthTypeId = packetsString[0]
            if (lengthTypeId == '0') {
                var lengthOfBitsToRead = Integer.parseInt(packetsString.substring(1, 16), 2)
                var startPosition = 16
                operatorLength += 15
                do {
                    subPackets.add(processor(packetsString.substring(startPosition, startPosition + lengthOfBitsToRead)))
                    startPosition += subPackets.last().inputBinary.length
                    lengthOfBitsToRead -= subPackets.last().inputBinary.length
                    operatorLength += subPackets.last().inputBinary.length
                } while (lengthOfBitsToRead > 3)
            } else {
                var startPosition = 12
                val repeat = Integer.parseInt(packetsString.substring(1, 12), 2)
                operatorLength += 11
                repeat(repeat) {
                    subPackets.add(processor(packetsString.substring(startPosition)))
                    startPosition += subPackets.last().inputBinary.length
                    operatorLength += subPackets.last().inputBinary.length
                }
            }
            return BITSOperator(inputBinary.substring(0, operatorLength), subPackets)
        }
    }

    class BITSLiteral(inputBinary: String, val literalDec: Long) : BITS(inputBinary){
        override fun toString(): String {
            return "BITSLiteral(version=${getPacketVersion()}, typeId=${getTypeId()}, literalDec=$literalDec)"
        }
    }

    class BITSOperator(inputBinary: String, val packets: List<BITS>) : BITS(inputBinary){
        override fun toString(): String {
            return "BITSOperator(version=${getPacketVersion()}, typeId=${getTypeId()}, packets=$packets)"
        }
    }

    open class BITS(val inputBinary: String) {
        fun getPacketVersion(): Int {
            return Integer.parseInt(inputBinary.substring(0, 3), 2)
        }
        fun getTypeId(): Int {
            return Integer.parseInt(inputBinary.substring(3, 6), 2)
        }
    }
}