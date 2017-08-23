package com.xwke.security.util;

public interface DeviceConnector {
    DeviceDTO findDevice(String code);

    void saveDevice(DeviceDTO deviceDto);
}
