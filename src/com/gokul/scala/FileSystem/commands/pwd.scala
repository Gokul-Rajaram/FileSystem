package com.gokul.scala.FileSystem.commands

import com.gokul.scala.FileSystem.FileSys.State

class pwd extends Command {
  override def apply(input: String, state: State): State = {
    state.setMessage(state.wd.path)
  }

}
