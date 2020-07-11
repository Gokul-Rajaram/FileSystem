package com.gokul.scala.FileSystem.commands

import com.gokul.scala.FileSystem.FileSys.State

class UnknownCommand extends Command {

  override def apply(input:String,state:State):State ={
    val message = s"'$input' is not recognized as an internal or external command."
    state.setMessage(message)
  }

}
