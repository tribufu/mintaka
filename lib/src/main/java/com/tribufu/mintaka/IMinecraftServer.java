// Copyright (c) Tribufu. All Rights Reserved.
// SPDX-License-Identifier: MIT

package com.tribufu.mintaka;

import java.util.logging.Logger;

public interface IMinecraftServer {
    String getName();
    String getVersion();
    Logger getLogger();
    String getIpAddress();
    int getMaxPlayers();
    int getPort();
    int getViewDistance();
    int getSimulationDistance();
}
