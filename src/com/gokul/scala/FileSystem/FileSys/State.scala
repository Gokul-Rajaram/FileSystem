package com.gokul.scala.FileSystem.FileSys

import com.gokul.scala.FileSystem.Files.Directories

//Class will hold the state of our directories and other things
class State (val root:Directories,val wd:Directories,val output: String){

  //method to show the state
  def show():Unit = {
    println(output)
    print(State.SHELL_TOKEN)
  }

  def setMessage(message: String): State =
    State(root,wd,message)
}

//companion object
object State {
  val SHELL_TOKEN = "$ "

  def apply(root:Directories, wd: Directories, output: String = ""): State =
    new State(root,wd,output)
}
