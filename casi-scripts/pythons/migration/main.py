# -*- coding: UTF-8 -*-
import cx_Oracle
conn = cx_Oracle.connect('challenger/challenger@10.180.85.207/challenge')
cursor = conn.cursor()