package schema

import cassandra.schema.generator.DomainCqlGenerator._
import cassandra.schema.generator.SchemaGenerator
import cassandra.schema.Keyspace
import cassandra.schema.Table
import cassandra.schema.Column

object SchemaGeneratorApp {

  def main(args: Array[String]) = {
    val ks = getKeyspaceWithTables
    println(SchemaGenerator.generateSchema(ks))
  }

  private def getKeyspaceWithTables: Keyspace = {
    val artifactsTable = getArtifactsTable
    val tables = Seq(artifactsTable)

    val ks = Keyspace("syndeia_cloud", tables)
    artifactsTable.keyspace = ks
    ks
  }

  private def getArtifactsTable: Table = {
    val syndeiaIdCol = Column("syndeia_id", "text", isPrimaryKey = true)
    val idCol = Column("id", "timeuuid")
    val gidCol = Column("gid", "text")
    val columns = Seq(syndeiaIdCol, idCol, gidCol)

    Table("artifacts", columns)
  }
}
