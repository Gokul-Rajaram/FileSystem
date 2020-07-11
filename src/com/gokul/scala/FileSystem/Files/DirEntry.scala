package com.gokul.scala.FileSystem.Files
//New directory should always have a path and name of directory
abstract class DirEntry(val parentPath: String,val name: String) {
  def  path: String = parentPath + Directories.SEPERATOR + name

  def asDirectory: Directories

  def getType: String
}
