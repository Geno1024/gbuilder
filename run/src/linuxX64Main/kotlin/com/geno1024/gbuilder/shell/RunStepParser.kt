package com.geno1024.gbuilder.shell

import com.geno1024.gbuilder.utils.FileUtils

class RunStepParser(projectLocation: String, configFile: String = "gbuilder.step")
{
    val content = FileUtils.readFileContents("$projectLocation/$configFile")
}
