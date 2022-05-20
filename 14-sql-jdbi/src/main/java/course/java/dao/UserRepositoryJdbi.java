package course.java.dao;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserRepositoryJdbi extends UserRepository {
    @SqlUpdate("CREATE TABLE \"users\" ( " +
            "\"id\"        BIGINT(20)   NOT NULL AUTO_INCREMENT, " +
            "\"firstName\" VARCHAR(30)  NOT NULL, " +
            "\"lastName\"  VARCHAR(30)  NOT NULL, " +
            "\"age\"       INT(11)      NULL, " +
            "\"phone\"     VARCHAR(45)  NULL, " +
            "\"username\"  VARCHAR(30)  NOT NULL, " +
            "\"password\"  VARCHAR(255) NOT NULL, " +
            "\"role\"      VARCHAR(10)  NOT NULL DEFAULT 'READER', " +
            "\"active\"    BIT(1)       NULL     DEFAULT 1, " +
            "\"created\"   DATETIME     NULL     DEFAULT NOW(), " +
            "\"modified\"  DATETIME     NULL     DEFAULT NOW(), " +
            "PRIMARY KEY (\"id\"), " +
            "UNIQUE INDEX \"username_UNIQUE\" (\"username\" ASC) VISIBLE " +
            ");")
    public boolean createTable();
}
