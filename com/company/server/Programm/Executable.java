package com.company.server.Programm;

import com.company.common.Net.CommandResult;
import com.company.common.Net.Request;

public interface Executable {
    CommandResult execute(Request<?> request);
}