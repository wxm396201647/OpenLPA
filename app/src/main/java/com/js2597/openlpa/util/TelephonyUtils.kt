package com.js2597.openlpa.util

import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import com.truphone.lpa.LocalProfileInfo
import java.lang.Exception

val TelephonyManager.supportsDSDS: Boolean
    /* API 29: force support Dual SIM
    get() = supportedModemCount == 2
    */
    get() = if (isMultiSimSupported == 0 ) { true } else { false }

var TelephonyManager.dsdsEnabled: Boolean
    /* API 29:
    get() = activeModemCount >= 2
    */
    // get() = if (supportsDSDS == true) { try { isModemEnabledForSlot(1) } catch (e: Exception) { false } } else { false }
    get() = if (supportsDSDS == true) { true } else { false }
    set(value) {
        switchMultiSimConfig(if (value) { 2 } else {1})
    }

fun SubscriptionManager.tryRefreshCachedEuiccInfo(cardId: Int) {
    if (cardId != 0) {
        try {
            requestEmbeddedSubscriptionInfoListRefresh(cardId)
        } catch (e: Exception) {
            // Ignore
        }
    }
}

val LocalProfileInfo.displayName: String
    get() = nickName.ifEmpty { name }

val List<LocalProfileInfo>.operational: List<LocalProfileInfo>
    get() = filter {
        it.profileClass == LocalProfileInfo.Clazz.Operational
    }