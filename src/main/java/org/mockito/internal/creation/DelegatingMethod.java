/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.creation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.mockito.internal.invocation.MockitoMethod;

public class DelegatingMethod implements MockitoMethod {

    private final Method method;
    private final Class<?>[] parameterTypes;

    public DelegatingMethod(Method method) {
        assert method != null : "Method cannot be null";
        this.method = method;
        this.parameterTypes = SuspendMethod.trimSuspendParameterTypes(method.getParameterTypes());
    }

    @Override
    public Class<?>[] getExceptionTypes() {
        return method.getExceptionTypes();
    }

    @Override
    public Method getJavaMethod() {
        return method;
    }

    @Override
    public String getName() {
        return method.getName();
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    @Override
    public Class<?> getReturnType() {
        return method.getReturnType();
    }

    @Override
    public boolean isVarArgs() {
        return method.isVarArgs();
    }

    @Override
    public boolean isAbstract() {
        return (method.getModifiers() & Modifier.ABSTRACT) != 0;
    }

    /**
     * @return True if the input object is a DelegatingMethod which has an internal Method which is equal to the internal Method of this DelegatingMethod,
     * or if the input object is a Method which is equal to the internal Method of this DelegatingMethod.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof DelegatingMethod) {
            DelegatingMethod that = (DelegatingMethod) o;
            return method.equals(that.method);
        } else {
            return method.equals(o);
        }
    }

    @Override
    public int hashCode() {
        return method.hashCode();
    }
}
