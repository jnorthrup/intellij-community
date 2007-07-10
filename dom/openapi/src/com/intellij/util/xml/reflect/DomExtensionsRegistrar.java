/*
 * Copyright (c) 2000-2007 JetBrains s.r.o. All Rights Reserved.
 */
package com.intellij.util.xml.reflect;

import com.intellij.util.xml.XmlName;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

/**
 * @author peter
 */
public interface DomExtensionsRegistrar {

  @NotNull DomExtension registerFixedNumberChildExtension(@NotNull XmlName name, @NotNull Type type);

  @NotNull DomExtension registerCollectionChildrenExtension(@NotNull XmlName name, @NotNull Type type);

  @NotNull DomExtension registerAttributeChildExtension(@NotNull XmlName name, final Type parameterType);

}
