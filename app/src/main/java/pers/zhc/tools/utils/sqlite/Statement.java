package pers.zhc.tools.utils.sqlite;

import pers.zhc.tools.jni.JNI;

public class Statement {
    private final long statementId;

    public Statement(long statementId) {
        this.statementId = statementId;
    }

    /**
     * Bing int value.
     *
     * @param row row, start from 1.
     * @param a   value
     */
    public void bind(int row, int a) {
        JNI.Sqlite3.Statement.bind(statementId, row, a);
    }

    /**
     * Bing long value.
     *
     * @param row row, start from 1.
     * @param a   value
     */
    public void bind(int row, long a) {
        JNI.Sqlite3.Statement.bind(statementId, row, a);
    }

    /**
     * Bing double value.
     *
     * @param row row, start from 1.
     * @param a   value
     */
    public void bind(int row, double a) {
        JNI.Sqlite3.Statement.bind(statementId, row, a);
    }

    /**
     * Bind text.
     *
     * @param row row, start from 1.
     * @param s   string
     */
    public void bindText(int row, String s) {
        JNI.Sqlite3.Statement.bindText(statementId, row, s);
    }

    /**
     * Bind null value.
     *
     * @param row row, start from 1.
     */
    public void bindNull(int row) {
        JNI.Sqlite3.Statement.bindNull(statementId, row);
    }

    /**
     * Reset the values bound in the statement.
     */
    public void reset() {
        JNI.Sqlite3.Statement.reset(statementId);
    }

    /**
     * Bind bytes.
     *
     * @param row   row, start from 1.
     * @param bytes byte array
     */
    public void bindBlob(int row, byte[] bytes) {
        bindBlob(row, bytes, bytes.length);
    }

    /**
     * Bind bytes.
     *
     * @param row   row, start from 1.
     * @param bytes byte array
     * @param size  bind size
     */
    public void bindBlob(int row, byte[] bytes, int size) {
        JNI.Sqlite3.Statement.bindBlob(statementId, row, bytes, size);
    }

    /**
     * Execute this statement.
     */
    public void step() {
        JNI.Sqlite3.Statement.step(statementId);
    }

    /**
     * Release native resource, origin: `finalize()`.
     * You should guarantee it's close before closing the database.
     */
    public void release() {
        JNI.Sqlite3.Statement.finalize(statementId);
    }

    public int stepRow() {
        return JNI.Sqlite3.Statement.stepRow(statementId);
    }

    public Cursor getCursor() {
        return new Cursor(JNI.Sqlite3.Statement.getCursor(statementId));
    }

    public int getIndexByColumnName(String name) {
        return JNI.Sqlite3.Statement.getIndexByColumnName(statementId, name);
    }
}
