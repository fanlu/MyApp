#!/usr/bin/env python
#-*- encoding: utf-8 -*-

import MySQLdb
import func_ext
import gflags
FLAGS = gflags.FLAGS
gflags.DEFINE_boolean('debug_sql', False, 'debug_sql')
dd = ["host", "user", "passwd", "db", "charset", "port"]
dbconfig = {
        "sm":dict(zip(dd, ["192.168.33.103", "sm", "sm", "sm", "utf8", 3306]))
        }


class DB(object):
    fields = None

    def open(self, db_name, autoCommit=True):
        pass

    def query(self, sql):
        pass

    def error(self, e):
        print "Error %d: %s" % (e.args[0], e.args[1])

    def close(self):
        """close the mysql connect"""
        self.cur.close()
        self.db_conn.close()

    def next_record(self):
        self.cur.nextset()

    def save(self, p_table_name, p_data, updatekey):
        #insert into table (c1, c2, c3) values (1,2,3) on duplicate key update c1 = values(c1) , c2 = values(c2)
        if isinstance(updatekey, str):
            updatekey = [updatekey]
        ukargs = ','.join(['`%s`=values(%s)' % (uk, uk) for uk in updatekey])
        ikvals = p_data.values()
        real_sql = "INSERT INTO %s (%s) values (%s) ON DUPLICATE KEY UPDATE %s" % (p_table_name, ",".join(['`%s`' % k for k in p_data.keys()]), ','.join(['%s'] * len(ikvals)), ukargs)
        self.debug_sql(real_sql)
        return self.cur.execute(real_sql, ikvals)

    def insert(self, p_table_name, p_data):
        for key in p_data:
            p_data[key] = func_ext.addslashes(func_ext.uni_str(p_data[key]))
        key = "`,`".join(p_data.keys())
        value = "','".join(p_data.values())
        real_sql = "INSERT INTO %s (`%s`) VALUES ('%s')" % (p_table_name, key, value)
        return self.query(real_sql)

    def replace(self, p_table_name, p_data):
        for key in p_data:
            p_data[key] = func_ext.addslashes(func_ext.uni_str(p_data[key]))
        key = "`,`".join(p_data.keys())
        value = "','".join(p_data.values())
        real_sql = "REPLACE INTO %s (`%s`) VALUES ('%s')" % (p_table_name, key, value)
        return self.query(real_sql)

    def update(self, p_table_name, p_data, p_where):
        for key in p_data:
            p_data[key] = func_ext.addslashes(func_ext.uni_str(p_data[key]))
        for key in p_where:
            p_where[key] = func_ext.addslashes(func_ext.uni_str(p_where[key]))
        edit_sql = ",".join(["%s='%s'" % (str(x[0]), str(x[1])) for x in p_data.items()])
        where_sql = " AND ".join(["%s='%s'" % (str(x[0]), str(x[1])) for x in p_where.items()])
        real_sql = "UPDATE %s SET %s WHERE %s" % (p_table_name, edit_sql, where_sql)
        return self.query(real_sql)

    def delete(self, p_table_name, p_where):
        for key in p_where:
            p_where[key] = func_ext.addslashes(func_ext.uni_str(p_where[key]))
        where_sql = " AND ".join(["%s='%s'" % (x[0], x[1]) for x in p_where.items()])
        real_sql = "DELETE FROM %s WHERE " % (p_table_name, where_sql)
        return self.query(real_sql)

    def isinstance(self, p_table_name, p_where):
        for key in p_where:
            p_where[key] = func_ext.addslashes(func_ext.uni_str(p_where[key]))
        where_sql = " AND ".join([x[0] + "='" + x[1] + "'" for x in p_where.items()])
        real_sql = "SELECT count(*) as cnt FROM " + p_table_name + " WHERE " + where_sql
        if self.query(real_sql):
            res = self.fetch_assoc()
            return res[0]
        else:
            return 0

    def select(self, sql):
        self.query(sql)
        return self.fetch_all() or []

    def selectRow(self, sql):
        self.query(sql)
        return self.fetch_assoc()

    def fetch_all(self, upper=0):
        if self.get_num_rows():
            d = []
            result = self.cur.fetchall()
            desc = self.cur.description
            for inv in result:
                _d = {}
                for i in xrange(0, len(inv)):
                    if isinstance(inv[i], (unicode)):
                        _d[desc[i][0]] = str(func_ext.uni_str(inv[i], self.charset))
                    else:
                        _d[desc[i][0]] = inv[i]

                d.append(_d)
            return d
        else:
            return None

    def fetch_assoc(self, upper=0, lower=0):
        if self.get_num_rows():
            d = {}
            desc = self.cur.description
            self.fields = self.cur.fetchone()
            for i in xrange(0, len(self.fields)):
                if isinstance(self.fields[i], (unicode)):
                    d[desc[i][0]] = str(func_ext.uni_str(self.fields[i], self.charset))
                else:
                    d[desc[i][0]] = self.fields[i]
            return d
        else:
            return None

    def get_num_rows(self):
        return self.cur.rowcount

    def autoCommit(self, flag):
        self.query("Set AUTOCOMMIT = " + str(flag))

    def debug_sql(self, sql):
        if FLAGS.debug_sql:
            print sql


class Mysql(DB):

    def __init__(self, db_name):
        self.open(db_name)

    def get_mysql_version(self):
        """return the mysql version"""
        return MySQLdb.get_client_info()

    def open(self, db_name, autoCommit=True):
        """mysql connect"""
        dbc = dbconfig.get(db_name)
        self.db_conn = MySQLdb.connect(dbc.get("host"), user=dbc.get("user"), passwd=dbc.get("passwd"), db=dbc.get("db"), charset=dbc.get("charset"))
        self.cur = self.db_conn.cursor()
        self.charset = dbc.get("charset")
        self.set_name()

    def set_name(self):
        self.query("SET NAMES " + self.charset)
        self.query("SET CHARACTER SET " + self.charset)

    def query(self, sql):
        if isinstance(sql, unicode):
            sql = sql.encode(self.charset)
        self.debug_sql(sql)
        return self.cur.execute(sql)

    def getInsertId(self):
        self.query("SELECT LAST_INSERT_ID() AS lid")
        rs = self.cur.fetchone()
        return rs[0]

    def commit(self):
        self.db_conn.commit()

    def rollback(self):
        self.db_conn.rollback()
