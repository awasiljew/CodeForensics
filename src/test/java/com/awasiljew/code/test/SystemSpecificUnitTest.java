package com.awasiljew.code.test;

import org.testng.annotations.Listeners;

@Listeners(value = {OnlyLinuxSupportedTestListener.class})
public interface SystemSpecificUnitTest {

}
