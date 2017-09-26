package cassandra.schema.generator

import cassandra.schema.Column
import cassandra.schema.Table
import cassandra.schema.Keyspace

trait DomainCqlGenerator extends CqlGenerator {

  override implicit val columnStatement: CqlStatement[Column] = new CqlStatement[Column] {
    override def cqlStatement(c: Column): String = {
      val pk = if (c.isPrimaryKey) " PRIMARY KEY" else ""
      s"${c.name} ${c.`type`}${pk}"
    }
  }

  override implicit val tableStatement: CqlStatement[Table] = new CqlStatement[Table] {
    override def cqlStatement(t: Table): String = {
      val prefix = s"CREATE TABLE ${t.keyspace.name}.${t.name} ("
      val suffix = ");"
      val columns = t.columns.map(c => columnStatement.cqlStatement(c)).mkString(", ")
      prefix + columns + suffix
    }
  }

  override implicit val keyspaceStatement: CqlStatement[Keyspace] = new CqlStatement[Keyspace] {
    override def cqlStatement(k: Keyspace): String = {
      val separator = System.lineSeparator() + System.lineSeparator()
      val ks = s"CREATE KEYSPACE ${k.name} WITH replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };"
      val tables = k.tables.map(t => tableStatement.cqlStatement(t)).mkString(separator)
      ks + separator + tables
    }
  }
}

object DomainCqlGenerator extends DomainCqlGenerator
