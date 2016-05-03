package com.joprice.slick

object Test {
  case class User(id: Int, name: String, age: Int)

  implicit val userGetResult = GenGetResult[User]

  implicitly[GetResult[User]]
}
