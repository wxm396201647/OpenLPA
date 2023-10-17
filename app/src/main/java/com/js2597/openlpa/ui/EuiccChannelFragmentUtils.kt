package com.js2597.openlpa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.js2597.openlpa.core.EuiccChannel
import com.js2597.openlpa.core.EuiccChannelManager
import com.js2597.openlpa.util.openlpaApplication

interface EuiccFragmentMarker

fun <T> newInstanceEuicc(clazz: Class<T>, slotId: Int): T where T: Fragment, T: EuiccFragmentMarker {
    val instance = clazz.newInstance()
    instance.arguments = Bundle().apply {
        putInt("slotId", slotId)
    }
    return instance
}

val <T> T.slotId: Int where T: Fragment, T: EuiccFragmentMarker
    get() = requireArguments().getInt("slotId")

val <T> T.euiccChannelManager: EuiccChannelManager where T: Fragment, T: EuiccFragmentMarker
    get() = openlpaApplication.euiccChannelManager

val <T> T.channel: EuiccChannel where T: Fragment, T: EuiccFragmentMarker
    get() =
        euiccChannelManager.findEuiccChannelBySlotBlocking(slotId)!!

interface EuiccProfilesChangedListener {
    fun onEuiccProfilesChanged()
}