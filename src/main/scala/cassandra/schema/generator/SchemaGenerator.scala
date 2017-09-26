package cassandra.schema.generator

object SchemaGenerator {
  def generateSchema[T: CqlStatement](t: T) = implicitly[CqlStatement[T]].cqlStatement(t)
}
