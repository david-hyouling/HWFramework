package com.huawei.android.hardware.fmradio;

public class FmRxEvCallbacksAdaptor {
    public static BaseFmRxEvCallbacksAdaptor mBaseFmRxEvCallbacksAdaptor;
    public static FmRxEvCallbacksAdaptor mInstance;

    public FmRxEvCallbacksAdaptor() {
        mInstance = this;
        mBaseFmRxEvCallbacksAdaptor = new BaseFmRxEvCallbacksAdaptor();
    }

    public static FmRxEvCallbacksAdaptor getInstance() {
        return mInstance;
    }

    public void FmRxEvEnableReceiver() {
    }

    public void FmRxEvDisableReceiver() {
    }

    public void FmRxEvRadioTuneStatus(int freq) {
    }

    public void FmRxEvRdsLockStatus(boolean rdsAvail) {
    }

    public void FmRxEvSearchInProgress() {
    }

    public void FmRxEvSearchComplete(int freq) {
    }

    public void FmRxEvRdsPsInfo() {
    }

    public void FmRxEvRdsRtInfo() {
    }

    public void FmRxEvRdsAfInfo() {
    }

    public void FmRxEvRadioReset() {
    }

    public void FmRxEvStereoStatus(boolean stereo) {
    }

    public void FmRxEvServiceAvailable(boolean service) {
    }

    public void FmRxEvSearchListComplete() {
    }

    public void FmRxEvRdsGroupData() {
    }

    public void FmRxEvSignalUpdate() {
    }
}
