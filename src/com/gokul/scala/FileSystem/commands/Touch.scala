package com.gokul.scala.FileSystem.commands

import com.gokul.scala.FileSystem.FileSys.{File, State}
import com.gokul.scala.FileSystem.Files.DirEntry

class Touch(name:String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry = {
    File.empty(state.wd.path,name)
  }

}
