package cassandra.schema

case class Column(name: String, `type`: String, isPrimaryKey: Boolean = false)
