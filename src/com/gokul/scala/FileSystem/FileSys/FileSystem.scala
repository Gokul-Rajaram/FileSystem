package com.gokul.scala.FileSystem.FileSys

import java.util.Scanner

import com.gokul.scala.FileSystem.Files.Directories
import com.gokul.scala.FileSystem.commands.Command

object FileSystem extends App{

  val root = Directories.ROOT
  var state = State(root,root)      //declared as var since we need o obtain new state for the user command

  //to get the input from user we can use scanner object from java importing below
  val scanner = new Scanner(System.in)   //system.in is standard input

  while(true) {
    //print("$ ")
    //println(scanner.nextLine())       //nextline denotes the input datatype as string similarly there is nextint etc.,

    state.show

    val input = scanner.nextLine()
    //below we are doing like we are obtaining a new state for the command user types
    state = Command.from(input).apply(input:String,state)


  }



}
