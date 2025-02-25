package com.gokul.scala.FileSystem.commands

import com.gokul.scala.FileSystem.FileSys.State
import com.gokul.scala.FileSystem.Files.DirEntry

class Ls extends Command {

  override def apply(input: String, state: State): State = {
    val contents = state.wd.contents
    val niceOutput = createNiceOutput(contents)
    state.setMessage(niceOutput)
  }

  def createNiceOutput(contents: List[DirEntry]): String = {
    if(contents.isEmpty) ""
    else {
      val entry = contents.head
      entry.name + "[" +entry.getType + "]"+ "\n" + createNiceOutput(contents.tail)
    }
  }

}
