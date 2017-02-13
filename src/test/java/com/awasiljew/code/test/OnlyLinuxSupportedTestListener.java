package com.awasiljew.code.test;

import org.apache.commons.lang.SystemUtils;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public class OnlyLinuxSupportedTestListener implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext iTestContext) {
        return methods.stream()
                .filter(iMethodInstance -> !linuxSystemSpecificTest(iMethodInstance) || SystemUtils.IS_OS_LINUX)
                .collect(toList());
    }

    private boolean linuxSystemSpecificTest(IMethodInstance iMethodInstance) {
        return getLinuxOnlyAnnotation(iMethodInstance).isPresent();
    }

    private Optional<LinuxOnlyTest> getLinuxOnlyAnnotation(IMethodInstance iMethodInstance) {
        return ofNullable(iMethodInstance
                .getMethod()
                .getConstructorOrMethod()
                .getMethod())
                .map(m -> m.getAnnotation(LinuxOnlyTest.class));
    }

}
