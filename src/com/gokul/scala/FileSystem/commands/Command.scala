package com.gokul.scala.FileSystem.commands

import com.gokul.scala.FileSystem.FileSys.State

//this trait holds the commands we will be using in our filesystem
trait Command {

  def apply(state:State): State

}

object Command{
  def from(input: String):Command =
    new UnknownCommand
}
