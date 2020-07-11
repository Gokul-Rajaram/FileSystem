package com.gokul.scala.FileSystem.commands

import com.gokul.scala.FileSystem.FileSys.State
import com.gokul.scala.FileSystem.Files.{DirEntry, Directories}

abstract class CreateEntry(name:String) extends Command {
  override def apply(input:String,state:State):State ={
    val wd = state.wd

    if(wd.hasEntry(name)){
      state.setMessage("File " + name + " already exists !" )
    }
    else if(name.contains(Directories.SEPERATOR)){
      // mkdir ot allowing to create directory inside another folder
      state.setMessage(name + " should not have seperator!!")
    }
    else if(CheckIllegal(name)) {
      state.setMessage(name + ": illegal entry for name !")
    }
    else doCreateEntry(state,name)

  }

  def CheckIllegal(name: String): Boolean =  {
    name.contains("$") || name.contains("@")
  }

  def doCreateEntry(state: State, name: String): State = {
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
    //val newdir = Directories.empty(wd.path, name)
    val newEntry: DirEntry = createSpecificEntry(state)

    //3.update whole directory structure starting from the root
    val newRoot = updateStructure(state.root,allDirsInPath,newEntry)

    //4.find new working directory given the full paths from wd's
    val newwd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newwd)

  }

  def createSpecificEntry(state:State): DirEntry

}
