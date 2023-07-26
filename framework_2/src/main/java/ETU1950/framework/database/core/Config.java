package database.core;

import database.provider.PostgreSQL;

/** Quick configuration shortcut */
public class Config {
    static Database PG_DB = new PostgreSQL("localhost", "5432", "frame", "priscafehiarisoadama", "");

    public static Database getPgDb() {
        return PG_DB;
    }
}
