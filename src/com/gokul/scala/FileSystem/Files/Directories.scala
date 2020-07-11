package com.gokul.scala.FileSystem.Files

class Directories(override val parentPath: String, override val name: String, val contents:List[DirEntry])
  extends DirEntry(parentPath,name) {


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
