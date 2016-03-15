package io.finch.benchmarks.wrk

import io.circe.generic.auto._
import io.finch._
import io.finch.circe._
import shapeless.Witness

/**
 * How to benchmark this:
 *
 * 1. Run the server: sbt 'benchmarks/runMain io.finch.benchmarks.wrk.Finch'
 * 2. Run wrk: wrk -t4 -c24 -d30s -s benchmarks/src/main/lua/wrk.lua http://localhost:8081/
 *
 * Rule of thumb for picking values for params `t` and `c` (given that `n` is a number of logical
 * cores your machine has, including HT):
 *
 *   t = n
 *   c = t * n * 1.5
 */
object Finch extends App {
  val roundTrip: Endpoint[Payload] = post(body.as[Payload])

  serve(roundTrip.toServiceAs[Witness.`"application/json"`.T])
}
