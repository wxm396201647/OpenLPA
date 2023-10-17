package com.js2597.openlpa.core

import android.se.omapi.Channel
import android.se.omapi.SEService
import android.util.Log
import com.truphone.lpa.LocalProfileAssistant
import com.truphone.lpa.impl.LocalProfileAssistantImpl
import java.lang.Exception

class OmapiChannel private constructor(
    info: EuiccChannelInfo,
    private val channel: Channel
) : EuiccChannel(info) {
    companion object {
        private const val TAG = "OmapiChannel"
        // AID A0 00 00 05 59 10 10 FF FF FF FF 89 00 00 01 00
        private val APPLET_ID = byteArrayOf(-96, 0, 0, 5, 89, 16, 16, -1, -1, -1, -1, -119, 0, 0, 1, 0)

        fun tryConnect(service: SEService, info: EuiccChannelInfo): OmapiChannel? {
            try {
                /* API 29:
                val reader = service.getUiccReader(info.slotId + 1) // slotId from telephony starts from 0
                */
                val readers = service.getReaders()
                val filtered = readers.filter { it.isSecureElementPresent && it.name.contains("SIM") }
                val reader = filtered.getOrNull(info.slotId) ?: return null
                
                val session = reader.openSession()
                val channel = session.openLogicalChannel(APPLET_ID) ?: return null
                return OmapiChannel(info, channel)
            } catch (e: Exception) {
                Log.e(TAG, "Unable to open eUICC channel for slot ${info.slotId}, skipping")
                Log.e(TAG, Log.getStackTraceString(e))
                return null
            } finally {
                val readers = service.getReaders()
                Log.i("OpenLPA", "All: ${readers.map { it.name }}")
                val filtered = readers.filter { it.isSecureElementPresent && it.name.contains("SIM") }
                Log.i("OpenLPA", "Filtered: ${filtered.map { it.name }}")
                val reader = readers.firstOrNull { it.isSecureElementPresent && it.name.contains("SIM") } ?: return null
                Log.i("OpenLPA", "First: ${reader.name}")
            }
        }
    }

    override val lpa: LocalProfileAssistant by lazy {
        LocalProfileAssistantImpl(OmapiApduChannel(channel))
    }
    override val valid: Boolean
        get() = channel.isOpen // TODO: This has to be implemented properly

    override fun close() = channel.close()
}
