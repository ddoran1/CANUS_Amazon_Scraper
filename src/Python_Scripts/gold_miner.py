###############################################################################
#  NOTE: The first print statement made appears to be the only thing to 
#  stream when executing from java, all else is ignored or not piped through
###############################################################################
import sys
from sqlite_connection import sqlite_connection

if __name__ == '__main__':
    con = sqlite_connection()
    dict, keys = con.get_dict_from_table("view_all_CAN_Product")

    if dict is None:
        print("NO GOOD NO GOOD")
    else:
        file = open('src\\Python_Scripts\\output.txt', 'w', encoding='utf-8')
        for row in dict: 
            file.write(str(row) + " : {\n")
            file.write("\t" + str(keys[1]) + " : " + str(dict[row][keys[1]]) + "\n")
            file.write("\t" + str(keys[2]) + " : " + str(dict[row][keys[2]]) + "\n")
            file.write("\t" + str(keys[3]) + " : " + str(dict[row][keys[3]]) + "\n")
            file.write("\t" + str(keys[4]) + " : " + str(dict[row][keys[4]]) + "\n")
            file.write("\t" + str(keys[5]) + " : " + str(dict[row][keys[5]]) + "\n")
            file.write("\t" + str(keys[6]) + " : " + str(dict[row][keys[6]]) + "\n")
            file.write("\t}\n")

        file.write("}")
        print("DATABASE EXPORT COMPLETE!")
        file.close()

    
    
    
    




