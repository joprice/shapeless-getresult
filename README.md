# Shapeless GetResult

## Automatic shapeless GetResult for case classes

When using plain sql in slick, you have to provide a `GetResult` instance that matches the arity 
of your case class. Any time a field is added or removed, it must be updated. This becomes cumbersome 
and unsightly when many classes with many parameters are introduced. Luckily, Shapeless can overcome this by 
treating the class as a heterogeneous list of fields:

```scala
  case class User(id: Int, name: String, age: Int)

  // implicit val userGetResult = GetResult(r => User(r.<<, r.<<, r.<<)

  implicit val userGetResult = GenGetResult[User]

  implicitly[GetResult[User]]

```

If you instead want to represent your data as hlists, see [slickless](https://github.com/underscoreio/slickless)
and [the source of the original code](https://github.com/d6y/slickless-hlist-getresult/blob/master/src/main/scala/hlist-getresult.scala).

