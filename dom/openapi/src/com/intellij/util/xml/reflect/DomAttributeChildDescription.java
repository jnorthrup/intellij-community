/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.intellij.util.xml.reflect;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.JavaMethod;
import org.jetbrains.annotations.Nullable;

/**
 * @author peter
 */
public interface DomAttributeChildDescription<T> extends DomChildrenDescription{
  GenericAttributeValue<T> getDomAttributeValue(DomElement parent);

  @Nullable
  JavaMethod getGetterMethod();

}
