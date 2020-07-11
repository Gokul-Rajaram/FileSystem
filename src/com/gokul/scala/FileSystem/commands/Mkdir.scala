package com.gokul.scala.FileSystem.commands

import com.gokul.scala.FileSystem.FileSys.State
import com.gokul.scala.FileSystem.Files.{DirEntry, Directories}
// making directory class
class Mkdir(name:String) extends Command {
  //implementation of mkdir command
  override def apply(input:String,state:State):State ={
    val wd = state.wd

    if(wd.hasEntry(name)){
      state.setMessage("Folder " + name + " already exists !" )
    }
    else if(name.contains(Directories.SEPERATOR)){
      // mkdir ot allowing to create directory inside another folder
      state.setMessage(name + " should not have seperator!!")
    }
    else if(CheckIllegal(name)) {
      state.setMessage(name + ": illegal entry for name !")
    }
    else doMkDir(state,name)

  }

  def CheckIllegal(name: String): Boolean =  {
    name.contains(".") && name.contains("_")
  }

  def doMkDir(state: State, name: String): State = {
    def updateStructure(CurrentDirectory: Directories, path: List[String], newEntry: DirEntry): Directories = {
     if(path.isEmpty) CurrentDirectory.AddEntry(newEntry)
     else {
       val oldEntry = CurrentDirectory.findEntry(path.head).asDirectory
       CurrentDirectory.replaceEntry(oldEntry.name,updateStructure(oldEntry,path.tail,newEntry))
     }

    }

    val wd = state.wd
    val fullPath = wd.path

    //1. All the directories should be in full path
    val allDirsInPath = wd.getAllFoldersInPath

    //2.Creating a new directory in Working Directory
    val newdir = Directories.empty(wd.path, name)

    //3.update whole directory structure starting from the root
    val newRoot = updateStructure(state.root,allDirsInPath,newdir)

    //4.find new working directory given the full paths from wd's
    val newwd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newwd)

  }


}
