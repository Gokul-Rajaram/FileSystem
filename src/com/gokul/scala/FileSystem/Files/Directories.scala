package com.gokul.scala.FileSystem.Files

class Directories(override val parentPath: String, override val name: String, val contents:List[DirEntry])
  extends DirEntry(parentPath,name) {

  def hasEntry(name: String): Boolean = {
    findEntry(name) != null
  }

  def getAllFoldersInPath:List[String] = {
    path.substring(1).split(Directories.SEPERATOR).toList.filter(x => ! x.isEmpty)
  }

  def findDescendant(path: List[String]): Directories = {
    if(path.isEmpty) this
    else findEntry(path.head).asDirectory.findDescendant(path.tail)
  }

  def AddEntry(newEntry:DirEntry):Directories = {
    new Directories(parentPath,name,contents :+ newEntry)   //appending newEntry with Contents
  }

  def findEntry(entryName: String): DirEntry ={
    def findEntryHelper(name: String, contentList: List[DirEntry]): DirEntry = {
      if(contentList.isEmpty) null
      else if (contentList.head.name.equals(name)) contentList.head
      else findEntryHelper(name,contentList.tail)
    }
    findEntryHelper(entryName,contents)
  }
  def replaceEntry(entryName: String,newEntry:DirEntry): Directories = {
    new Directories(parentPath,name,contents.filter(e => ! e.name.equals(entryName)) :+ newEntry)
  }

  def asDirectory: Directories = this

  def getType: String = "Folder"
}

//Creating object for having constants
object Directories{
  val SEPERATOR = "/"
  val ROOT_PATH = "/"

  /*
  Method for creating new directories --here we are going to avoid instantiation whenever
  we use it instead we create a method for instantiation below and will be using it.
   */
  def empty(parentPath: String,name:String): Directories = {
    new Directories(parentPath, name, List())
  }

  //Method for creating root directory
  def ROOT: Directories = empty("","");






}
