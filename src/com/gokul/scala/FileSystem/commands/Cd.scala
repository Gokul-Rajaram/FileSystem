package com.gokul.scala.FileSystem.commands
import com.gokul.scala.FileSystem.FileSys.State
import com.gokul.scala.FileSystem.Files.{DirEntry, Directories}

import scala.annotation.tailrec

class Cd(dir: String) extends Command {
  override def apply(input: String, state: State): State = {
    //1.find root
    val root = state.root
    val wd = state.wd

    //2.find the absolute path of the directory i want to cd to
    val absolutePath =
      if(dir.startsWith(Directories.SEPERATOR)) dir
      else if(wd.isRoot) wd.path + dir
      else wd.path + dir

    //3.find the directory to cd to given the path
    val destinationFolder = doFindEntry(root,absolutePath)

    //4.change the state given the new directory
    if(destinationFolder == null || !destinationFolder.isDirectory)
      state.setMessage(dir + ": No Such Directory")
    else
      State(root,destinationFolder.asDirectory)

  }
  def doFindEntry(root: Directories, path: String): DirEntry = {
    @tailrec
    def findEntryHelper(currentDirectory: Directories, path: List[String]):DirEntry ={
      if(path.isEmpty || path.head.isEmpty) currentDirectory
      else if(path.tail.isEmpty) currentDirectory.findEntry(path.head)
      else {
        val nextDir = currentDirectory.findEntry(path.head)
        if(nextDir == null || !nextDir.isDirectory) null
        else findEntryHelper(nextDir.asDirectory,path.tail)
      }
    }

    //1.tokens
    val tokens: List[String] = path.substring(1).split(Directories.SEPERATOR).toList

    //2.navigate to the correct entry
    findEntryHelper(root,tokens)
  }

}
