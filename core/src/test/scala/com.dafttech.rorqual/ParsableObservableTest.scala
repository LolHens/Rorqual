package com.dafttech.rorqual

import com.dafttech.rorqual.util.Next
import com.dafttech.rorqual.util.TransformableObservable._
import monix.execution.Scheduler.Implicits.global
import monix.reactive.Observable
import scodec.bits.ByteVector

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Try

/**
  * Created by pierr on 24.04.2017.
  */
object ParsableObservableTest {
  def main(args: Array[String]): Unit = {
    object SmallDevice extends BlockStorageDevice {
      override val id: String = "asdf"

      override def size: Long = ???

      override def blockSize = 16

      override def open(writable: Boolean): Try[BlockStorageHandle] = ???
    }

    def bvec(size: Int) = ByteVector((0 until size).map(_.toByte))

    val observable: Observable[ByteVector] = Observable(bvec(1), bvec(4), bvec(16))

    val result = SmallDevice.alignAsync(0, observable).map(_.size)
    //println(Await.result(result.toListL.runAsync, Duration.Inf))

    println(Await.result(SmallDevice.blockAddresses(0, 16).toListL.runAsync, Duration.Inf))

    println(Await.result(Observable.repeat("abc").transformWhile(0) {
      case (last, Next.Elem(e)) =>
      println(last)
      (last + 1, Some(e).filter(_ => last <= 5))
      case (last, _) =>
        (last, None)
    }.toListL.runAsync, Duration.Inf))
  }
}
