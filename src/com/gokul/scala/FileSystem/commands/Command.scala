package com.gokul.scala.FileSystem.commands

import com.gokul.scala.FileSystem.FileSys.State

//this trait holds the commands we will be using in our filesystem
trait Command {

  def apply(input:String ="",state:State): State

}

object Command{

  val MKDIR = "mkdir"   //constants
  val LS ="ls"
  val PWD = "pwd"
  val TOUCH = "touch"
  val CD = "cd"

  def emptyCommand: Command = new Command {
    override def apply(input:String,state: State):State = state
  }

  def inCompleteCommand(name: String):Command = new Command {
    override def apply(input: String, state: State): State =
      state.setMessage(name + ": InComplete Command !!")
  }

  def from(input: String):Command = {
    //input will be having all the statement which includes command and arguments passed so we need to split
    val tokens: Array[String] = input.split(" ")

    if(input.isEmpty || tokens.isEmpty) emptyCommand
    else if (MKDIR.equals(tokens(0))) {
      if(tokens.length < 2) inCompleteCommand(MKDIR)
      else new Mkdir(tokens(1))
    }
    else if(LS.equals(tokens(0))){
      new Ls
    }
    else if(PWD.equals(tokens(0))){
      new pwd
    }
    else if(TOUCH.equals(tokens(0))){
      if(tokens.length < 2) inCompleteCommand(TOUCH)
      else new Touch(tokens(1))
    }
    else if(CD.equals(tokens(0))){
      if(tokens.length < 2) inCompleteCommand(TOUCH)
      else new Cd(tokens(1))
    }
    else new UnknownCommand
  }
}
