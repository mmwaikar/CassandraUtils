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
    val keyCol = Column("key", "text")
    val typeCol = Column("type", "text")
    val nameCol = Column("name", "text")
    val descriptionCol = Column("description", "text")
    val versionCol = Column("version", "text")
    val containerCol = Column("container", "text")
    val repositoryIdCol = Column("repository_id", "text")
    val repositoryTypeCol = Column("repository_type", "text")
    val isDisabledCol = Column("is_disabled", "boolean")

    val columns = Seq(syndeiaIdCol, idCol, gidCol, keyCol, typeCol, nameCol,
        descriptionCol, versionCol, containerCol, repositoryIdCol, repositoryTypeCol,
        isDisabledCol)

    Table("artifacts", columns)
  }
}
