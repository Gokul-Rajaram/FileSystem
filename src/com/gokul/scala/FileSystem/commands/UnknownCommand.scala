package com.gokul.scala.FileSystem.commands

import com.gokul.scala.FileSystem.FileSys.State

class UnknownCommand extends Command {

  override def apply(state:State):State ={
    state.setMessage("Command not found!")
  }

}
