package cassandra.schema.generator

import cassandra.schema.Column
import cassandra.schema.Table
import cassandra.schema.Keyspace

trait CqlGenerator {
  implicit val columnStatement: CqlStatement[Column]
  implicit val tableStatement: CqlStatement[Table]
  implicit val keyspaceStatement: CqlStatement[Keyspace]
}
