package com.js2597.openlpa.core

import android.se.omapi.Channel
import com.truphone.lpa.ApduChannel
import com.truphone.lpa.ApduTransmittedListener
import com.js2597.openlpa.util.byteArrayToHex
import com.js2597.openlpa.util.hexStringToByteArray

class OmapiApduChannel(private val channel: Channel) : ApduChannel {
    override fun transmitAPDU(apdu: String): String =
        byteArrayToHex(channel.transmit(hexStringToByteArray(apdu)))

    override fun transmitAPDUS(apdus: MutableList<String>): String {
        var res = ""
        for (pdu in apdus) {
            res = transmitAPDU(pdu)
        }
        return res
    }

    override fun sendStatus() {
    }

    override fun setApduTransmittedListener(apduTransmittedListener: ApduTransmittedListener?) {
    }

    override fun removeApduTransmittedListener(apduTransmittedListener: ApduTransmittedListener?) {
    }
}