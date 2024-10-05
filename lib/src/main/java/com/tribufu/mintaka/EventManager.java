// Copyright (c) Tribufu. All Rights Reserved.
// SPDX-License-Identifier: MIT

package com.tribufu.mintaka;

public abstract class EventManager implements IManager {
    public abstract void setupListeners();

    @Override
    public void setup() {
        setupListeners();
    }
}
