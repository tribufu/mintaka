// Copyright (c) Tribufu. All Rights Reserved.
// SPDX-License-Identifier: MIT

package com.tribufu.mintaka;

public interface IMapper<I, O> {
    O map(I input);
}
