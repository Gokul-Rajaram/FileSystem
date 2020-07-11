package com.gokul.scala.FileSystem.commands

import com.gokul.scala.FileSystem.FileSys.State

//this trait holds the commands we will be using in our filesystem
trait Command {

  def apply(input:String ="",state:State): State

}

object Command{
  def from(input: String):Command = {
    //input will be having all the statement which includes command and arguments passed so we need to split
    val tokens: Array[String] = input.split(" ")

    new UnknownCommand
  }
}
