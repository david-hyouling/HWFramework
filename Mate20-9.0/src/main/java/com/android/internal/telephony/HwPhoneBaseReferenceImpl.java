package com.android.internal.telephony;

import com.android.internal.telephony.AbstractPhoneBase;

public class HwPhoneBaseReferenceImpl implements AbstractPhoneBase.HwPhoneBaseReference {
    private static final String TAG = "HwPhoneBaseReferenceImpl";
    private Phone mPhone;

    public HwPhoneBaseReferenceImpl(AbstractPhoneBase phoneBase) {
        this.mPhone = (Phone) phoneBase;
    }

    public int getDataRoamingScope() {
        return this.mPhone.mDcTracker.getDataRoamingScope();
    }

    public boolean setDataRoamingScope(int scope) {
        return this.mPhone.mDcTracker.setDataRoamingScope(scope);
    }
}
