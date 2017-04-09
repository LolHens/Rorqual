package com.dafttech.device

import java.nio.charset.{Charset, StandardCharsets}
import java.nio.file.Paths

import monix.execution.Scheduler.Implicits.global

/**
  * Created by pierr on 09.04.2017.
  */
object BlockFileTest {
  def main(args: Array[String]): Unit = {
    new BlockFile(Paths.get("D:\\pierr\\Downloads\\test.txt")).read(0, 1000).foreach { e =>
      println(e)
      println(e.decodeString(StandardCharsets.ISO_8859_1))
      println("asd")
    }
  }
}