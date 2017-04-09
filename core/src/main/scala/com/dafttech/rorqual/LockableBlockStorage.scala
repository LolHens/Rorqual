package com.dafttech.rorqual

import monix.reactive.Observable
import scodec.bits.ByteVector

/**
  * Created by pierr on 08.04.2017.
  */
trait LockableBlockStorage extends BlockStorage {
  def lock(): LockedBlockStorage
}

class LockedBlockStorage(blockStorage: BlockStorage) extends BlockStorage {
  override def size: Long = blockStorage.size

  override def blockSize = blockStorage.blockSize

  override def read(index: Long, length: Long, chunkSize: Long): Observable[ByteVector] =
    blockStorage.read(index, length, chunkSize)

  override def write(index: Long, data: Observable[ByteVector]): Unit =
    blockStorage.write(index, data)

  def unlock(): Unit
}