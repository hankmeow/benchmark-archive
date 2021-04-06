package com.hankmew.benchmark.library.code;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.vm.VM;

public class AddressTest {
    @Test
    public void testAddress() {
        String answer = "42";
        System.out.println("The memory address is " + VM.current().addressOf(answer));
    }
}
