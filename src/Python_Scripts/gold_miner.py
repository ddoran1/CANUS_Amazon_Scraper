###############################################################################
#  NOTE: The first print statement made appears to be the only thing to 
#  stream when executing from java, all else is ignored or not piped through
###############################################################################
import sys
import sqlite3

def getQuery(file_name):
    path = "src\\SQLQueries\\" + file_name + ".sql"
    file = open(path, "r")
    query = file.read()
    file.close()
    return query

if __name__ == '__main__':
    con = sqlite3.connect('appDB.db')
    cur = con.cursor()
    query = getQuery("view_all_CAN_Product")
    cur.execute(query)
    results = cur.fetchall()
    if results is None:
        print("NO GOOD NO GOOD")
    else:
        file = open('src\\Python_Scripts\\output.txt', 'w', encoding='utf-8')
        for x in results:
            file.write(str(x) + "\n")
            print("DATABASE EXPORT COMPLETE!")
        file.close()
    cur.close()
    




#return "BLAH"