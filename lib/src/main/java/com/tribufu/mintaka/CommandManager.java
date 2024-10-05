// Copyright (c) Tribufu. All Rights Reserved.
// SPDX-License-Identifier: MIT

package com.tribufu.mintaka;

public abstract class CommandManager implements IManager {
    public abstract void setupCommands();

    @Override
    public void setup() {
        setupCommands();
    }
}
