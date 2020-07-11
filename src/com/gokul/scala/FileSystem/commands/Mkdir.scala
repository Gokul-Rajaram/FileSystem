package com.gokul.scala.FileSystem.commands

import com.gokul.scala.FileSystem.FileSys.State
import com.gokul.scala.FileSystem.Files.{DirEntry, Directories}
// making directory class
class Mkdir(name:String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry ={
    Directories.empty(state.wd.path,name)
  }


  }
