package cassandra.schema.generator

trait CqlStatement[T] {
  def cqlStatement(t: T): String
}
