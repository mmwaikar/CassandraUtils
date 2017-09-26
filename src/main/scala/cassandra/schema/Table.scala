package cassandra.schema

case class Table(name: String, columns: Seq[Column]) {
  var keyspace: Keyspace = null
}
