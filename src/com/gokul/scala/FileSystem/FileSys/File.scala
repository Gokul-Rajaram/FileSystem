package com.gokul.scala.FileSystem.FileSys

import com.gokul.scala.FileSystem.Files.{DirEntry, Directories, FileSystemException}

class File(override val parentPath: String, override val name: String,contents:String)
  extends DirEntry(parentPath,name) {

  def asDirectory: Directories =
    throw new FileSystemException("A file Cannot be converted to a directory")

  def asFile:File = this
  def isDirectory:Boolean = false
  def isFile:Boolean = true

  def getType: String = "File"
}

object File{
  def empty(parentPath: String, Filename: String): File ={
    new File(parentPath,Filename, "")
  }

}
