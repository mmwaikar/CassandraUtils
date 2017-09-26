package cassandra.schema

case class Keyspace(name: String, tables: Seq[Table])
