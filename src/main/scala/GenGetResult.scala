package com.joprice.slick

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import slick.driver.H2Driver.api._
import shapeless._
import slick.jdbc.{ GetResult, PositionedResult }

// modified from https://github.com/d6y/slickless-hlist-getresult/blob/master/src/main/scala/hlist-getresult.scala

final case class GenGetResult[T](value: GetResult[T])

object GenGetResult {
  import shapeless._

  def apply[A](implicit g: GenGetResult[A]) = g.value


  implicit val hnilGetResult = GenGetResult(new GetResult[HNil] {
    def apply(r: PositionedResult) = HNil
  })

  implicit def hlistConsGetResult[H, T <: HList](
    implicit
    h: GetResult[H],
    t: GenGetResult[T]
    ): GenGetResult[H :: T] = GenGetResult(new GetResult[H :: T] {
      def apply(r: PositionedResult) = (r << h) :: t.value(r)
    })

  implicit def mkResult[A <: Product, AR <: HList](
    implicit
    gen: Generic.Aux[A, AR],
    g: GenGetResult[AR]
  ): GenGetResult[A] =
    GenGetResult(new GetResult[A] {
      def apply(r: PositionedResult): A = gen.from(g.value(r))
  })

}

