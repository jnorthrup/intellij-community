// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.intellij.build

import com.intellij.openapi.application.PathManager
import com.intellij.util.ObjectUtils
import com.intellij.util.execution.ParametersListUtil
import org.jetbrains.intellij.build.impl.PluginLayout

import java.nio.charset.StandardCharsets

class LayoutGenerator {
  static void main(String[] args) {
    String homePath = PathManager.getHomePath(false)
    def className = args[0]
    def clazz = Class.forName(className)
    JetBrainsProductProperties properties = clazz.getConstructor(String.class).newInstance(homePath) as JetBrainsProductProperties
    List<PluginLayout> plugins = properties.getProductLayout().getAllNonTrivialPlugins()
    OutputStream stream = new FileOutputStream(PathManager.getSystemPath() + File.separator + ObjectUtils.notNull(properties.platformPrefix, "idea") + ".txt", false)
    try {
      for (PluginLayout plugin : plugins) {
        Set<String> modules = new LinkedHashSet<>()
        modules.add(plugin.getMainModule())

        plugin.moduleJars.entrySet().findAll { !it.key.contains("/") }.collectMany(modules) {it.value}

        modules.remove("intellij.platform.commercial.verifier")
        if (modules.size() == 1) continue
        stream.write((ParametersListUtil.join(new ArrayList<CharSequence> (modules)) + "\n").getBytes(StandardCharsets.UTF_8))
      }
    }
    finally {
      stream.close()
    }
  }
}
