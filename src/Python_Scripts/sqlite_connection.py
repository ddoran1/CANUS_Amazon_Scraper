import sqlite3

class sqlite_connection:
    
    def __init__(self):
        try:
            con = sqlite3.connect('appDB.db')
        except:
            print("DATABASE CONNECTION FAILED")
        else:
            print("DATABASE CONNECTION SUCCESSFUL")
        finally:
            con.close()
            
    @staticmethod        
    def get_query(file_name):
        path = "src\\SQLQueries\\" + file_name + ".sql"
        file = open(path, "r")
        query = file.read()
        file.close()
        return query        
            
    @staticmethod
    def get_cursor():
        con = sqlite3.connect('appDB.db')
        cur = con.cursor()
        return cur
    
    
    def executeQuery(self, file_name):
        cur = self.get_cursor()
        query = self.get_query(file_name) 
        cur.execute(query)
        cur.execute(query)
        results = cur.fetchall()
        field_names = [i[0] for i in cur.description]
        cur.close()
        return results, field_names
    
    #NEED TO FIX  AND MAKE DYNAMIC BUT GOOD ENOUGH FOR NOW
    def get_dict_from_table(self, file_name):
        results, field_names = self.executeQuery(file_name)
        dict = {}
        i = 0
        for x in results:
            dict[i] = {
                    field_names[1] : x[1], #name
                    field_names[2] : x[2], #brand
                    field_names[3] : x[3], #link
                    field_names[4] : x[4], #price
                    field_names[5] : x[5], #num_of_ratings
                    field_names[6] : x[6] #rating
                    }
            i += 1
            
        return dict, field_names
        
        
    
    def export_table_to_file(self):
        cur = self.get_cursor()
        query = self.get_query("view_all_CAN_Product")
        cur.execute(query)
        results = cur.fetchall()
        field_names = [i[0] for i in cur.description]
        if results is None:
            print("NO GOOD NO GOOD")
        else:
            file = open('src\\Python_Scripts\\output.txt', 'w', encoding='utf-8')
            file.write(str(field_names) + "\n")
            for x in results:
                file.write(str(x) + "\n")
            print("DATABASE EXPORT COMPLETE!")
            file.close()
        cur.close()
        
        
        