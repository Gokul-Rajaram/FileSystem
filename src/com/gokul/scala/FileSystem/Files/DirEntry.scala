package com.gokul.scala.FileSystem.Files

import com.gokul.scala.FileSystem.FileSys.File

//New directory should always have a path and name of directory
abstract class DirEntry(val parentPath: String,val name: String) {
  def path: String = {
    val seperatorisnecessary =
      if(Directories.ROOT_PATH.equals(parentPath)) ""
      else Directories.SEPERATOR

    parentPath + seperatorisnecessary + name
  }

  def asDirectory: Directories

  def asFile: File

  def getType: String

  def isDirectory: Boolean
  def isFile: Boolean
}
